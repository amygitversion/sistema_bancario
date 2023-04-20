package com.nttdata.clientes.services;

import com.nttdata.clientes.entities.Cliente;
import com.nttdata.clientes.entities.Persona;
import com.nttdata.clientes.repositories.PersonaRepository;
import com.nttdata.cuentas.entities.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private PersonaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listar() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> detalle(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente guardar(Persona persona) {
        return (Cliente) repository.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Persona> buscarPorIdentificacion(String identificacion) {
        return repository.findByIdentificacion(identificacion);
    }

    @Override
    @Transactional
    public Optional<Cuenta> asignarCuenta(Cuenta cuenta, Long clienteId) {
        Optional<Persona> personaOptional = repository.findById(clienteId);
        if (personaOptional.isPresent()) {
            Cliente cliente = (Cliente) personaOptional.get();
            cliente.getCuentas().add(cuenta);
            repository.save(cliente);
            return Optional.of(cuenta);
        }
        return Optional.empty();
    }
}
