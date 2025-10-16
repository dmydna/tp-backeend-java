package com.techlab.main;



import com.techlab.productos.Coffee;
import com.techlab.productos.Te;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int stock = 12;

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Coffee("Cafe Suave", 5.7, stock, 3));
        productos.add(new Coffee("Cafe Amargo", 10.7, stock, 4));
        productos.add(new Te("Te Negro", 8, stock, 5));

        imprimirProductos(productos);

    }

    public static void imprimirProductos(ArrayList<Producto> productos){
        for (Producto p : productos){
            System.out.printf("\nProducto: %-10s  \n Stock: %d \n Precio: %f \n PrecioFinal: %-10s",
                    p.getNombre() , p.getCantidadEnStock(), p.getPrecio() , p.calcularPrecioFinal()) ;
        }
        System.out.println("\n");
    }


    static void agregarCarritoSCanner(ArrayList<Producto> productos, Carrito c){
        Scanner sc = new Scanner(System.in);
        for (Producto p : productos){
            p.mostrarInformacion();
            System.out.println("¿Desea agregar al Carrito? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if(respuesta.equals("s")){
                System.out.println("Ingrega Cantidad: ");
                String cantidad = sc.nextLine().trim().toLowerCase();
                c.agregarProducto(p, Integer.parseInt(cantidad));
                System.out.println(p.getNombre().toUpperCase() + " agregado al carrito. \n");
            }else{
                System.out.println( " ...no agrega al carrito. \n");
            }
        }
        sc.close();
    }
}