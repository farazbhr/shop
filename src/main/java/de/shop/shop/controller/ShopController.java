package de.shop.shop.controller;

import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import de.shop.shop.repository.CrateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j  //Simple Logging Facade for Java
@Controller
@RequestMapping
public class ShopController {

    private final String TAG = this.getClass().getName() +" :";
    private final BeverageRepository beverageRepository;
    private final  CrateRepository crateRepository;

    @Autowired
    public ShopController(BeverageRepository beverageRepository,CrateRepository crateRepository){

        this.beverageRepository = beverageRepository;
        this.crateRepository = crateRepository;
    }

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles" , this.beverageRepository.findAll());
        model.addAttribute("crates" , this.crateRepository.findAll());
        return "beveragesHtml";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(Bottle bottle, Crate crate,Model model){

        model.addAttribute(bottle);
        model.addAttribute(crate);
        return "portfolioHtml";
    }



    @PostMapping("/portfolio") //@Valid
    public String processNewItem( Bottle bottle,Crate crate , Errors errors, Model model  ){
        log.error(TAG + "processNewItem: " + bottle);
        log.error(TAG + "processNewItem: " + crate);

        if(errors.hasErrors()){
            log.error(TAG + "Validation errors occurred : " + errors.getAllErrors());
            model.addAttribute("bottles", this.beverageRepository.findAll());
            model.addAttribute("crates", this.crateRepository.findAll());

            return "beveragesHtml";
        }
        if(bottle.getSupplier()!=null){
            this.beverageRepository.save(bottle);
        }else {
            this.crateRepository.save(crate);
        }
        return "redirect:/beverages";
    }



}
