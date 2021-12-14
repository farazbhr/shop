package de.shop.shop.service;

import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BottleRepository;
import de.shop.shop.repository.CrateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BeverageServiceImpl  implements BeverageService {
    private final BottleRepository beverageRepository;
    private final CrateRepository crateRepository;

    @Autowired
    public BeverageServiceImpl(BottleRepository bottleRepository, CrateRepository crateRepository) {
        this.beverageRepository = bottleRepository;
        this.crateRepository = crateRepository;
    }


    @Override
    public void addBottle(Bottle bottle) {
        this.beverageRepository.save(bottle);
    }

    @Override
    public void addCrate(Crate crate) {
        this.crateRepository.save(crate);
    }

    @Override
    public Iterable<Bottle> getBeverages() {
        return this.beverageRepository.findAll();
    }

    @Override
    public Iterable<Crate> getCrates() {
        return this.crateRepository.findAll();
    }


}


