package com.nttdata.movimientos.controllers;

import com.nttdata.movimientos.entities.Movimiento;
import com.nttdata.movimientos.services.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.nttdata.util.Validaciones.excepciones;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService service;

    @GetMapping
    public List<Movimiento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Movimiento> movimientoOptional = service.detalle(id);
        if (movimientoOptional.isPresent()) {
            return ResponseEntity.ok(movimientoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Movimiento movimiento, BindingResult result) {

        if (result.hasErrors()) {
            return excepciones(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(movimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Movimiento movimiento, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return excepciones(result);
        }

        Optional<Movimiento> movimientoOptional = service.detalle(id);

        if (movimientoOptional.isPresent()) {
            Movimiento movimientoDB = (Movimiento) movimientoOptional.get();

            movimientoDB.setFecha(movimiento.getFecha());
            movimientoDB.setValorMovimiento(movimiento.getValorMovimiento());
            movimientoDB.setSaldoInicial(movimiento.getSaldoInicial());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(movimientoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Movimiento> movimientoOptional = service.detalle(id);
        if (movimientoOptional.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
