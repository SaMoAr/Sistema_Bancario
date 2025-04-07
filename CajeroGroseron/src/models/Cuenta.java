package models;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private String pin;
    private int intentosFallidos;
    private List<String> historialDeTransacciones;
    private TiposCuentas tipoCuenta; //Atributo para el tipo de cuenta desde el enum 

    public Cuenta(String numeroCuenta, double saldo, String pin, int intentosFallidos,TiposCuentas tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.pin = pin;
        this.intentosFallidos = intentosFallidos;
        this.historialDeTransacciones = new ArrayList<>();
        this.tipoCuenta = tipoCuenta; 
    }

    public TiposCuentas getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TiposCuentas tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public List<String> getHistorialDeTransacciones() {
        return historialDeTransacciones;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void registrarTransaccion(String transaccion) {
        historialDeTransacciones.add(transaccion);
    }
}
