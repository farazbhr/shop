package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message="Price must be > 0")
    private double price;

    @Pattern(regexp = "^[0-9]*$", message = "Position can only digits")
    private String position;

    @NotNull
    private Long beverageId;

    @NotNull
    private Integer beverageCount;

}
