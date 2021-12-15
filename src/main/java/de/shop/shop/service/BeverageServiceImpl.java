package de.shop.shop.service;

import de.shop.shop.model.Beverage;
import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import de.shop.shop.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.OneToOne;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.LinkedList;
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

    public List<Bottle> getBottles(){
        return entityManager.createQuery("SELECT b from Bottle b").getResultList();
    }

    public List<Crate> getCrates(){
       return entityManager.createQuery("SELECT c from Crate c").getResultList();
    }

    @Override
    public void addBeverage(Beverage beverage) {
        this.beverageRepository.save(beverage);
    }

}


