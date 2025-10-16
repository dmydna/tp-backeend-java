package com.techlab.productos;

public class Comida extends Producto implements Descontable {
    private String fechaVencimiento;

    public Comida(String nombre, double precio, int cantidadEnStock, String fechaVencimiento){
        super(nombre, precio, cantidadEnStock);
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public double calcularPrecioFinal(){
        return 0;
    }

    @Override
    public double aplicarDecuento(double porcentaje) {
        return 0;
    }
}
