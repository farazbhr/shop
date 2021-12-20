package de.shop.shop.model;


import de.shop.shop.controller.ShopController;
import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import de.shop.shop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DemoData {


    private final BeverageRepository beverageRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public DemoData(BeverageRepository beverageRepository, OrderRepository orderRepository) {
        this.beverageRepository = beverageRepository;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void createDemoData(ApplicationReadyEvent event){


        Beverage bottle1 = new Bottle(10,true,10,"Schlenkerla1");
        bottle1.setName("Bier");
        bottle1.setPrice(4);
        bottle1.setId(1235L);
        bottle1.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/600/middle/Augustiner-Lagerbier-Hell-0-5-l-Bierflasche-kaufen.jpg");
        OrderItem item = new OrderItem();
        item.setPrice(bottle1.getPrice());
        item.setPosition("1");
        item.setBeverageId(bottle1.getId());
        item.setId(974L);
        System.out.println(item);


        Bottle bottle2 = new Bottle(20,false,20, "Schlenkerla2");
        bottle2.setName("Rauchbier");
        bottle2.setPrice(5);
        bottle2.setId(13459L);
        bottle2.setPicture("https://cdn02.plentymarkets.com/99cbvkn2wswt/item/images/521/full/Aecht-Schlenkerla-Rauchbier-Maerzen-50-cl-Bierflasc_1.jpg");
        OrderItem item1 = new OrderItem();
        item1.setPrice(bottle2.getPrice());
        item1.setPosition("1");
        item1.setId(984L);
        item1.setBeverageId(bottle2.getId());

        Order order = new Order();
        order.setPrice(123);
        order.setId(0L);
        List<OrderItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item1);

        order.setOrderItemList(itemList);

        // saving
        this.beverageRepository.saveAll(Arrays.asList(bottle1,bottle2));
        this.orderRepository.save(order);
    }
}
