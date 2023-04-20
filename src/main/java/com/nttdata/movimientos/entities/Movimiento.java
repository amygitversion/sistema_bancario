package com.nttdata.movimientos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.movimientos.util.TipoMovimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @NotNull
   @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoMovimiento tipo;

   @NotNull
    private Double valorMovimiento;

   @NotNull
    private Double saldoInicial;

}
