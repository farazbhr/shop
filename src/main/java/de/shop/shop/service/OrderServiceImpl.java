package de.shop.shop.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.shop.shop.model.*;
import de.shop.shop.repository.BeverageRepository;
import de.shop.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BeverageRepository beverageRepository;

    @Autowired
    private SessionBasket basket;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, BeverageRepository beverageRepository) {
        this.orderRepository = orderRepository;
        this.beverageRepository = beverageRepository;
    }

    @Override
    public Multimap<?, Integer> getUnderlyingBeverages(Multimap<Long, List<String>> hashMap, List<Bottle> existingBottles, List<Crate> existingCrates, String type) {

        Multimap<Bottle, Integer> bottleMap = ArrayListMultimap.create();
        Multimap<Crate, Integer> crateMap = ArrayListMultimap.create();
        for (Map.Entry<Long, List<String>> item : hashMap.entries()) {
            Long id = item.getKey();
            int beverageCount = Integer.parseInt(item.getValue().get(0));
            if (type.equals("bottle") && item.getValue().get(1).equals("bottle")) {
                for (Bottle b : existingBottles) {
                    if (b.getId().equals(id)) {
                        bottleMap.put(b, beverageCount);
                    }
                }
            } else if (type.equals("crate") && item.getValue().get(1).equals("crate")) {
                for (Crate c : existingCrates) {
                    if (c.getId().equals(id)) {
                        crateMap.put(c, beverageCount);
                    }
                }
            }
        }
        if (type.equals("bottle")) {
            return bottleMap;
        } else {
            return crateMap;
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

    @Override
    public Multimap<Long, List<String>> getSessionBasket() {
        return this.basket.getBasketItems();
    }

    @Override
    public void addItemToBasket(Long id, int number, String type) {
        this.basket.addItem(id, number, type);
    }

}
