package com.productos;
import com.excepciones.StockInsuficienteException;
import static com.input.input.*;

public  abstract class Producto {

    public String tipo;
    private String nombre;
    private double precio;
    private int cantidadEnStock;
    private int id;     // TODO clase catologo tiene que asignar IDs

    static int cantidadProductosCreados = 0;

    public  abstract double calcularPrecioFinal();

    public Producto(String nombre, double precio, int cantidadEnStock){
        this.id = cantidadProductosCreados;
        if(nombre.trim().isEmpty()){
            throw new IllegalArgumentException(ERROR_VACIO);
        }
        if(cantidadEnStock < 0 || precio < 0){
            String MENSAJE = precio < 0 ? ERROR_FLOAT_POSITIVO : ERROR_POSITIVO;
            throw new IllegalArgumentException(MENSAJE);
        }

        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        cantidadProductosCreados ++;
    }


    public int getID(){
        return this.id ;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String getNombre(){
        return nombre;
    }


    public void setNombre(String nombre){
        if(nombre.trim().isEmpty()){
            throw new IllegalArgumentException(ERROR_VACIO);
        }
        this.nombre = nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        if(precio < 0){
           throw new IllegalArgumentException(ERROR_FLOAT_POSITIVO);
        }
        this.precio = precio;
    }

    public int getCantidadEnStock(){
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock){
        if(cantidadEnStock < 0){
            throw new IllegalArgumentException(ERROR_POSITIVO);
        }
        this.cantidadEnStock = cantidadEnStock;
    }

    public int descontarStock(int cantidad) throws StockInsuficienteException {
        if(cantidad < 0){
            throw new IllegalArgumentException(ERROR_POSITIVO);
        }
        int Stock = this.getCantidadEnStock();
        if(Stock - cantidad < 0){
            throw new StockInsuficienteException(ERROR_STOCK);
        }
        this.cantidadEnStock -= cantidad;
        return this.cantidadEnStock;
    }

    public static double calcularImpuestos(double precio){
        return precio * 0.21;
    }


}


