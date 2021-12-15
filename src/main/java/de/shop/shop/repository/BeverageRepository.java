package de.shop.shop.repository;

import de.shop.shop.model.Beverage;

import de.shop.shop.model.Bottle;
import de.shop.shop.model.Crate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeverageRepository extends CrudRepository<Beverage,Long> {



}
