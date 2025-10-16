package com.techlab.productos;

import com.techlab.excepciones.ProductoNotEncotradoException;

public  abstract class Producto {

    public String tipo;
    private String nombre;
    private double precio;
    private int cantidadEnStock;
    private int id;

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

    public void descontarStock(int cantidad){
        this.cantidadEnStock -= cantidad;
    }

    public static double calcularImpuestos(double precio){
        return precio * 0.21;
    }

    public void mostrarInformacion(){
        System.out.printf("Producto: %s, \n precio: %f \n stock: %d",
                           this.nombre,
                           this.precio,
                           this.cantidadEnStock);
    }

    public void buscarProductoPorNombre() throws ProductoNotEncotradoException {
        throw new ProductoNotEncotradoException("El producto 'X' no existe.");
    }

}


