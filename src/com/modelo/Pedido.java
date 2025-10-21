package com.modelo;

import com.excepciones.ProductoNotEncotradoException;
import com.excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.ui.Utils.printWarning;


public class Pedido {

    // TODO Pasar a HashMap <id, ?<Cantidad c, Producto p>>
    // fix
    public class infoProducto {
        // cantidad del producto en el pedido
        public  int id;
        public  int cantidad;

        public infoProducto(Producto p, int cantidad){
            this.cantidad = cantidad;
            this.id = p.getID();
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


    public boolean agregarProducto(Producto p, int cantidad){
        try{
            p.descontarStock(cantidad);

            // si existe producto actualiza su cantidad
            if(this.infoProductos.stream().anyMatch(item -> item.id == p.getID())){
                for (int i=0; i < this.infoProductos.size(); i++){
                    if(this.infoProductos.get(i).id == p.getID()){
                        this.infoProductos.get(i).cantidad += cantidad;
                    };
                };
            }else {
                this.infoProductos.add(new infoProducto(p, cantidad));
            }

            productos.add(p);
            return true;
        }catch (StockInsuficienteException e){
            printWarning(e.getMessage());
            return false;
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

}


