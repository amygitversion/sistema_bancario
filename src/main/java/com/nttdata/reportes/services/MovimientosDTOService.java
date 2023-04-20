package com.nttdata.reportes.services;

import com.nttdata.reportes.dto.MovimientosDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Service
public interface MovimientosDTOService {
    List<MovimientosDTO> listaMovimientosPorFechaCliente(String fechaInicio, String fechaFin, String clienteId) throws ParseException;
}
