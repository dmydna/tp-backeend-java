package com.techlab.utiles;

import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.productos.Bebida;
import com.techlab.productos.Coffee;
import com.techlab.productos.Producto;
import com.techlab.productos.Te;

import java.util.ArrayList;

import static com.techlab.app.Main.catalogo;
import static com.techlab.utiles.input.*;
import static java.lang.Integer.parseInt;

public class inputProductos {


    public static void opcion_buscarProducto() throws ProductoNotEncotradoException {
       Producto p = inputBuscarProducto() ;
       p.mostrarInformacion();
    }

    public static void opcion_actualizarProducto() throws ProductoNotEncotradoException {
       Producto p = inputActualizarProducto();
       p.mostrarInformacion();
    }




    public static Producto inputBuscarProducto() throws ProductoNotEncotradoException {
        int Id = inputId();
        return catalogo.buscarPorID(Id);
    }

    public static Producto inputActualizarProducto() throws ProductoNotEncotradoException {
        Producto p = inputBuscarProducto();

        String nombre = inputNombre();
        double precio = inputPrecio();
        int    stock  = inputStock();

        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidadEnStock(stock);

        return p;
    }


    public static Producto inputCrearProducto(String producto) {

        String nombre = inputNombre();
        double precio = inputPrecio();
        int stock     = inputStock();
        int litros    = inputLitros();

        Producto p = null;
        switch (producto){
            case "Cafe": p = (new Coffee(nombre, precio, stock,litros)); break;
            case "Te": p = (new Te(nombre, precio, stock, litros)); break;
            case "Bebida":
                default: p = (new Bebida(nombre, precio, stock, litros));
        }

        return p;
    }


    public static void inputRegistarProducto(String producto){
        Producto p = inputCrearProducto(producto);
        String mensaje = producto + " regitrado con exito...";
        if(p != null){
            catalogo.agregarProducto(p);
            System.out.println("\n");
            p.mostrarInformacion();
            System.out.println("\n");
            System.out.println(mensaje);
            inputContinuar();
        }
    }



    public static void registrarProductos(ArrayList<Producto> ps){
        for(Producto p : ps ){
            catalogo.agregarProducto(p);
        }
    }





}
