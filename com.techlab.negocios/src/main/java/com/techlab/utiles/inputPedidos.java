package com.techlab.utiles;

import com.techlab.cliente.Cliente;
import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.pedido.Pedido;
import com.techlab.productos.Producto;

import java.util.ArrayList;

import static com.techlab.app.Main.*;
import static com.techlab.utiles.input.*;
import static com.techlab.utiles.inputMenu.menu_opcion2;
import static com.techlab.utiles.inputProductos.inputBuscarProducto;
import static java.lang.Integer.parseInt;

public class inputPedidos {


    // crea un pedido vacio
    public static  void crearPedidoMenu() throws ProductoNotEncotradoException {
        System.out.println("""
                Crear Pedido:
                ----------------
                1) Crear Cliente
                2) Buscar Cliente
                0) Salir
                
                Elija una opción:
                """);

        String respuesta = sc.nextLine().trim();

        switch (respuesta){
            case "1": option_crearCliente(); break;
            case "2": option_buscarCliente();break;
            case "0":;
        }
    }


    public static void option_crearCliente() throws ProductoNotEncotradoException {
        inputArmarPedido(inputRegistrarCliente());
    }

    public static void option_buscarCliente() throws ProductoNotEncotradoException {
        inputArmarPedido(inputBuscarCliente());
    }


    // agrega productos al pedido
    public static void inputArmarPedido(Cliente c) throws ProductoNotEncotradoException {

        Pedido pedido = new Pedido(c);
        Producto producto;
        int cantidad;
        Boolean respuesta;

        menu_opcion2(); // Listar Productos

        if(catalogo.cantidadProductos == 0){
            inputContinuar();
            return;
        }
        System.out.println("Agregar Productos Al Pedido:\n");

        Boolean salir= false;
        while (!salir){
            producto = inputBuscarProducto();
            respuesta = inputConfirmar("agregar al pedido?");
            if(respuesta){
                cantidad = inputCantidad();
                pedido.agregarProducto(producto, cantidad);
                System.out.println("\nAgregado con exito...\n");
            }
            respuesta = inputConfirmar("agregar otro producto?");

            salir=!respuesta;
        }


    }


    public static Cliente inputRegistrarCliente(){
        String nombre = inputNombre();
        String email = inputEmail();
        Cliente c = new Cliente(nombre, email);
        clientes.agregar(c);
        System.out.println("\nCliente registrado con exito...\n");
        return c;
    }

    public static Cliente inputBuscarCliente() throws ProductoNotEncotradoException {
        int Id = inputId();
        return clientes.buscarPorID(Id);
    }





    public static void registrarPedidos(ArrayList<Pedido> ps){
        for(Pedido p : ps ){
            pedidos.agregar(p);
        }
    }

    public static void registrarClientes(ArrayList<Cliente> cs){
        for(Cliente c : cs ){
            clientes.agregar(c);
        }
    }

}
