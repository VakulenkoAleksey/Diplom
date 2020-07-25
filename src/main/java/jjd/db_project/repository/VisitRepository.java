package jjd.db_project.repository;

import jjd.db_project.entity.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;


public interface VisitRepository extends CrudRepository<Visit, Integer> {
}
