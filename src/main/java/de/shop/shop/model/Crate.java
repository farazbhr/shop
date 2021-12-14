package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Crate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull(message="Name must be set")
    @NotEmpty(message="Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Name can only contain letters and digits")
    private String name;
    @Pattern(regexp = "(https:\\/\\/).*\\.(?:jpg|gif|png)", message="Must be valid URL to a picture")
    private String cratePic;
    @Positive(message="Number of Bottles must be > 0")
    private  int noOfBottles;
    @Positive(message="Price must be > 0")
    private double price;
    @PositiveOrZero(message="in Stock must be >= 0")
    private int cratesInStock;
    @ManyToOne
    private Bottle bottle;
}
