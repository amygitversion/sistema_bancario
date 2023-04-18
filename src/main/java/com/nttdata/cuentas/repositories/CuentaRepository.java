package com.nttdata.cuentas.repositories;

import com.nttdata.cuentas.entities.Cuenta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumero(String numero);

}
