package com.nttdata.reportes.controllers;

import com.nttdata.reportes.dto.MovimientosDTO;
import com.nttdata.reportes.services.MovimientosDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class MovimientosDTOController {
    @Autowired
    private MovimientosDTOService service;

    @GetMapping
    public ResponseEntity<?> listarMovimientosPorFecha(@RequestParam String fechaInicio,@RequestParam String fechaFin,@RequestParam String clienteId ) {

        List<MovimientosDTO> movimientosPorFechas = null;
        try {
            movimientosPorFechas = service.listaMovimientosPorFechaCliente(fechaInicio,fechaFin,clienteId);
        } catch (ParseException e) {
            ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Error Fecha Formato:dd/MM/yyyy", e.getMessage()));
        }
        return ResponseEntity.ok(movimientosPorFechas);
    }
}
