import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class App {

    private static void esperar(){
        try {
            System.out.println("cargando...");
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final String PIN_CORRECTO = "123";
        int intentos = 0;
        double saldo = 50000;
        boolean cuentaBloqueada = false;
        List<String> historialDeTransacciones = new ArrayList<>();

        while (true) {
            if (intentos < 3) {
                System.out.println("\u001B[34mIngrese su PIN: \u001B[0m");
                try {
                    String pinIngresado = scanner.next();

                    if (pinIngresado.equals(PIN_CORRECTO)) {
                        System.out.println("\u001B[32mBienvenido a la sucursal virtual...\u001B[0m");
                        break;
                    } else {
                        intentos++;
                        System.out.println("\u001B[31mEl PIN ingresado es incorrecto, intento número: " + intentos + "\u001B[0m");
                    }

                    if (intentos == 3) {
                        cuentaBloqueada = true;
                        System.out.println("\u001B[31mDemasiados intentos incorrectos, cuenta bloqueada, intente más tarde.\u001B[0m");

                        try {
                            Thread.sleep(60000); // Bloqueo temporal de 60 segundos
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        intentos = 0; // Reiniciar intentos después del bloqueo temporal
                        cuentaBloqueada = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\u001B[31mEntrada no válida. Por favor, ingrese un número.\u001B[0m");
                    scanner.next(); // Limpiar el buffer
                }
            }
        }

        // Menú del cajero
        int opcion;

        do {
            System.out.println("\u001B[34mBienvenido al Menú de su misero cajero.\u001B[0m");
            System.out.println("1. Consultar Balance.");
            System.out.println("2. Retirar.");
            System.out.println("3. Enviar Dinero.");
            System.out.println("4. Ver historial de transacciones.");
            System.out.println("5. Salir.");
            System.out.println("¿Qué quieres hacer chatarra?");

            //Comienzo el Try para control de excepciones en caso de que se ingrese un dato no valido 
            try {
                opcion = scanner.nextInt();
                esperar();

                switch (opcion) {
                    case 1:
                        System.out.println("\u001B[32mel saldo de actual de tu cuenta es de: $" + saldo + "\u001B[0m");
                        historialDeTransacciones.add("consulta de saldo: " + saldo);
                        esperar();
                        break;
                    case 2:
                        System.out.println("Ingrese el monto a retirar: ");
                        double cantRetiro = scanner.nextDouble();
                        esperar();
                        if (cantRetiro > 0 && cantRetiro <= saldo) {
                            saldo -= cantRetiro;
                            System.out.println("\u001B[32mRetiro exitoso, el monto retirado fue: " + cantRetiro + " y ahora su saldo es: " + saldo + "\u001B[0m");
                            historialDeTransacciones.add("Retiro de: " + cantRetiro + " y el saldo restante es: "+ saldo);
                            esperar();
                        } else {
                            System.out.println("\u001B[31mPorfavor deja de ser bruto, eso no lo puedes retirar.\u001B[0m");
                            historialDeTransacciones.add("transaccion fallida...");
                            esperar();
                        }
                        break;
                    case 3:
                        System.out.println("¿cuánto desea enviar?, no sea tacaño con la mamá ");
                        double cantDeposito = scanner.nextDouble();
                        esperar();

                        if (cantDeposito > 0 && cantDeposito <= saldo) {
                            System.out.println("¿A qué cuenta vas a mandar esa miseria?: ");
                            double cuentaDeposito = scanner.nextDouble();
                            System.out.println("\u001B[32mExcelente, le mandaste $" + cantDeposito + " a un pobre miserable " + cuentaDeposito + "\u001B[0m");
                            historialDeTransacciones.add("Transferencia de: " + cantDeposito +" a la cuenta No: " + cuentaDeposito);
                            esperar();
                        } else {
                            System.out.println("\u001B[31m¿Pero de dónde vas a mandar esa plata zopenco?\u001B[0m");
                            historialDeTransacciones.add("Transaccion invalida, Monto insuficiente..");
                            esperar();
                        }
                        break;
                    case 4:
                        System.out.println(historialDeTransacciones);
                        break;
                    case 5:
                        System.out.println("\u001B[32mGracias por nada :)\u001B[0m");
                        break;
                    default:
                        System.out.println("\u001B[31mDeja de ser tan incompetente, ni para elegir una opción servís\u001B[0m");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer
                opcion = -1; // Reiniciar la opción para que el bucle continúe
            }
        } while (opcion != 5);

        scanner.close();
    }
}