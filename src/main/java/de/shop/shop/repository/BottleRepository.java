package de.shop.shop.repository;

import de.shop.shop.model.Bottle;
import org.springframework.data.repository.CrudRepository;

public interface BottleRepository extends CrudRepository<Bottle,Long> {

    @Override
    Bottle save(Bottle bottle);

}
