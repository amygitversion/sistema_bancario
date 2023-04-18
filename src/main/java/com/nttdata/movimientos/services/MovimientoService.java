package com.nttdata.movimientos.services;

import com.nttdata.movimientos.entities.Movimiento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovimientoService {
    List<Movimiento> listar();
    Optional<Movimiento> detalle(Long id);
    Movimiento guardar(Movimiento cuenta);
    void eliminar(Long id);
}
