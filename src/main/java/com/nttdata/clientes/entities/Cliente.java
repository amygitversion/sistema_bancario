package com.nttdata.clientes.entities;

import com.nttdata.cuentas.entities.Cuenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Cliente extends Persona {

    @NotBlank
    private String password;
    @NotNull
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Cuenta> cuentas;

    public Cliente(){
        cuentas=new ArrayList<>();
    }

}
