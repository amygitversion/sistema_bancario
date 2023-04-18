package com.nttdata.clientes.services;

import com.nttdata.clientes.entities.Persona;
import com.nttdata.cuentas.entities.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClienteService {

    List<Persona> listar();
    Optional<Persona> detalle(Long id);
    Persona guardar(Persona persona);
    void eliminar(Long id);

    Optional<Persona> buscarPorIdentificacion(String identificacion);
    Optional<Cuenta> asignarCuenta(Cuenta cuenta, Long clienteId);

}
