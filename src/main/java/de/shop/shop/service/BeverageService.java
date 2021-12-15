package de.shop.shop.service;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;


public interface BeverageService {

    public abstract void addBeverage(Beverage beverage);
    public abstract  Iterable<Bottle> getBottles();



}
