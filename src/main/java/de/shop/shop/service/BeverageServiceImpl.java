package de.shop.shop.service;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;



@Service
public class BeverageServiceImpl  implements BeverageService {

    private final BeverageRepository beverageRepository;
    private final EntityManager entityManager;

    @Autowired
    public BeverageServiceImpl(BeverageRepository beverageRepository, EntityManager entityManager) {
        this.beverageRepository = beverageRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Bottle> getBottles(){
        return entityManager.createQuery("SELECT b from Bottle b").getResultList();
    }
    @Override
    public List<Crate> getCrates(){
       return entityManager.createQuery("SELECT c from Crate c").getResultList();
    }

    @Override
    public void addBeverage(Beverage beverage) {
        this.beverageRepository.save(beverage);
    }

}


