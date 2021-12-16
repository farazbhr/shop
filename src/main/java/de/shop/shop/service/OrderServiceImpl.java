package de.shop.shop.service;

import de.shop.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }



}
