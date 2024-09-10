package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Celular;

public interface CelularRepository extends CrudRepository<Celular, Long> {
    
}