package de.shop.shop.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

@Data
@MappedSuperclass
public class Beverage {

    // Beverage will not be present in the database -> no table beverage
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Name must be set")
    @NotEmpty(message="Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9äÄöÖüÜß ]*$", message = "Name can only contain letters and digits")
    private String name;

    @Pattern(regexp = "(https:\\/\\/).*\\.(?:jpg|gif|png)", message="Must be valid URL to a picture")
    private String picture;

    @Positive(message="Price must be > 0")
    private double price;

    @PositiveOrZero(message="in Stock must be >= 0")
    private int inStock;

}
