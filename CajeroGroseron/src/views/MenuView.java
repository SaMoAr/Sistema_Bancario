package views;

import controllers.BancoController;
import java.util.Scanner;
import models.Usuario;

public class MenuView {
    private BancoController bancoController;
    private Scanner scanner;

    public MenuView(BancoController bancoController) {
        this.bancoController = bancoController;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        Usuario usuarioActual = null;
        while (usuarioActual == null) {
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    AuthView authView = new AuthView(bancoController);
                    usuarioActual = authView.iniciarSesion();
                    break;
                case 2:
                    RegisterView registerView = new RegisterView(bancoController);
                    registerView.registrarUsuario();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema bancario.");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        mostrarMenuUsuario(usuarioActual);
    }

    public void mostrarMenuUsuario(Usuario usuario) {
        int opcion;
        do {
            System.out.println("1. Consultar Balance");
            System.out.println("2. Retirar");
            System.out.println("3. Depositar");
            System.out.println("4. Ver Historial");
            System.out.println("5. Transferir");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    double saldo = bancoController.consultarSaldo(usuario);
                    System.out.println("Saldo actual: $" + saldo);
                    break;
                case 2:
                    System.out.print("Ingrese el monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();
                    if (bancoController.retirar(usuario, montoRetiro)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Saldo insuficiente o monto inválido.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();
                    if (bancoController.depositar(usuario, montoDeposito)) {
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("Monto inválido.");
                    }
                    break;
                case 4:
                    System.out.println("Historial de transacciones:");
                    for (String transaccion : bancoController.verHistorial(usuario)) {
                        System.out.println(transaccion);
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el número de cuenta destino: ");
                    String numeroCuentaDestino = scanner.nextLine();
                    System.out.print("Ingrese el monto a transferir: ");
                    double montoTransferencia = scanner.nextDouble();
                    if (bancoController.transferir(usuario, numeroCuentaDestino, montoTransferencia)) {
                        System.out.println("Transferencia realizada exitosamente.");
                    } else {
                        System.out.println("Error en la transferencia. Verifique los datos e intente nuevamente.");
                    }
                    break;
                case 6:
                    mostrarSubmenuSalir();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void mostrarSubmenuSalir() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Salir al menú principal");
        System.out.println("2. Salir del sistema");
        System.out.print("Seleccione una opción: ");
        int opcionSalir = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcionSalir) {
            case 1:
                System.out.println("Regresando al menú principal...");
                mostrarMenuPrincipal(); // Volver al menú principal
                break;
            case 2:
                System.out.println("Gracias por usar el sistema bancario. ¡Hasta luego!");
                System.exit(0); // Salir del sistema
                break;
            default:
                System.out.println("Opción inválida. Regresando al menú principal...");
                mostrarMenuPrincipal(); // Volver al menú principal por defecto
                break;
        }
    }
}