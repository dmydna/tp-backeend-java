package com.input;

import static com.Main.sc;

public class input {

    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    /** ANSI COLORS */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /** ERROR FORMATO */
    public static final String ERROR_FLOAT_FORMATO = ("❌ ERROR: Ingresa solo números decimales válidos.");
    public static final String ERROR_FORMATO  = ("❌ ERROR: Ingresa solo números enteros válidos.");
    public static final String ERROR_EMAIL_FORMATO = ("❌ ERROR: Ingresa un formato de email válido (ej: usuario@dominio.com).");

    /** ERROR */
    public static final String ERROR_STOCK = ("⚠️ ERROR: Stock insuficiente");
    public static final String ERROR_PRODUCTO = ("⚠️ ERROR: Producto no encontrado.");
    public static final String ERROR_POSITIVO = ("⚠️ ERROR: Debe ser un número entero positivo." );
    public static final String ERROR_FLOAT_POSITIVO = ("⚠️ ERROR: Debe ser un número decimal positivo." );

    /** MENSAJES */
    public static final String MSG_STOCK = "Ingresa Stock: ";
    public static final String MSG_CANTIDAD = "Ingresa Cantidad: ";
    public static final String MSG_ID = "Ingresa ID: ";
    public static final String MSG_NOMBRE = "Ingresa Nombre: ";
    public static final String MSG_EMAIL = "Ingresa Email: ";
    public static final String ERROR_VACIO = ("⚠️ ERROR: Este campo no puede estar vacío.");


    /** PRINT COLORS */

    public static void printWarning(String mensaje){
        System.out.println("\n" + ANSI_RED + mensaje + ANSI_RESET);
    }

    public static void printSuccess(String mensaje){
        System.out.println("\n" + ANSI_GREEN + mensaje + ANSI_RESET);
    }

    public static void printInfo(String mensaje){
        System.out.println("\n" + ANSI_BLUE + mensaje + ANSI_RESET);
    }

    public static void printAttention(String mensaje){
        System.out.println("\n" + ANSI_YELLOW + mensaje + ANSI_RESET);
    }



    /** INPUT TRY */

    public static int inputIntegerPositivo(String MSG, String MSG_Error, String MSG_Formato) {
        int valor = -1;
        boolean entradaValida = false;
        do {
            System.out.print(MSG);
            String entrada = sc.nextLine().trim();

            try {
                valor = Integer.parseInt(entrada);
                if (valor >= 0) {
                    entradaValida = true;
                } else {
                    printAttention(MSG_Error);
                }
            } catch (NumberFormatException e) {
                printAttention(MSG_Formato);
            }
        } while (!entradaValida);
        return valor;
    }


    public static double inputFloatPositivo(String MSG, String MSG_Error, String MSG_Formato) {
        double valor = -1;
        boolean entradaValida = false;

        do {
            System.out.print(MSG);
            String entrada = sc.nextLine().trim();

            try {
                valor = Double.parseDouble(entrada);
                if (valor > 0) {
                    entradaValida = true;
                } else {
                    printAttention(MSG_Error);
                }
            } catch (NumberFormatException e) {
                printAttention(MSG_Formato);
            }
        } while (!entradaValida);

        return valor;
    }

    public static String inputEmailTry() {
        String email;
        boolean formatoValido = false;

        do {
            email = inputStringTry(MSG_EMAIL, ERROR_VACIO);
            if (email.matches(EMAIL_REGEX)) {
                formatoValido = true;
            } else {
                printAttention(ERROR_EMAIL_FORMATO);
            }
        } while (!formatoValido);

        return email;
    }

    public static String inputStringTry(String mensaje, String errorVacio) {
        String entrada;
        boolean entradaValida = false;

        do {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();

            if (!entrada.isEmpty()) {
                entradaValida = true;
            } else {
                System.out.println(errorVacio);
            }
        } while (!entradaValida);

        return entrada;
    }


    /** INPUT HELPERS */

    public static int inputStock() {
        return inputIntegerPositivo(MSG_STOCK, ERROR_POSITIVO, ERROR_FORMATO);
    }

    public static int inputCantidad() {
        return inputIntegerPositivo(MSG_CANTIDAD, ERROR_POSITIVO, ERROR_FORMATO);
    }

    public static int inputId() {
        return inputIntegerPositivo(MSG_ID, ERROR_POSITIVO, ERROR_FORMATO);
    }

    public static double inputPrecio() {
        return inputFloatPositivo(MSG_CANTIDAD, ERROR_FLOAT_POSITIVO, ERROR_FLOAT_FORMATO);
    }

    public static int inputLitros() {
        return inputIntegerPositivo(MSG_CANTIDAD, ERROR_POSITIVO, ERROR_FORMATO);
    }

    public static String inputNombre() {
        return inputStringTry(MSG_NOMBRE, ERROR_VACIO);
    }

    public static String inputEmail() {
        return inputEmailTry();
    }

    public static void pausarConsola() {
        System.out.println("\nPresiona [ENTER] para continuar...");
        sc.nextLine();
    }

    public  static boolean confirmarAccion(String mensaje){
        System.out.println(mensaje + " s/n:");
        String entrada = (sc.nextLine().trim());
        return  entrada.equals("s");
    }

}
