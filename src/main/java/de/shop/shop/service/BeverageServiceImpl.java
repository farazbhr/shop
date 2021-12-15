package de.shop.shop.service;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import java.util.List;


@Service
public class BeverageServiceImpl  implements BeverageService {

    private final BeverageRepository beverageRepository;




    @Autowired
    public BeverageServiceImpl(BeverageRepository bottleRepository) {
        this.beverageRepository = bottleRepository;
    }


    @Override
    public void addBeverage(Beverage beverage) {
        this.beverageRepository.save(beverage);
    }



    @Override
    public Iterable<Bottle> getBottles() {
        return this.beverageRepository.findAll();
    }


}


