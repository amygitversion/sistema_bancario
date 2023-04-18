package com.nttdata.movimientos.repositories;

import com.nttdata.movimientos.entities.Movimiento;
import org.springframework.data.repository.CrudRepository;

public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {
}
