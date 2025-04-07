package views;

import controllers.BancoController;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.TiposCuentas;

public class RegisterView {
    private BancoController bancoController;
    private Scanner scanner;

    public RegisterView(BancoController bancoController) {
        this.bancoController = bancoController;
        this.scanner = new Scanner(System.in);
    }

    public void registrarUsuario() {
        try {
            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el tipo de cuenta (1.AHORRO, 2.CORRIENTE, 3.EMPRESARIAL): ");
            int OpcionTipoCuenta = scanner.nextInt();
            TiposCuentas tipoCuenta; 

            switch (OpcionTipoCuenta) {
                case 1:
                    tipoCuenta = TiposCuentas.AHORRO; 
                    break;
                case 2:
                    tipoCuenta = TiposCuentas.CORRIENTE; 
                    break;
                case 3:
                    tipoCuenta = TiposCuentas.EMPRESARIAL;
                    break;
                default:
                    throw new AssertionError("Opción inválida: " + OpcionTipoCuenta); 
            }

            System.out.print("Ingrese un número de cuenta: ");
            scanner.nextLine(); 
            String numeroCuenta = scanner.nextLine();
            System.out.print("Ingrese un PIN: ");
            String pin = scanner.nextLine();
            System.out.print("Ingrese el saldo inicial: ");
            double saldoInicial = scanner.nextDouble();
            scanner.nextLine(); 

            if (saldoInicial < 0) {
                System.out.println("El saldo inicial no puede ser negativo.");
                return;
            }

            if (bancoController.registrarUsuario(nombre, numeroCuenta, pin, saldoInicial, tipoCuenta)) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("El usuario ya existe o los datos son inválidos.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número válido para el saldo inicial.");
            scanner.nextLine(); 
        }
    }
}