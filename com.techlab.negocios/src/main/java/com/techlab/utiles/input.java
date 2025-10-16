package com.techlab.utiles;

import static com.techlab.app.Main.sc;

public class input {

    public static int inputLitros(){
        System.out.print("Ingresa Litros: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static int inputStock(){
        System.out.print("Ingresa Stock: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static int inputCantidad(){
        System.out.print("Ingresa Cantidad: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static double inputPrecio(){
        System.out.print("Ingresa Precio: ");
        return Double.parseDouble(sc.nextLine().trim());
    }

    public static String inputNombre(){
        System.out.print("Ingresa Nombre: ");
        return (sc.nextLine().trim());
    }

    public static String inputEmail(){
        System.out.print("Ingresa Email: ");
        return (sc.nextLine().trim());
    }

    public static void inputContinuar() {
        System.out.println("\nPresiona [ENTER] para continuar...\n");
        sc.nextLine();
    }

    public  static boolean inputConfirmar(String mensaje){
        System.out.println(mensaje + " s/n:");
        String respuesta = (sc.nextLine().trim());
        return  respuesta.equals("s");
    }

    public static int inputId(){
        System.out.print("Ingresa Id: ");
        return Integer.parseInt(sc.nextLine().trim());
    }
}
