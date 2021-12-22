package de.shop.shop.controller;

import com.google.common.collect.Multimap;
import de.shop.shop.model.*;

import de.shop.shop.service.BeverageService;
import de.shop.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j  //Simple Logging Facade for Java
@Controller
@RequestMapping
public class ShopController {

    private final String TAG = this.getClass().getName() + " :";
    private final BeverageService beverageService;
    private final OrderService orderService;

    @Autowired
    public ShopController(BeverageService beverageService, OrderService orderService) {
        this.orderService = orderService;
        this.beverageService = beverageService;
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model) {
        model.addAttribute("bottles", this.beverageService.getBottles());
        model.addAttribute("crates", this.beverageService.getCrates());

        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Model model) {
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("bottle", new Bottle());
        model.addAttribute("crate", new Crate());

        return "portfolioHtml";
    }

    @GetMapping("/order")
    public String getOrder(Model model) {

        List<Order> orderList = this.orderService.getStoredOrders();
        model.addAttribute("orders", orderList);
        return "order";
    }

    @GetMapping("/basket")
    public String getBasket(Model model) {
        Multimap<Long, List<String>> itemList = this.orderService.getSessionBasket();
        List<Bottle> existingBottles = this.beverageService.getBottles();
        List<Crate> existingCrates = this.beverageService.getCrates();
        model.addAttribute("basketBottles", this.orderService.getUnderlyingBeverages(itemList, existingBottles, existingCrates, "bottle"));
        model.addAttribute("basketCrates", this.orderService.getUnderlyingBeverages(itemList, existingBottles, existingCrates, "crate"));
        Multimap<Bottle, Integer> bottles = (Multimap<Bottle, Integer>) model.getAttribute("basketBottles");
        Multimap<Crate, Integer> crates = (Multimap<Crate, Integer>) model.getAttribute("basketCrates");
        Order order = this.orderService.createOrder(bottles, crates);
        model.addAttribute("order", order);
        System.out.println(model.getAttribute("order"));
        this.orderService.storeOrder(order);
        return "basketHtml";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(Model model) {
        this.orderService.saveOrder(this.orderService.getOrder());
        this.orderService.resetBasket();
        return "redirect:/beverages";
    }

    @PostMapping("/addToBasket")
    public String addToBasket(Model model,
                              @RequestParam(value = "id") Long id,
                              @RequestParam(value = "number") int number,
                              @RequestParam(value = "beverageType") String type) {
        this.orderService.addItemToBasket(id, number, type);
        return "redirect:/beverages";
    }

    @PostMapping("/addBottle")
    public String addBottle(@Valid Bottle bottle, Errors errors, Model model) {

        if (errors.hasErrors()) {
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottles", beverageService.getBottles());
            model.addAttribute("beverage", bottle);
            model.addAttribute("crate", new Crate());
            return "portfolioHtml";
        }

        //set the 'isAlcoholic' attribute based on the value of 'volumePercent'
        if (bottle.getVolumePercent() > 0.0) {
            bottle.setAlcoholic(true);
        }

        this.beverageService.addBeverage(bottle);

        return "redirect:/portfolio";
    }

    @PostMapping("/addCrate")
    public String addCrate(@Valid Crate crate, Errors errors, Model model,
                           @RequestParam(value = "bottleId", required = false) Long bottleId) {
        if (errors.hasErrors()) {
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottles", beverageService.getBottles());
            model.addAttribute("bottle", new Bottle());
            model.addAttribute("crate", crate);
            return "portfolioHtml";
        }

        //set the 'bottle' reference in crate based on the 'bottleId' request parameter
        List<Bottle> bottles = beverageService.getBottles();
        for (Bottle b : bottles) {
            if (b.getId() == bottleId) {
                crate.setBottle(b);
            }
        }
        this.beverageService.addBeverage(crate);
        return "redirect:/portfolio";
    }
}
