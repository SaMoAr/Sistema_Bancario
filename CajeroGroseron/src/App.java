import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //Declaración de variables y constantes
        final int pin_correcto = 123;
        int intentos = 0;
        double saldo = 50000;
        boolean cuenta_bloqueada = false;

        //autenticación segun intentos
        while(intentos < 3){
            System.out.println("ingrese su PIN: ");
            int pin_ingresado = scanner.nextInt();
            
            if(pin_ingresado == pin_correcto){
                System.out.println("Bienvenido a la sucursal virtual...");
                break;
            }else{
                intentos ++;
                System.out.println("El pin Ingresado es incorrecto, intento numero:  " + intentos);
            }

            if(intentos == 3){
                cuenta_bloqueada = true;
                System.out.println("Demasiados intentos incorrectos, cuenta bloqueada.");
            }
        }
        //si la cuenta esta bloqueada, se finaliza el programa.
        if(cuenta_bloqueada){
            scanner.close();
            return;
        }

        //Menú del cajero
        int opcion;

        do {
            System.out.println("Bienvenido al Menú de su malp*** cajero. ");
            System.out.println("1.Consultar chichigua. ");
            System.out.println("2.Retirar menuda. ");
            System.out.println("3.Mandar lana. ");
            System.out.println("4.abraham. ");
            System.out.println("Que quieres hacer chatarra? ");
            
            opcion = scanner.nextInt();

            // Manejo de opciones.
            switch (opcion) {
                case 1:
                System.out.println("ay mano, tan solo tienes: "+ saldo);
                    break;


                case 2:
                System.out.println("Cuanto necesita?: ");
                double cant_Retiro = scanner.nextDouble();

                if(cant_Retiro >0 && cant_Retiro <= saldo){

                    saldo -= cant_Retiro;
                    System.out.println("melo, retiraste "+ cant_Retiro +" y ahora tu saldo es: "+ saldo);
                }else{
                    System.out.println("deja de ser bruto ome, eso no lo podes retirar. ");
                }
                    break;

                case 3:
                System.out.println("Si va a mandar bastante, cuanto??? ");
                double cant_deposito =scanner.nextDouble();

                if(cant_deposito>0 && cant_deposito <= saldo){
                    System.out.println("a que cuenta le vas a mandar esa miseria?: ");
                    double cuenta_deposito = scanner.nextDouble();

                    System.out.println("melos, le mandaste "+ cant_deposito +" a la cuenta" + cuenta_deposito);

                }else{
                    System.out.println("pero de donde vas a mandar esa plata zopenco?? ");
                }
                    break;

    
                case 4:
                System.out.println("Abrite ome zunga");
                    break;
            
                default:
                System.out.println("Deja de ser tan tapado gono** ni para elejir una misera opción servís");
                    break;
            }
        } while (opcion !=4);
        scanner.close();

       
    }
}
