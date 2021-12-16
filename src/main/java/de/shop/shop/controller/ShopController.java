package de.shop.shop.controller;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;

import de.shop.shop.service.BeverageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("crates", beverageService.getCrates());

        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Model model){
        model.addAttribute("bottles", beverageService.getBottles());
        model.addAttribute("bottle", new Bottle());
        model.addAttribute("crate", new Crate());
        return "portfolioHtml";
    }


    @PostMapping("/addBottle")
    public String addBottle(@Valid Bottle bottle, Errors errors, Model model){

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("beverage", bottle);
            model.addAttribute("crate", new Crate());
            return "portfolioHtml";
        }

        //set the 'isAlcoholic' attribute based on the value of 'volumePercent'
        if(bottle.getVolumePercent() > 0.0){
            bottle.setAlcoholic(true);
        }
        this.beverageService.addBeverage(bottle);

        return "redirect:/beverages";
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

        return "redirect:/beverages";
    }



}
