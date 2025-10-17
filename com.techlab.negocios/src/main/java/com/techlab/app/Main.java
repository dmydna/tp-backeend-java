/** Clase 07 - Herencia y Polimorfismo */
package com.techlab.app;

import com.techlab.admin.GestorProductos;
import com.techlab.admin.GestorClientes;
import com.techlab.admin.GestorPedidos;
import com.techlab.excepciones.ProductoNotEncotradoException;

import java.util.Scanner;

import static com.techlab.utiles.inputMenu.*;
import static java.lang.Integer.parseInt;


public class Main {

    public static final GestorProductos catalogo = new GestorProductos();
    public static final GestorClientes clientes = new GestorClientes();
    public static final GestorPedidos pedidos = new GestorPedidos();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        sc.close();
    }

    }






