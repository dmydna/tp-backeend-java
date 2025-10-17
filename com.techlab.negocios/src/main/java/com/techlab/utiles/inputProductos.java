package com.techlab.utiles;

import com.techlab.excepciones.NotValidValueException;
import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.productos.Bebida;
import com.techlab.productos.Cafe;
import com.techlab.productos.Producto;
import com.techlab.productos.Te;

import java.util.ArrayList;

import static com.techlab.app.Main.catalogo;
import static com.techlab.utiles.input.*;
import static java.lang.Integer.parseInt;

public class inputProductos {


    public static void opcion_buscarProducto()  {
        try {
            Producto p = inputBuscarProducto() ;
            p.mostrarInformacion();
        } catch (ProductoNotEncotradoException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Operación de búsqueda finalizada");
            inputContinuar();
        }

    }

    public static void opcion_actualizarProducto()  {
        try {
            Producto p = inputActualizarProducto();
            p.mostrarInformacion();
        } catch (ProductoNotEncotradoException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Operación de búsqueda finalizada");
            inputContinuar();
        }
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


    public static Producto inputCrearProducto(String producto) throws NotValidValueException {

        String nombre = inputNombre();
        double precio = inputPrecio();
        int stock     = inputStock();
        int litros    = inputLitros();

        if(precio<0 || stock <0 || litros <0)
            throw new NotValidValueException("Se ingresaron valores no validos o negativos");

        Producto p = null;
        switch (producto){
            case "Cafe": p = (new Cafe(nombre, precio, stock,litros)); break;
            case "Te": p = (new Te(nombre, precio, stock, litros)); break;
            case "Bebida":
                default: p = (new Bebida(nombre, precio, stock, litros));
        }

        return p;
    }



    public static void inputRegistarProducto(String producto){

        try{
            Producto p = inputCrearProducto(producto);
            catalogo.agregarProducto(p);
            System.out.println("\n");
            p.mostrarInformacion();
            System.out.println("\n");
            System.out.println(producto + " regitrado con exito...");
        }catch (NotValidValueException e){
            System.out.println("Error:" + e.getMessage());
            System.out.println("No registro ningun producto.");
        }finally {
            inputContinuar();
        }

    }



    public static void registrarProductos(ArrayList<Producto> ps){
        for(Producto p : ps ){
            catalogo.agregarProducto(p);
        }
    }





}
