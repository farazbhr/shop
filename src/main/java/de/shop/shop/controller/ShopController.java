package de.shop.shop.controller;

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

    private final String TAG = this.getClass().getName() +" :";
    private final BeverageService beverageService;
    private final OrderService orderService;

    @Autowired
    public ShopController(BeverageService beverageService,OrderService orderService){
        this.orderService=orderService;
        this.beverageService = beverageService;
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles" , this.beverageService.getBottles());
        model.addAttribute("crates" , this.beverageService.getCrates());

        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Model model){
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("bottle", new Bottle());
        model.addAttribute("crate", new Crate());

        return "portfolioHtml";
    }

    @GetMapping("/order")
    public String getOrder(Model model){

        List<Order> orderList = this.orderService.getStoredOrders();
        model.addAttribute("orders", orderList);
        return "order";
    }


    @GetMapping("/basket")
    public String getBasket(Model model){

        model.addAttribute("sessionBasket", this.orderService.getSessionBasket());

        return "basketHtml";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(Order order, Model model) {
        this.orderService.storeOrder(order);
        return "beveragesHtml";
        }


    @PostMapping("/addToBasket")
    public String addToBasket(Model model,
                              @RequestParam(value="id") Long id,
                              @RequestParam(value="number") int number){


        this.orderService.addItemToBasket(id, number);

        return "redirect:/beverages";
    }



    @PostMapping("/addBottle")
    public String addBottle(@Valid Bottle bottle, Errors errors, Model model){

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottles", beverageService.getBottles());
            model.addAttribute("beverage", bottle);
            model.addAttribute("crate", new Crate());
            return "portfolioHtml";
        }

        //set the 'isAlcoholic' attribute based on the value of 'volumePercent'
        if(bottle.getVolumePercent() > 0.0){
            bottle.setAlcoholic(true);
        }

        this.beverageService.addBeverage(bottle);

        return "redirect:/portfolio";
    }

    @PostMapping("/addCrate")
    public String addCrate(@Valid Crate crate, Errors errors, Model model,
                           @RequestParam(value="bottleId", required = false) Long bottleId)
    {
        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottles", beverageService.getBottles());
            model.addAttribute("bottle", new Bottle());
            model.addAttribute("crate", crate);
            return "portfolioHtml";
        }

        //set the 'bottle' reference in crate based on the 'bottleId' request parameter
        List<Bottle> bottles = beverageService.getBottles();
        for(Bottle b : bottles){
            if(b.getId() == bottleId){
                crate.setBottle(b);
            }
        }

        this.beverageService.addBeverage(crate);

        return "redirect:/portfolio";
    }



}
