package com.nttdata.reportes.services;

import com.nttdata.reportes.dto.MovimientosPorFecha;
import com.nttdata.reportes.repositories.MovimientoPorFechaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class MovimientosPorFechaServiceImpl implements MovimientosPorFechaService{
    @Autowired
    private MovimientoPorFechaRepository repository;


    @Override
    public List<MovimientosPorFecha> listaMovimientosPorFechaCliente(List<String> params) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio = LocalDate.parse(params.get(0), formatter);
        LocalDate fechaFin = LocalDate.parse(params.get(1), formatter);
        Long clienteId = Long.valueOf(params.get(2));

        return repository.listaMovimientosPorFechaCliente(fechaInicio,fechaFin,clienteId);
    }
}
