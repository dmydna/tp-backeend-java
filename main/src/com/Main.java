package com;
import com.admin.GestorProductos;
import com.admin.GestorClientes;
import com.admin.GestorPedidos;

import java.util.Scanner;

import static com.input.inputMenu.menu;


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






