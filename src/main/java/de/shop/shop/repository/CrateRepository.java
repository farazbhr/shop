package de.shop.shop.repository;

import de.shop.shop.model.Crate;
import org.springframework.data.repository.CrudRepository;


public interface CrateRepository extends CrudRepository<Crate,Long> {

    @Override
    Crate save(Crate bottle);

}