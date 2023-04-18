package com.nttdata.movimientos.entities;

import com.nttdata.movimientos.util.TipoMovimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String fecha;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoMovimiento tipo;

   @NotNull
    private Double valorMovimiento;

   @NotNull
    private Double saldoInicial;

}
