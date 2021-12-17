package de.shop.shop.service;

import de.shop.shop.model.Order;

import java.util.List;

public interface OrderService {

    List<?> getUnderlyingBeverages(Order order, String bottle);

    void storeOrder(Order order);
}
