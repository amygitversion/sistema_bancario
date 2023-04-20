package com.nttdata.cuentas.entities;

import com.nttdata.cuentas.util.TipoCuenta;
import com.nttdata.movimientos.entities.Movimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;
    @NonNull
    private Double saldo;
    @NotNull
    @Column(columnDefinition = "BOOLEAN")
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuenta_id")
    private List<Movimiento> movimientos;


    public Cuenta() {
        movimientos=new ArrayList<>();
    }

    public <T> Cuenta(List<T> asList) {
    }

}
