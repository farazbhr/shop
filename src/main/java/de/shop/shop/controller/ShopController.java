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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    public String addToBasket(@Valid Order order) {



        return "redirect:/basket";
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

    public List<Bottle> generateBottleMockup() {

        List<Bottle> bottleList = new ArrayList<>();
        Bottle bottle = new Bottle();
        bottle.setBottlePic("https://wein.jpg");
        bottle.setAlcoholic(true);
        bottle.setId(12345L);
        bottle.setInStock(10);
        bottle.setName("wein");
        bottle.setSupplier("testsupplier");
        bottle.setVolume(5.0);
        bottle.setVolumePercent(5.0);
        bottle.setPrice(6);

        Bottle bottle1 = new Bottle();
        bottle.setBottlePic("https://bier.jpg");
        bottle.setAlcoholic(true);
        bottle.setId(12343L);
        bottle.setInStock(10);
        bottle.setName("bier");
        bottle.setSupplier("testanbieter");
        bottle.setVolume(5.0);
        bottle.setVolumePercent(5.0);
        bottle.setPrice(6);

        bottleList.add(bottle);
        bottleList.add(bottle1);
        return bottleList;
    }

    public List<Crate> generateCrateMockup(Bottle bottle, Bottle bottle1) {

        List<Crate> crateList = new ArrayList<>();
        Crate crate = new Crate();
        crate.setCratePic("https://weincrate.jpg");
        crate.setId(22345L);
        crate.setCratesInStock(10);
        crate.setName("weincrate");
        crate.setPrice(6);
        crate.setBottle(bottle);

        Crate crate1 = new Crate();
        crate1.setCratePic("https://biercrate.jpg");
        crate1.setId(22343L);
        crate1.setCratesInStock(10);
        crate1.setName("biercrate");
        crate1.setBottle(bottle1);
        crate1.setPrice(6);

        crateList.add(crate);
        crateList.add(crate1);
        return crateList;
    }
}
