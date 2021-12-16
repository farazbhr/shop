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
        Bottle bottle = new Bottle();
        bottle.setBottlePic("https://wein.jpg");
        bottle.setAlcoholic(true);
        bottle.setId(12345L);
        bottle.setInStock(10);
        bottle.setName("wein");
        bottle.setSupplier("testsupplier");
        bottle.setVolume(5.0);
        bottle.setVolumePercent(5.0);
        bottle.setPrice(6);
        bottleRepository.save(bottle);

        Bottle bottle1 = new Bottle();
        bottle.setBottlePic("https://bier.jpg");
        bottle.setAlcoholic(true);
        bottle.setId(12343L);
        bottle.setInStock(10);
        bottle.setName("bier");
        bottle.setSupplier("testanbieter");
        bottle.setVolume(5.0);
        bottle.setVolumePercent(5.0);
        bottle.setPrice(6);
        bottleRepository.save(bottle);
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


