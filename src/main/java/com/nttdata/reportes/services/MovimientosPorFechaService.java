package com.nttdata.reportes.services;

import com.nttdata.reportes.dto.MovimientosPorFecha;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface MovimientosPorFechaService {
    List<MovimientosPorFecha> listaMovimientosPorFechaCliente(List<String> params) throws ParseException;
}
