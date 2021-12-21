package de.shop.shop.service;

import com.google.common.collect.Multimap;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.model.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {


    Multimap<?, Integer> getUnderlyingBeverages(Multimap<Long, List<String>> hashMap, List<Bottle> existingBottles, List<Crate> existingCrates, String type);

    public abstract void storeOrder(Order order);

    public abstract List<Order> getStoredOrders();

    public abstract Multimap<Long, List<String>> getSessionBasket();

    public abstract void addItemToBasket(Long id, int number, String type);
}
