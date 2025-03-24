package views;

import controllers.BancoController;
import models.Usuario;

import java.util.Scanner;

public class AuthView {
    private BancoController bancoController;
    private Scanner scanner;

    public AuthView(BancoController bancoController) {
        this.bancoController = bancoController;
        this.scanner = new Scanner(System.in);
    }

    public Usuario iniciarSesion() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingrese su PIN: ");
        String pin = scanner.nextLine().trim();

        if (nombre.isEmpty() || pin.isEmpty()) {
            System.out.println("El nombre y el PIN no pueden estar vacíos.");
            return null;
        }

        Usuario usuario = bancoController.autenticarUsuario(nombre, pin);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso.");
            return usuario;
        } else {
            System.out.println("Nombre o PIN incorrecto. Por favor, intente nuevamente.");
            return null;
        }
    }
}