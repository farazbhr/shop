package de.shop.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity()
@Table(name="ShopOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message="Price must be > 0")
    private double price;

    @ManyToOne//(cascade=CascadeType.ALL)
    @NotNull
    @NotEmpty
    private List<OrderItem> orderItemList;

}
