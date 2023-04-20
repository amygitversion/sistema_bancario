package com.nttdata.reportes.dto;

import java.time.LocalDate;

public interface MovimientosDTO {
    public LocalDate getFecha();

    public String getNombreCliente();

    public String getNumeroCuenta();

    public String getTipoCuenta();

    public Double getSaldoInicial();

    public Boolean getEstadoCuenta();

    public Double getMontoMovimiento();

    public Double getSaldoDisponible();
}
