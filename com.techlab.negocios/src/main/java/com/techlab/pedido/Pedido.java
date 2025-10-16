package com.techlab.pedido;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.cliente.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;



public class Pedido {

    // TODO Pasar a HashMap <id, ?<Cantidad c, Producto p>>
    // fix
    public class infoProducto {
        // cantidad de producto en pedido
        public   int id;
        public   int cantidad;

        public infoProducto(int id, int cantidad){
            this.cantidad = cantidad;
            this.id = id;
        }
    }

    public enum state {
        PROCESANDO,
        COMPLETO,
    }

    private ArrayList<Producto> productos;
    private ArrayList<infoProducto> infoProductos = new ArrayList<>();

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

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }

    public ArrayList<infoProducto> getInfoProductos(){
        return this.infoProductos;
    }
    public Cliente getCliente() {
        return this.cliente;
    }

    public int cantidadDeUnProductosID(int Id){

        for(infoProducto i : this.infoProductos ){
            if(i.id == Id){
                return i.cantidad;
            }
        }

        return 0;
    }
    public int cantidadProductos(){
        return this.productos.size();
    }

    public void agregarProducto(Producto p, int cantidad){
        try{
            int stock = p.descontarStock(cantidad);
            this.infoProductos.add(new infoProducto(p.getID(), stock));
            productos.add(p);
        }catch (StockInsuficienteException e){
            System.out.println(e.getMessage());
        }
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


