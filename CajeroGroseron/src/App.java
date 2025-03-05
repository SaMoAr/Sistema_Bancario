import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final String PIN_CORRECTO = "123";
    private static int intentos = 0;
    private static double saldo = 50000;
    private static boolean cuentaBloqueada = false;
    private static List<String> historialDeTransacciones = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        autenticarUsuario();
        mostrarMenu();
        scanner.close();
    }

    private static void autenticarUsuario() {
        while (intentos < 3) {
            System.out.println("\u001B[34mIngrese su PIN: \u001B[0m");
            String pinIngresado = scanner.next();
            if (pinIngresado.equals(PIN_CORRECTO)) {
                System.out.println("\u001B[32mBienvenido a la sucursal virtual...\u001B[0m");
                return;
            } else {
                intentos++;
                System.out.println("\u001B[31mEl PIN ingresado es incorrecto, intento número: " + intentos + "\u001B[0m");
            }
            if (intentos == 3) {
                cuentaBloqueada = true;
                System.out.println("\u001B[31mDemasiados intentos incorrectos, cuenta bloqueada, intente más tarde.\u001B[0m");
                esperar(60000);
                intentos = 0;
                cuentaBloqueada = false;
            }
        }
    }

    private static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\u001B[34mBienvenido al Menú de su misero cajero.\u001B[0m");
            System.out.println("1. Consultar Balance.");
            System.out.println("2. Retirar.");
            System.out.println("3. Enviar Dinero.");
            System.out.println("4. Ver historial de transacciones.");
            System.out.println("5. Salir.");
            System.out.println("¿Qué quieres hacer chatarra?");
            try {
                opcion = scanner.nextInt();
                esperar(1500);
                procesarOpcion(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Entrada invaldida bobita, ingresa números. ");
                scanner.next(); // Limpiar el buffer
                opcion = -1; // Reiniciar la opción para que el bucle continúe
            }
        } while (opcion != 5);
    }

    private static void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1:
                    consultarBalance();
                    break;
                case 2:
                    retirar();
                    break;
                case 3:
                    enviarDinero();
                    break;
                case 4:
                    verHistorial();
                    break;
                case 5:
                    System.out.println("\u001B[32mGracias por nada :)\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31mDeja de ser tan incompetente, ni para elegir una opción servís\u001B[0m");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida tontin, ingresa un número.");
            scanner.next(); // Limpiar el buffer
        }
    }

    private static void consultarBalance() {
        System.out.println("\u001B[32mel saldo de actual de tu cuenta es de: $" + saldo + "\u001B[0m");
        historialDeTransacciones.add("consulta de saldo: " + saldo);
    }

    private static void retirar() {
        try {
            System.out.println("Ingrese el monto a retirar: ");
            double cantRetiro = scanner.nextDouble();
            esperar(1500);
            if (cantRetiro > 0 && cantRetiro <= saldo) {
                saldo -= cantRetiro;
                System.out.println("\u001B[32mRetiro exitoso, el monto retirado fue: " + cantRetiro + " y ahora su saldo es: " + saldo + "\u001B[0m");
                historialDeTransacciones.add("Retiro de: " + cantRetiro + " y el saldo restante es: " + saldo);
            } else {
                System.out.println("\u001B[31mPorfavor deja de ser bruto, eso no lo puedes retirar.\u001B[0m");
                historialDeTransacciones.add("transaccion fallida...");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida, ya estas muy petardo. ");
            scanner.next(); // Limpiar el buffer
        }
    }

    private static void enviarDinero() {
        try {
            System.out.println("¿cuánto desea enviar?, no sea tacaño con la mamá ");
            double cantDeposito = scanner.nextDouble();
            esperar(1500);
            if (cantDeposito > 0 && cantDeposito <= saldo) {
                System.out.println("¿A qué cuenta vas a mandar esa miseria?: ");
                double cuentaDeposito = scanner.nextDouble();
                System.out.println("\u001B[32mExcelente, le mandaste $" + cantDeposito + " a un pobre miserable " + cuentaDeposito + "\u001B[0m");
                historialDeTransacciones.add("Transferencia de: " + cantDeposito + " a la cuenta No: " + cuentaDeposito);
            } else {
                System.out.println("\u001B[31m¿Pero de dónde vas a mandar esa plata zopenco?\u001B[0m");
                historialDeTransacciones.add("Transaccion invalida, Monto insuficiente..");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ya me cansé de tus errores. ");
            scanner.next(); // Limpiar el buffer
        }
    }

    private static void verHistorial() {
        System.out.println(historialDeTransacciones);
    }

    private static void esperar(int milisegundos) {
        try {
            System.out.println("cargando...");
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}