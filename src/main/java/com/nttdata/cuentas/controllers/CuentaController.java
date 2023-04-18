package com.nttdata.cuentas.controllers;


import com.nttdata.cuentas.entities.Cuenta;
import com.nttdata.cuentas.services.CuentaService;
import com.nttdata.movimientos.entities.Movimiento;
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
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService service;

    @GetMapping
    public List<Cuenta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cuenta> cuentaOptional = service.detalle(id);
        if (cuentaOptional.isPresent()) {
            return ResponseEntity.ok(cuentaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<?> crear(@Valid @RequestBody Cuenta cuenta, BindingResult result, @PathVariable Long clienteId) {
        if (!cuenta.getNumero().isEmpty() && service.buscarPorNumero(cuenta.getNumero()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Numero", "Ya existe una cuenta con ese numero"));
        }
        if (result.hasErrors()) {
            return excepciones(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Cuenta cuenta, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return excepciones(result);
        }

        Optional<Cuenta> cuentaOptional = service.detalle(id);

        if (cuentaOptional.isPresent()) {
            Cuenta cuentaDB = (Cuenta) cuentaOptional.get();

            if (!cuenta.getNumero().equalsIgnoreCase(cuentaDB.getNumero())
                    && service.buscarPorNumero(cuenta.getNumero()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("Numero", "Ya existe una cuenta con ese numero"));
            }

            cuentaDB.setNumero(cuenta.getNumero());
            cuentaDB.setTipo(cuenta.getTipo());
            cuentaDB.setSaldo(cuenta.getSaldo());
            cuentaDB.setEstado(cuenta.getEstado());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cuentaDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Cuenta> cuentaOptional = service.detalle(id);
        if (cuentaOptional.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-movimiento/{cuentaId}")
    public ResponseEntity<?> asignarMovimiento(@RequestBody Movimiento movimiento, @PathVariable Long cuentaId) {
        Optional<Movimiento> movimientoOptional = service.asignarMovimiento(movimiento, cuentaId);

        if (movimientoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movimientoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
