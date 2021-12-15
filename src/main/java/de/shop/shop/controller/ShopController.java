package de.shop.shop.controller;

import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;

import de.shop.shop.model.Order;
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

import javax.validation.Valid;


@Slf4j  //Simple Logging Facade for Java
@Controller
@RequestMapping
public class ShopController {

    private final String TAG = this.getClass().getName() +" :";
    private final BeverageService beverageService;
    private final OrderService orderService;

    @Autowired
    public ShopController(BeverageService beverageService, OrderService orderService){
        this.beverageService = beverageService;
        this.orderService =orderService;
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles" , this.beverageService.getBeverages());
        model.addAttribute("crates" , this.beverageService.getCrates());
        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Model model){
        model.addAttribute("bottle", new Bottle());
        model.addAttribute("crate", new Crate());
        return "portfolioHtml";
    }

    @GetMapping("/basket")
    public String getBasket(Model model){

        model.addAttribute("items");

        return "basketHtml";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(Order order, Model model) {
        model.addAttribute("order", order);
        return "beveragesHtml";
        }

    @PostMapping("/addToBasket")
    public void addToBasket(Bottle bottle, Model model){

        model.addAttribute("bottle", bottle);

    }


    @PostMapping("/addBottle")
    public String addBottle(@Valid Bottle bottle, Errors errors, Model model){

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottle", bottle);
            model.addAttribute("crate", new Crate());
            return "portfolioHtml";
        }

        this.beverageService.addBottle(bottle);

        return "redirect:/portfolio";
    }

    @PostMapping("/addCrate")
    public String addCrate(@Valid Crate crate, Errors errors, Model model){

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottle", new Bottle());
            model.addAttribute("crate", crate);
            return "portfolioHtml";
        }

        this.beverageService.addCrate(crate);

        return "redirect:/portfolio?type=crate";
    }



}
