/** Clase 07 - Herencia y Polimorfismo */
package com.techlab.app;

import com.techlab.admin.CatalogoProductos;
import com.techlab.admin.LineaClientes;
import com.techlab.admin.LineaPedidos;
import com.techlab.excepciones.ProductoNotEncotradoException;

import java.util.Scanner;

import static com.techlab.utiles.inputMenu.*;
import static java.lang.Integer.parseInt;


public class Main {

    public static final CatalogoProductos catalogo = new CatalogoProductos();
    public static final LineaClientes clientes = new LineaClientes();
    public static final LineaPedidos pedidos = new LineaPedidos();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ProductoNotEncotradoException {
        menu();
        sc.close();
    }

    }






