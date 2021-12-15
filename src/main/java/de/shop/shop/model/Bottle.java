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
public class Bottle extends Beverage{

    @Positive(message="Volume must be > 0")
    private double volume;

    private boolean isAlcoholic;

    @PositiveOrZero(message="VolumePercent must be >= 0")
    private double volumePercent;

    @NotEmpty(message="Supplier must be set")
    @NotNull(message="Supplier cannot be empty")
    private String supplier;

}
