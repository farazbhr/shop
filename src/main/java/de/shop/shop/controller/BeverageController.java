package de.shop.shop.controller;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.repository.BeverageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

@Slf4j  //Simple Logging Facade for Java
@Controller
@RequestMapping(value = "beverages")
public class BeverageController {


    private BeverageRepository beverageRepository;

    @Autowired
    public BeverageController(BeverageRepository beverageRepository){
        this.beverageRepository = beverageRepository;
    }

    @GetMapping
    public String getBeverages(Model model){

        model.addAttribute("bottels" , this.beverageRepository.findAll());

        return "beveragesHtml";
    }

}
