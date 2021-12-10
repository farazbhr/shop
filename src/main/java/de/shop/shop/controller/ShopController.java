package de.shop.shop.controller;

import de.shop.shop.model.Bottle;
import de.shop.shop.repository.BeverageRepository;
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

    private String TAG = this.getClass().getName() +" :";
    private BeverageRepository beverageRepository;

    @Autowired
    public ShopController(BeverageRepository beverageRepository){
        this.beverageRepository = beverageRepository;
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottels" , this.beverageRepository.findAll());
        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Bottle bottle,Model model){

        model.addAttribute(bottle);
        return "portfolioHtml";
    }

//    @PostMapping("/portfolio")
//    public String processBottle(@Valid Bottle bottle , Errors errors, Model model  ){
//        log.error(TAG + "addBeverages: " + bottle);
//        if(errors.hasErrors()){
//            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
//            model.addAttribute("bottels", this.beverageRepository.findAll());
//            return "bottels";
//        }
//        this.beverageRepository.save(bottle);
//        return "redirect:/beverages";
//    }

}
