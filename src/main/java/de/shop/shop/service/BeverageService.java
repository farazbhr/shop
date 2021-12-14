package de.shop.shop.service;

import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;


public interface BeverageService {

    public abstract void addBottle(Bottle bottle);
    public abstract void addCrate( Crate crate);


    public Iterable<Bottle> getBeverages();

    public Iterable<Crate> getCrates();


}
