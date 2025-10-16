package com.techlab.pedido;

import com.techlab.productos.Producto;
import com.techlab.cliente.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;



public class Pedido {

    public enum state {
        PROCESANDO,
        COMPLETO,
    }

    private ArrayList<Producto> productos;
    private Cliente cliente;
    private int id = 0;
    public state estado;

    static int cantidadPedidos = 0;

    public Pedido(Cliente cliente){
        this.id = cantidadPedidos;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.estado = state.PROCESANDO;
        this.cantidadPedidos ++;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }
    public int cantidadProductos(){
        return this.productos.size();
    }

    public void agregarProducto(Producto p, int cantidad){
        if(cantidad < 0){
            System.out.println("Error: cantidad negativa.");
            return;
        }
        int Stock = p.getCantidadEnStock();
        Stock -= cantidad;
        if(Stock < 0){
            System.out.println("Error: cantidad supera stock disponible.");
            return;
        }
        p.setCantidadEnStock(Stock);
        productos.add(p);
    }

    public double calcularTotal(){
        double total = 0;
        for (Producto p : productos){
            total += p.getPrecio() * p.getCantidadEnStock();
        }
        return total;
    }

    public int getID(){
        return this.id;
    }



    public void mostrarInformacion() {
        System.out.printf("Cliente: %s, Email: %s\n", cliente.getNombre(), cliente.getEmail());
        String listaProductos = this.productos
                .stream()
                .map(p -> "{ ID: " + p.getID() + ","  +
                                    "TIPO: " + p.getNombre() +
                                    "}")
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(listaProductos);
    }

}


