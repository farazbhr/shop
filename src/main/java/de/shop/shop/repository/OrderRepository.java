package de.shop.shop.repository;

import de.shop.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAll();

    List<Order> findByPrice(double price);
}
