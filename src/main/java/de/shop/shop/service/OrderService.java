package de.shop.shop.service;

import de.shop.shop.model.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    public abstract List<?> getUnderlyingBeverages(Order order, String bottle);

    public abstract void storeOrder(Order order);

    public abstract List<Order> getStoredOrders();

    public abstract HashMap<Long, Integer> getSessionBasket();

    public abstract void addItemToBasket(Long id, int number);
}
