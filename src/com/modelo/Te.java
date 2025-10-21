package com.modelo;

public class Te extends Producto {


    public Te(String nombre, double precio, int cantidadEnStock, double volumenEnLitros){
        // Es el constructor de producto.
        // Mandanda parametros  al constructor para inicializarlos.
        super(nombre, precio, cantidadEnStock);
        this.tipo = "TE";
    }

    public double calcularPrecioFinal(){
        return 1;
    }
}
