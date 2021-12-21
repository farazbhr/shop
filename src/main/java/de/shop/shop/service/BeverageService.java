package de.shop.shop.service;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;

import java.util.List;


public interface BeverageService {

    public abstract void addBeverage(Beverage beverage);
    public abstract List<Bottle> getBottles();
    public abstract List<Crate> getCrates();

}
