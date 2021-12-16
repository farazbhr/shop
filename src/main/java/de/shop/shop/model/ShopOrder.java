package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message="Price must be > 0")
    private double price;


    @OneToMany(mappedBy = "shopOrder")
    private List<OrderItem> orderItems ;


}
