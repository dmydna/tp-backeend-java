package com.input;

import com.admin.GestorProductos;
import com.excepciones.NotValidValueException;
import com.excepciones.ProductoNotEncotradoException;
import com.productos.Bebida;
import com.productos.Cafe;
import com.productos.Producto;
import com.productos.Te;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.Main.*;
import static com.input.input.*;


public class inputProductos {


    public static Producto obtenerProductoPorInput() throws ProductoNotEncotradoException {
        int Id = inputId();
        return catalogo.buscarPorID(Id);
    }

    public static Producto actualizarProductoPorInput() throws ProductoNotEncotradoException {

        Producto p = obtenerProductoPorInput();
        mostrarInformacion(p);

        Supplier<Boolean> actualizarNombre =
                () -> confirmarAccion("Actualizar Nombre del producto?");
        Supplier<Boolean>  actualizarPrecio =
                () -> confirmarAccion("Actualizar Precio del producto?");
        Supplier<Boolean>  actualizarStock  =
                () -> confirmarAccion("Actualizar Stock del producto?");

        if(actualizarNombre.get()){
            String nombre = inputNombre();
            p.setNombre(nombre);
        }
        if(actualizarPrecio.get()){
            double precio = inputPrecio();
            p.setPrecio(precio);
        }
        if(actualizarStock.get()){
            int stock  = inputStock();
            p.setCantidadEnStock(stock);
        }
        return p;
    }


    public static Producto seleccionarProductoPorInput() throws ProductoNotEncotradoException {
        System.out.println("Selecciona un producto:");
        if(GestorProductos.cantidadProductos != 0){
            System.out.println();
            listarProductos(p->true);
            System.out.println();
            return obtenerProductoPorInput();
        }
        throw new ProductoNotEncotradoException("Productos no encontrados...");
    }

    public static Producto crearProductoPorInput(String tipoProducto) throws NotValidValueException {
        String nombre = inputNombre();
        double precio = inputPrecio();
        int stock     = inputStock();
        int litros    = inputLitros();

        if(precio<0 || stock <0 || litros <0) {
            throw new NotValidValueException(ERROR_POSITIVO);
        }

        return switch (tipoProducto) {
            case "Cafe" -> new Cafe(nombre, precio, stock, litros);
            case "Te" ->   new Te(nombre, precio, stock, litros);
            default ->     new Bebida(nombre, precio, stock, litros);
        };
    }


    public static void listarProductos(Predicate<Producto> filtro) throws ProductoNotEncotradoException {
        int i = 0;
        if(GestorProductos.cantidadProductos == 0){
            throw new ProductoNotEncotradoException("No se encontraron Productos...");
        }
        System.out.printf("%-5s %-30s %-15s %-15s %-10s\n",
                "ID", "NOMBRE" ,"TIPO", "PRECIO", "STOCK");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Producto p : catalogo.filtrarProductos(filtro)) {
            if (filtro.test(p)) {
                System.out.printf("%-5d %-30s %-15s %-15.2f %-10d\n",
                        p.getID(),
                        p.getNombre(),
                        p.getTipo(),
                        p.getPrecio(),
                        p.getCantidadEnStock()
                );
            }
        }
    }

    public static void registrarProducto(String producto){
        try{
            Producto p = crearProductoPorInput(producto);
            printSuccess( producto + " regitrado con exito...");
            catalogo.agregarProducto(p);
            mostrarInformacion(p);
        }catch (NotValidValueException e){
            printWarning(e.getMessage());
            printAttention("No se registro ningun producto.");
        }finally {
            pausarConsola();
        }
    }

    public static void buscarProducto()  {
        try {
            Producto p = obtenerProductoPorInput() ;
            mostrarInformacion(p);
        } catch (ProductoNotEncotradoException e) {
            printWarning(e.getMessage());
        } finally {
            pausarConsola();
        }
    }

    public static void actualizarProducto()  {
        try {
            Producto p = actualizarProductoPorInput();
            mostrarInformacion(p);
        } catch (ProductoNotEncotradoException e) {
            printWarning(e.getMessage());
        } finally {
            pausarConsola();
        }
    }


    public static void registrarProductos(ArrayList<Producto> ps){
        for(Producto p : ps ){
            catalogo.agregarProducto(p);
        }
    }


    public static void mostrarInformacion(Producto p){
        System.out.println();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                "ID", "TIPO", "PRECIO", "STOCK", "NOMBRE");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-15d %-15s %-15f %-15d %-15s\n",
                p.getID(),
                p.getTipo(),
                p.getPrecio(),
                p.getCantidadEnStock(),
                p.getNombre()
        );
        System.out.println();
    }



}
