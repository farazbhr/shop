package de.shop.shop.model;


import de.shop.shop.controller.ShopController;
import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class DemoData {


    @Autowired
    private final BeverageRepository BvRepo;


    public DemoData(BeverageRepository bvRepo) {
        this.BvRepo = bvRepo;
    }

    @EventListener
    public void createDemoData(ApplicationReadyEvent event){

        Beverage beverage = new Beverage();

        Bottle bottle1 = new Bottle(0.5,true,4.6,"Augustiner");
        bottle1.setName("Augustiner Hell");
        bottle1.setPrice(1.5);
        bottle1.setInStock(10);
        bottle1.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");


        Bottle bottle2 = new Bottle(0.5,true,4.6,"Kulmbacher");
        bottle2.setName("Kulmbacher Edelherb");
        bottle2.setPrice(1.2);
        bottle2.setInStock(60);
        bottle2.setPicture("https://cdn02.plentymarkets.com/q7p0kwea05gv/item/images/3901/full/23530-1.png");

        Bottle bottle3 = new Bottle(0.5,false,0,"Mönchshof");
        bottle3.setName("Mönchshof Radler");
        bottle3.setPrice(1.2);
        bottle3.setInStock(15);
        bottle3.setPicture("https://www.mönchshof.de/uploads/tx_twmhprodukte/grosse_flasche_radler_neu.png");

        Bottle bottle4 = new Bottle(0.5,true,4.5,"Oettinger");
        bottle4.setName("Oettinger Export");
        bottle4.setPrice(0.6);
        bottle4.setInStock(40);
        bottle4.setPicture("https://d2j6dbq0eux0bg.cloudfront.net/images/43861113/1989450590.jpg");

        Bottle bottle5 = new Bottle(0.5,true,4.7,"Reckendorfer");
        bottle5.setName("Reckendorfer Helle Freude");
        bottle5.setPrice(1.3);
        bottle5.setInStock(23);
        bottle5.setPicture("https://www.hier-gibts-bier.de/media/image/9a/c3/63/reckendorf_helle_freude_B303.png");

        Bottle bottle6 = new Bottle(0.5,true,6, "Schlenkerla");
        bottle6.setName("Schlenkerla Rauchbier");
        bottle6.setPrice(2);
        bottle6.setInStock(20);
        bottle6.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/521/full/Aecht-Schlenkerla-Rauchbier-Maerzen-50-cl-Bierflasc_1.jpg");

        Crate crate1 = new Crate(20, bottle1);
        crate1.setId(12L);
        crate1.setPrice(15);
        crate1.setInStock(10);
        crate1.setPicture("https://mediafile.deloma.de/image/upload/v1/images/product/39afa6d1-5240-4150-8a98-bfacf51f05a3.jpg");
        crate1.setName("Augustiner Kasten");

        Crate crate2 = new Crate(20, bottle2);
        crate1.setId(13L);
        crate2.setPrice(13);
        crate2.setInStock(10);
        crate2.setPicture("https://img.rewe-static.de/7823550/2576310_digital-image.png");
        crate2.setName("Kulmbacher Kasten");



        // saving
        this.BvRepo.saveAll(Arrays.asList(bottle1,bottle2, bottle3, bottle4, bottle5, bottle6, crate1,crate2));

    }
}
