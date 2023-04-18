package com.nttdata.clientes.controllers;

import com.nttdata.clientes.entities.Cliente;
import com.nttdata.clientes.entities.Persona;
import com.nttdata.clientes.services.ClienteService;
import com.nttdata.cuentas.entities.Cuenta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.nttdata.util.Validaciones.excepciones;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Persona> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Persona> detalleOptional = service.detalle(id);
        if (detalleOptional.isPresent()) {
            return ResponseEntity.ok(detalleOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (!cliente.getIdentificacion().isEmpty() && service.buscarPorIdentificacion(cliente.getIdentificacion()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Identificacion", "Ya existe un cliente con esa identificacion"));
        }
        if (result.hasErrors()) {
            return excepciones(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return excepciones(result);
        }

        Optional<Persona> personaOptional = service.detalle(id);

        if (personaOptional.isPresent()) {
            Cliente clienteDB = (Cliente) personaOptional.get();

            if (!cliente.getIdentificacion().equalsIgnoreCase(clienteDB.getIdentificacion())
                    && service.buscarPorIdentificacion(cliente.getIdentificacion()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("Identificacion", "Ya existe un cliente con esa identificacion"));
            }

            clienteDB.setNombre(cliente.getNombre());
            clienteDB.setEdad(cliente.getEdad());
            clienteDB.setDireccion(cliente.getDireccion());
            clienteDB.setGenero(cliente.getGenero());
            clienteDB.setIdentificacion(cliente.getIdentificacion());
            clienteDB.setTelefono(cliente.getTelefono());
            clienteDB.setPassword(cliente.getPassword());
            clienteDB.setEstado(cliente.getEstado());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(clienteDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Persona> clienteOptional = service.detalle(id);
        if (clienteOptional.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-cuenta/{clienteId}")
    public ResponseEntity<?> asignarCuenta(@RequestBody Cuenta cuenta, @PathVariable Long clienteId) {
        Optional<Cuenta> cuentaOptional = service.asignarCuenta(cuenta, clienteId);

        if (cuentaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
