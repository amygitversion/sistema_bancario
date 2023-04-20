package com.nttdata.reportes.services;

import com.nttdata.reportes.dto.MovimientosDTO;
import com.nttdata.reportes.repositories.MovimientosDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MovimientosDTOServiceImpl implements MovimientosDTOService {
    @Autowired
    private MovimientosDTORepository repository;


    @Override
    public List<MovimientosDTO> listaMovimientosPorFechaCliente(String fechaInicio, String fechaFin, String clienteId) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);
        Long id = Long.valueOf(clienteId);

        return repository.listaMovimientosPorFechaCliente(fechaInicial,fechaFinal,id);
    }
}
