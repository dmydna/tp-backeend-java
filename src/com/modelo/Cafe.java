package com.modelo;

public class Cafe extends Producto {

    public Cafe(String nombre, double precio, int cantidadEnStock, double volumenEnLitros){
        super(nombre, precio, cantidadEnStock);
        this.tipo = "CAFE";
    }

    public double calcularPrecioFinal(){
        return 0;
    }

}
