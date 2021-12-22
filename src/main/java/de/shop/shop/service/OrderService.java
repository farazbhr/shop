package de.shop.shop.service;

import com.google.common.collect.Multimap;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.model.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {


    Multimap<?, Integer> getUnderlyingBeverages(Multimap<Long, List<String>> hashMap, List<Bottle> existingBottles, List<Crate> existingCrates, String type);

    Order createOrder(Multimap<Bottle, Integer> bottles, Multimap<Crate, Integer> crates);

    List<Order> getStoredOrders();

    void storeOrder(Order order);

    Multimap<Long, List<String>> getSessionBasket();

    void addItemToBasket(Long id, int number, String type);

   void saveOrder(Order order);

    Order getOrder();

   void resetBasket();

    void decreaseStock(Multimap<Bottle, Integer> bottles, Multimap<Crate, Integer> crates);
}
