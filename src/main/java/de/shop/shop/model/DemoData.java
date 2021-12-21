package de.shop.shop;


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


    private final BeverageRepository BvRepo;


    @Autowired
    public DemoData(BeverageRepository bvRepo) {
        BvRepo = bvRepo;
    }

    @EventListener
    public void createDemoData(ApplicationReadyEvent event){

        Beverage beverage = new Beverage();

        Bottle bottle1 = new Bottle(300,true,10,"Schlenkerla");
        bottle1.setName("Bier1");
        bottle1.setPrice(4);
        bottle1.setInStock(10);
        bottle1.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");


        Bottle bottle2 = new Bottle(200,true,10,"Schlenkerla");
        bottle2.setName("Bier2");
        bottle2.setPrice(5);
        bottle2.setInStock(8);
        bottle2.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");

        Bottle bottle3 = new Bottle(200,true,10,"Schlenkerla");
        bottle3.setName("Bier3");
        bottle3.setPrice(4);
        bottle3.setInStock(15);
        bottle3.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");

        Bottle bottle4 = new Bottle(10,true,10,"Schlenkerla");
        bottle4.setName("Bier4");
        bottle4.setPrice(3);
        bottle4.setInStock(40);
        bottle4.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");

        Bottle bottle5 = new Bottle(10,true,10,"Schlenkerla");
        bottle5.setName("Bier5");
        bottle5.setPrice(1);
        bottle5.setInStock(23);
        bottle5.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");

        Bottle bottle6 = new Bottle(20,false,20, "Schlenkerla");
        bottle6.setName("Rauchbier");
        bottle6.setPrice(2);
        bottle6.setInStock(99);
        bottle6.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/521/full/Aecht-Schlenkerla-Rauchbier-Maerzen-50-cl-Bierflasc_1.jpg");

        Crate crate1 = new Crate(3, bottle1);
        crate1.setPrice(2);
        crate1.setInStock(10);
        crate1.setPicture("https://mediafile.deloma.de/image/upload/v1/images/product/39afa6d1-5240-4150-8a98-bfacf51f05a3.jpg");
        crate1.setName("BierCrate1");

        Crate crate2 = new Crate(3, bottle2);
        crate2.setPrice(25);
        crate2.setInStock(10);
        crate2.setPicture("https://mediafile.deloma.de/image/upload/v1/images/product/39afa6d1-5240-4150-8a98-bfacf51f05a3.jpg");
        crate2.setName("BierCrate2");



        // saving
        this.BvRepo.saveAll(Arrays.asList(bottle1,bottle2, bottle3, bottle4, bottle5, bottle6, crate1,crate2));

    }
}
