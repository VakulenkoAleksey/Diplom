package jjd.db_project.repository;

import jjd.db_project.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    //    @Query("SELECT c FROM Client c WHERE c.phone = :phone")
//    Optional<Client> findByPhone(@Param("phone") String phone);
    Optional<Client> findByPhone(String phone);
}


