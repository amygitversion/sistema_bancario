package com.nttdata.reportes.controllers;

import com.nttdata.movimientos.services.MovimientoService;
import com.nttdata.reportes.dto.MovimientosPorFecha;
import com.nttdata.reportes.services.MovimientosPorFechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class MovimientosPorFechaController {
    @Autowired
    private MovimientosPorFechaService service;

    @GetMapping
    public ResponseEntity<?> listarMovimientosPorFecha(@RequestParam List<String> params) {

        List<MovimientosPorFecha> movimientosPorFechas = null;
        try {
            movimientosPorFechas = service.listaMovimientosPorFechaCliente(params);
        } catch (ParseException e) {
            ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Error Fecha Formato:dd/MM/yyyy", e.getMessage()));
        }
        return ResponseEntity.ok(movimientosPorFechas);
    }
}
