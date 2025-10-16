package com.techlab.admin;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.productos.Producto;

public class CatalogoProductos {

    private ArrayList<Producto> productos = new ArrayList<>();
    public int cantidadProductos = 0;

    public void agregarProducto(Producto p){
        this.productos.add(p);
        this.cantidadProductos ++;
    }

    public boolean productoExiste(String nombre){
        return this.productos.stream()
                             .anyMatch(p -> nombre.equals(p.getNombre()));
    }

    public void quitarProducto(int id) throws ProductoNotEncotradoException {

        boolean productoRemovido = this.productos.removeIf(prod -> prod.getID() == id);

        if (productoRemovido) {
            this.cantidadProductos--;
            System.out.println("Operación de remoción finalizada con éxito.");
        } else {
            throw new ProductoNotEncotradoException("Producto con ID " + id + " no encontrado.");
        }
    }

    public Producto buscarPorID(int Id) throws ProductoNotEncotradoException {
        for(Producto p : this.productos){
            if(Id == p.getID()) {
                return p;
            }
        }
        throw new ProductoNotEncotradoException("Producto con Id: " + Id + " no encontrado.");
    }

    private void actualizarPorID(int Id, Consumer<Producto> accion){
        try {
            Producto p = this.buscarPorID(Id);
            System.out.println("Producto encontrado: " + p.getNombre() + "\n");
            accion.accept(p);
            p.mostrarInformacion();
        } catch (ProductoNotEncotradoException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Operación de búsqueda finalizada");
        }
    }

    public void actualizarStock(int Id, int stock){
        this.actualizarPorID(Id, p -> p.setCantidadEnStock(stock));
    }

    public void actualizarPrecio(int Id, int precio){
        this.actualizarPorID(Id, p -> p.setPrecio(precio));
    }


    public void listarProductos(Predicate<Producto> filtro) {
        int i = 0;
        if(this.cantidadProductos == 0){
            System.out.println("\nNo hay productos...agrega alguno!\n");
            return;
        }
        System.out.printf("%-15s %-15s %-15s %-15s\n",
                "ID", "TIPO", "CANTIDAD", "NOMBRE");
        System.out.println("----------------------------------------------------------------");
        for (Producto p : this.productos) {
            if (filtro.test(p)) {
                System.out.printf("%-15s %-15s %-15d %-15s\n",
                        p.getID(),
                        p.tipo,
                        p.getCantidadEnStock(),
                        p.getNombre());
            }
        }
    }


}
