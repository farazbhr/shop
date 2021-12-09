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

@Slf4j
@Controller
@RequestMapping(value = "portfolio")
public class PortfolioController {

    private String TAG = this.getClass().getName() +" :";
    private BeverageRepository beverageRepository;

    @Autowired
    public PortfolioController(BeverageRepository beverageRepository){
        this.beverageRepository = beverageRepository;
    }

    @GetMapping
    public String getBeverages(Model model){
        log.info(TAG + "getBeverages(Model model) called ");
        return "portfolioHtml";
    }


    @PostMapping
    public String addBeverages(@Valid Bottle bottle , Errors errors, Model model  ){

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottels", this.beverageRepository.findAll());
            return "bottels";
        }
        this.beverageRepository.save(bottle);
        return "redirect:/beverages";
    }
}
