package com.nttdata.clientes.repositories;

import com.nttdata.clientes.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Long> {
    Optional<Persona> findByIdentificacion(String identificacion);
}
