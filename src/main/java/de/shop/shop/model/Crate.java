package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Crate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
  //  @NotNull
  // @NotEmpty
  // @Pattern(regexp = "[^A-Za-z0-9]", message = "No valid name")
    private String name;
    // @Pattern(regexp = "(https:\\\\/\\\\/).*\\\\.(?:jpg|gif|png)\"")
    private String cratePic;
    // @Min(1)
    private  int noOfBottles;
    // @DecimalMin("0.01")
    private double price;
    // @Min(1)
    private int cratesInStock;
}
