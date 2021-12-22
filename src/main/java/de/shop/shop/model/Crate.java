package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Crate extends Beverage{

    @Positive(message="Number of Bottles must be > 0")
    private  int noOfBottles;

    @ManyToOne
    private Bottle bottle;

}
