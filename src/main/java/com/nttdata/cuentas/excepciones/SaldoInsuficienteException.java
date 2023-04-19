package com.nttdata.cuentas.excepciones;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException() {
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
