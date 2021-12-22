package de.shop.shop.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.shop.shop.model.*;
import de.shop.shop.repository.BeverageRepository;
import de.shop.shop.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
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
    public Order createOrder(Multimap<Bottle, Integer> bottles, Multimap<Crate, Integer> crates) {

        Order order = new Order();
        List<OrderItem> itemList = new ArrayList<>();
        int posCounter =0;
        double totalPrice = 0;
        for(Map.Entry<Bottle, Integer> e : bottles.entries()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBeverageId(e.getKey().getId());
            orderItem.setBeverageCount(e.getValue());
            double itemPrice = e.getKey().getPrice() * e.getValue();
            orderItem.setPrice(itemPrice);
            totalPrice = totalPrice + itemPrice;
            orderItem.setPosition(String.valueOf(posCounter));
            posCounter++;
            itemList.add(orderItem);
        }
        for(Map.Entry<Crate, Integer> e : crates.entries()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBeverageId(e.getKey().getId());
            orderItem.setBeverageCount(e.getValue());
            double itemPrice = e.getKey().getPrice() * e.getValue();
            orderItem.setPrice(itemPrice);
            totalPrice = totalPrice + itemPrice;
            orderItem.setPosition(String.valueOf(posCounter));
            posCounter++;
            itemList.add(orderItem);
        }
        order.setOrderItemList(itemList);
        order.setPrice(totalPrice);
        return order;
    }

    public boolean decreaseStock(Multimap<Bottle, Integer> bottles, Multimap<Crate, Integer> crates){
        for(Map.Entry<Bottle, Integer> e : bottles.entries()){

            Bottle bottle = e.getKey();
            int oldStock = bottle.getInStock();
            int amountBasket = e.getValue();

            if(oldStock < amountBasket){
                return false;
            }
            else{
                bottle.setInStock(oldStock - amountBasket);
            }

        }

        for(Map.Entry<Crate, Integer> e : crates.entries()){

            Crate crate = e.getKey();
            int oldStock = crate.getInStock();
            int amountBasket = e.getValue();

            if(oldStock < amountBasket){
                return false;
            }
            else{
                crate.setInStock(oldStock - amountBasket);
            }
        }

        return true;
    }

    @Override
    public List<Order> getStoredOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public void storeOrder(Order order) {
        this.basket.setOrder(order);
    }

    @Override
    public void saveOrder(Order order) {
        this.orderRepository.save(this.basket.getOrder());
    }

    @Override
    public Multimap<Long, List<String>> getSessionBasket() {
        return this.basket.getBasketItems();
    }

    public Order getOrder() {
        return this.basket.getOrder();
    }

    @Override
    public void addItemToBasket(Long id, int number, String type) {
        this.basket.addItem(id, number, type);
    }

    @Override
    public void resetBasket() {
        this.basket.resetBasket();
    }
}
