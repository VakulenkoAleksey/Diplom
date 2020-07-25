package jjd.db_project.repository;

import jjd.db_project.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.gosnumber = :gosNumber")
    Optional<Car> findByGosNumber(@Param("gosNumber") String gosNumber);
}
