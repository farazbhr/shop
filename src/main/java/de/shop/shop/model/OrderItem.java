package de.shop.shop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
public class OrderItem   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message="Price must be > 0")
    private double price;
    // @Pattern(regexp = "^[0-9]*$", message = "Name can only contain letters and digits")
    private String position;
    private Long beverageId;

    @ManyToOne
    private ShopOrder shopOrder;

}
