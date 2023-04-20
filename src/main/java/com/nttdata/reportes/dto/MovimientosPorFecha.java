package com.nttdata.reportes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class MovimientosPorFecha {
    private Long id;
    private LocalDate fecha;
    private String nombreCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estadoCuenta;
    private Double montoMovimiento;
    private Double saldoDisponible;



}
