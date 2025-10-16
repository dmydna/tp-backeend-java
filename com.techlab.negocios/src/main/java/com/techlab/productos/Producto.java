package com.techlab.productos;

import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedido.Pedido;

public  abstract class Producto {

    public String tipo;
    private String nombre;
    private double precio;
    private int cantidadEnStock;
    private int id;     // TODO clase catologo tiene que asignar IDs

    static int cantidadProductosCreados = 0;

    public  abstract double calcularPrecioFinal();

    // Constructor con parametros
    public Producto(String nombre, double precio, int cantidadEnStock){
        this.id = cantidadProductosCreados;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        cantidadProductosCreados ++;
    }


    public int getID(){
        return this.id ;
    }

    public String getNombre(){
        return nombre;
    }


    public void setNombre(String nombre){
        if(nombre != null && !nombre.trim().isEmpty()){
            this.nombre = nombre;
        }
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        if(precio >= 0){
            this.precio = precio;
        }
    }

    public int getCantidadEnStock(){
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock){
        if(cantidadEnStock >= 0){
            this.cantidadEnStock = cantidadEnStock;
        }
    }

    public int descontarStock(int cantidad) throws StockInsuficienteException {
        if(cantidad < 0){
            System.out.println("Error: cantidad negativa.");
            return this.cantidadEnStock;
        }
        int Stock = this.getCantidadEnStock();
        if(Stock - cantidad == 0){
            System.out.println("Error: cantidad supera stock disponible.");
            throw new StockInsuficienteException("Error: stock insuficiente");
        }
        this.cantidadEnStock -= cantidad;
        return this.cantidadEnStock;
    }

    public static double calcularImpuestos(double precio){
        return precio * 0.21;
    }

    public void mostrarInformacion(){
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                "ID", "TIPO", "PRECIO", "STOCK", "NOMBRE");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-15d %-15s %-15f %-15d %-15s\n",
                           this.id,
                           this.tipo,
                           this.precio,
                           this.cantidadEnStock,
                           this.nombre
        );
    }


}


