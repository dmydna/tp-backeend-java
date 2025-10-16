package com.techlab.admin;

import com.techlab.cliente.Cliente;
import com.techlab.excepciones.NotEncotradoException;
import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.function.Predicate;

public class LineaClientes {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    public static int cantidadClientes = 0;

    public void agregar(Cliente cliente){
        this.clientes.add(cliente);
        this.cantidadClientes++;
    }


    public Cliente buscarPorID(int Id) throws NotEncotradoException {
        for(Cliente c : this.clientes){
            if(Id == c.getID()) {
                return c;
            }
        }
        throw new NotEncotradoException("Cliente con Id: " + Id + " no encontrado.");
    }

    public void listarClientes(Predicate<Cliente> filtro) {
        int i = 0;
        if(this.cantidadClientes == 0){
            System.out.println("\nNo hay Clientes...agrega alguno!\n");
            return;
        }
        System.out.printf("%-15s %-15s %-15s\n",
                "ID", "EMAIL", "NOMBRE");
        System.out.println("----------------------------------------------------------------");
        for (Cliente c : this.clientes) {
            if (filtro.test(c)) {
                System.out.printf("%-15d %-15s %-15s\n",
                        c.getID(),
                        c.getEmail(),
                        c.getNombre());
            }
        }
    }

}
