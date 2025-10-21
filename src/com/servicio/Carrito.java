package com.servicio;

import com.excepciones.ProductoNotEncotradoException;
import com.excepciones.StockInsuficienteException;
import com.modelo.Producto;

import java.util.ArrayList;

public class Carrito {

    // Admite copias del mismo producto (para simplificar)
    private final ArrayList<Producto> productos = new ArrayList<>();
    public int total; // precio en total 
    public int cantidadTotal; // cantidad total de productos en carrito

    public void agregarProducto(Producto p, int cantidad) throws StockInsuficienteException {
        if(p.getCantidadEnStock() == 0 ||
           cantidad >= p.getCantidadEnStock()){
            return;
        }
        p.descontarStock(cantidad);
        this.cantidadTotal++;
        this.total += (int) p.getPrecio();
        this.productos.add(p);
    }

    public ArrayList<Producto> filterProductos(Producto p){
        ArrayList<Producto> filtered = new ArrayList<>();
        for(Producto producto: this.productos){
            if(producto.getNombre().equals(p.getNombre())) filtered.add(p);
        }
        return filtered;
    }

    // cantidad de un producto especifico
    public int cantidadProducto(Producto p){
        return this.filterProductos(p).size();
    }

    public  void validarProducto(String nombre) throws ProductoNotEncotradoException {

        for(Producto p : this.productos){
            if (nombre.equals(p.getNombre())) {
                return;
            }
        }
        throw new ProductoNotEncotradoException("El producto " + nombre + " no existe.");
    }


    // total de un producto especifico
    public double totalProducto(Producto p){
        return this.filterProductos(p).size() * p.getPrecio();
    }


    public int getCantidadTotal (){
        return this.cantidadTotal;
    }

    public int getTotal(){
        return this.total;
    }

    public void quitarProducto(Producto p, int cantidad){

        String nombre = p.getNombre();
        int i = 0;

        while(i < cantidad){
            Producto producto = this.productos.get(i);
            if(producto.getNombre().equals(nombre)){
                this.productos.remove(i);
            }
            i++;
        }

    }
}
