package de.shop.shop.repository;

import de.shop.shop.model.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Bottle,Long> {
}
