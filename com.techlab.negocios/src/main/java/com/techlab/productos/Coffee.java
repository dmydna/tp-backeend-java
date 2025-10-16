package com.techlab.productos;

public class Coffee extends Producto {


    public Coffee(String nombre, double precio, int cantidadEnStock, double volumenEnLitros){
        // Es el constructor de producto.
        // Mandanda parametros  al constructor para inicializarlos.
        super(nombre, precio, cantidadEnStock);
        this.tipo = "CAFE";
    }

    public double calcularPrecioFinal(){
        return 0;
    }

}
