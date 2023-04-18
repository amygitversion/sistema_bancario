package com.nttdata.cuentas.services;


import com.nttdata.cuentas.entities.Cuenta;
import com.nttdata.cuentas.repositories.CuentaRepository;
import com.nttdata.movimientos.entities.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> listar() {
        return (List<Cuenta>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> detalle(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cuenta guardar(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> buscarPorNumero(String numero) {
        return repository.findByNumero(numero);
    }

    @Override
    @Transactional
    public Optional<Movimiento> asignarMovimiento(Movimiento movimiento, Long cuentaId) {
        Optional<Cuenta> cuentaOptional = repository.findById(cuentaId);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            if (movimiento.getValorMovimiento() > 0
                    | (movimiento.getValorMovimiento() < 0 && cuenta.getSaldo() >= Math.abs(movimiento.getValorMovimiento()))) {
                cuenta.setSaldo(cuenta.getSaldo() + movimiento.getValorMovimiento());
            } else {
                return Optional.empty();
            }

            cuenta.getMovimientos().add(movimiento);
            repository.save(cuenta);
            return Optional.of(movimiento);
        }
        return Optional.empty();
    }
}
