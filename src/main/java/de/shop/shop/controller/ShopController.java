package de.shop.shop.controller;

import de.shop.shop.model.*;

import de.shop.shop.service.BeverageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j  //Simple Logging Facade for Java
@Controller
@RequestMapping
public class ShopController {

    private final String TAG = this.getClass().getName() +" :";
    private final BeverageService beverageService;

    @Autowired
    public ShopController(BeverageService beverageService){
        this.beverageService = beverageService;
    }



    @GetMapping("/portfolio")
    public String getPortfolio(Model model){
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("bottle", new Bottle());
        model.addAttribute("crate", new Crate());
       // OrderItem orderItem = ;
        model.addAttribute("orderItem", new OrderItem());
       // log.info(TAG + "orderItem get portlofio : " + orderItem);

        return "portfolioHtml";
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("crates", beverageService.getCrates());

        ShopOrder shopOrder = new ShopOrder();
        List<OrderItem> orderItems =  new ArrayList();
        for (int i= 0; i< 2 ; i++){
            OrderItem orderItem= new OrderItem();
            orderItem.setBeverageId(new Long(i+ 10));
            orderItems.add(orderItem);
            orderItem.setShopOrder(shopOrder);
            log.info(TAG + "orderItem bevor client : " + orderItem);

        }

        shopOrder.setOrderItems(orderItems);
      //  log.info(TAG + "orderItem bevor client : " + shopOrder.getOrderItems());
        model.addAttribute("shopOrder",shopOrder);
        return "beveragesHtml";
    }

    @PostMapping("/addToBasket")
    public String addToBasket(OrderItem [] orderItems ,  Errors errors, Model model){

        if(orderItems!=null){
            log.info(TAG + "shopOrder add to bas : " + orderItems);
        }



        return "redirect:/portfolio";
    }

    //change bottle to beverage
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
