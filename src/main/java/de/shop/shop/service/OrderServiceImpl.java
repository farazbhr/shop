package de.shop.shop.service;

import de.shop.shop.model.*;
import de.shop.shop.repository.BeverageRepository;
import de.shop.shop.repository.OrderRepository;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BeverageRepository beverageRepository;

    @Autowired
    private SessionBasket basket;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, BeverageRepository beverageRepository){
        this.orderRepository = orderRepository;
        this.beverageRepository = beverageRepository;
    }

    @Override
    public List<?> getUnderlyingBeverages(Order order, String type) {

        List<Bottle> bottleList = new ArrayList<>();
        List<Crate> crateList = new ArrayList<>();
        for (OrderItem item : order.getOrderItemList()) {
            Long beverageId = item.getBeverageId();
            if (beverageRepository.findById(beverageId).isPresent()) {
                Beverage beverage = beverageRepository.findById(beverageId).get();
                if(type.equals("bottle") && beverage instanceof Bottle){
                        bottleList.add((Bottle) beverage);
                } else if(type.equals("crate") && beverage instanceof Crate) {
                        crateList.add((Crate) beverage);
                }
            }
        }
        if(type.equals("bottle")){
            return bottleList;
        } else {
            return crateList;
        }
    }

    @Override
    public void storeOrder(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> getStoredOrders() {
        return this.orderRepository.findAll();
    }


}
