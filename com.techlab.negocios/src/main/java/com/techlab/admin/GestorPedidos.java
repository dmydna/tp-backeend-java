package com.techlab.admin;

import com.techlab.excepciones.NotEncotradoException;
import com.techlab.pedido.Pedido;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.function.Predicate;

public class GestorPedidos {
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    public static int cantidadPedidos = 0;

    public void  agregar(Pedido p){
        this.pedidos.add(p);
        this.cantidadPedidos++;
    }

    public void imprimirPedidoDeCliente(String nombre){
        mostrarPedidos(p -> p.getCliente().getNombre().equals(nombre));
    }

    public void imprimirTodosLosPedidos(){
        mostrarPedidos(p->true);
    }

    public void mostrarPedidos(Predicate<Pedido> filtro) {
        this.pedidos.stream()
                .filter(filtro)
                .forEach(p -> p.mostrarInformacion());
    }

    public void listarPedidos(Predicate<Pedido> filtro) {
        int i = 0;
        if(this.cantidadPedidos == 0){
            System.out.println("\nNo hay Pedidos...agrega alguno!\n");
            return;
        }
        System.out.printf("%-15s %-15s %-15s\n",
                "ID", "CLIENTE", "CANTIDAD PRODUCTOS");
        System.out.println("----------------------------------------------------------------");
        for (Pedido p : this.pedidos) {
            if (filtro.test(p)) {
                System.out.printf("%-15d %-15s %-15d\n",
                        p.getID(),
                        p.getCliente().getNombre(),
                        p.cantidadProductos()
                );
            }
        }
    }

    public Pedido buscarPorID(int Id) throws NotEncotradoException {
        for(Pedido p : this.pedidos){
            if(Id == p.getID()) {
                return p;
            }
        }
        throw new NotEncotradoException("Pedido con Id: " + Id + " no encontrado.");
    }

    public void listarProductosEnPedido(int id) throws NotEncotradoException {

        Pedido pedido = buscarPorID(id);
        System.out.printf("%-15s %-15s %-15s %-15s\n",
                "ID", "TIPO", "CANTIDAD", "NOMBRE");
        System.out.println("----------------------------------------------------------------");
        for (Producto p : pedido.getProductos()) {
                System.out.printf("%-15s %-15s %-15d %-15s\n",
                        p.getID(),
                        p.tipo,
                        pedido.cantidadDeUnProductosID(p.getID()),
                        p.getNombre());

        }
    }
}
