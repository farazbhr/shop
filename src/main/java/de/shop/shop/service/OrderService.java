package de.shop.shop.service;

import com.google.common.collect.Multimap;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.model.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {


    Multimap<?, Integer> getUnderlyingBeverages(Multimap<Long, List<String>> hashMap, List<Bottle> existingBottles, List<Crate> existingCrates, String type);

    public abstract Order createOrder(Multimap<Bottle, Integer> bottles, Multimap<Crate, Integer> crates);

    public abstract List<Order> getStoredOrders();

    public void storeOrder(Order order);

    public abstract Multimap<Long, List<String>> getSessionBasket();

    public abstract void addItemToBasket(Long id, int number, String type);

    public void saveOrder(Order order);

    public Order getOrder();

    void resetBasket();
}
