package com.nttdata.cuentas.services;

import com.nttdata.cuentas.entities.Cuenta;
import com.nttdata.cuentas.excepciones.SaldoInsuficienteException;
import com.nttdata.movimientos.entities.Movimiento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CuentaService {
    List<Cuenta> listar();
    Optional<Cuenta> detalle(Long id);
    Cuenta guardar(Cuenta cuenta);
    void eliminar(Long id);

    Optional<Cuenta> buscarPorNumero(String numero);

   Optional<Movimiento> asignarMovimiento(Movimiento movimiento, Long cuentaId) throws SaldoInsuficienteException;

}
