package controllers;

import models.Cuenta;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private List<Usuario> usuarios;

    public BancoController() {
        this.usuarios = new ArrayList<>();
    }

    public Usuario autenticarUsuario(String nombre, String pin) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getCuenta().getPin().equals(pin)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado o PIN incorrecto
    }

    public boolean registrarUsuario(String nombre, String numeroCuenta, String pin, double saldoInicial) {
        if (saldoInicial < 0) {
            return false; // Saldo inicial no puede ser negativo
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return false; // Usuario ya existe
            }
        }
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, saldoInicial, pin, 0);
        Usuario nuevoUsuario = new Usuario(nombre, nuevaCuenta);
        usuarios.add(nuevoUsuario);
        return true;
    }

    public double consultarSaldo(Usuario usuario) {
        return usuario.getCuenta().getSaldo();
    }

    public boolean retirar(Usuario usuario, double monto) {
        if (monto <= 0) {
            return false; // Monto inválido
        }
        Cuenta cuenta = usuario.getCuenta();
        if (monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuenta.registrarTransaccion("Retiro de: $" + monto);
            return true;
        }
        return false; // Saldo insuficiente
    }

    public boolean depositar(Usuario usuario, double monto) {
        if (monto <= 0) {
            return false; // Monto inválido
        }
        Cuenta cuenta = usuario.getCuenta();
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuenta.registrarTransaccion("Depósito de: $" + monto);
        return true;
    }

    public List<String> verHistorial(Usuario usuario) {
        return usuario.getCuenta().getHistorialDeTransacciones();
    }

    public boolean transferir(Usuario usuarioOrigen, String numeroCuentaDestino, double monto) {
        if (monto <= 0) {
            return false; // Monto inválido
        }

        Cuenta cuentaOrigen = usuarioOrigen.getCuenta();
        if (monto > cuentaOrigen.getSaldo()) {
            return false; // Saldo insuficiente
        }

        // Buscar la cuenta destino
        Usuario usuarioDestino = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCuenta().getNumeroCuenta().equals(numeroCuentaDestino)) {
                usuarioDestino = usuario;
                break;
            }
        }

        if (usuarioDestino == null) {
            return false; // Cuenta destino no encontrada
        }

        // Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaOrigen.registrarTransaccion("Transferencia enviada de: $" + monto + " a la cuenta " + numeroCuentaDestino);

        Cuenta cuentaDestino = usuarioDestino.getCuenta();
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        cuentaDestino.registrarTransaccion("Transferencia recibida de: $" + monto + " desde la cuenta " + cuentaOrigen.getNumeroCuenta());

        return true;
    }
}