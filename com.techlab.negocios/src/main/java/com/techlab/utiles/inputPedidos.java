package com.techlab.utiles;

import com.techlab.cliente.Cliente;
import com.techlab.excepciones.NotEncotradoException;
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
    public static  void crearPedidoMenu() throws NotEncotradoException {
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


    public static boolean confirmarPedidoMenu(){
        boolean salir = false;
        while (!salir){
            String bloque = """
                1) Listar Productos
                2) Agregar Productos al Pedido
                
                0) Salir
                """;
            System.out.println(bloque);
            String seleccion = (sc.nextLine().trim());

            switch (seleccion){
                case "1":
                    menu_opcion2();
                    salir = inputConfirmar("Agregar un producto al pedido?");
                    break;
                case "2": salir = true; break;
                default:
                    return false;
            }
        }
        return  true;

    }

    public static void option_crearCliente() {
            Cliente c = inputRegistrarCliente();
            c.mostrarInformacion();
            inputContinuar();
            inputArmarPedido(c);
    }

    public static void option_buscarCliente(){
        try{
            Cliente c = inputBuscarCliente();
            c.mostrarInformacion();
            inputContinuar();
            inputArmarPedido(c);
        }catch (NotEncotradoException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Operacion finalizada...");
        }


    }


    // agrega productos al pedido
    public static void inputArmarPedido(Cliente c) {

        if(!confirmarPedidoMenu()){
            return;
        }
        Pedido pedido = new Pedido(c);
        Producto producto;
        int cantidad;
        Boolean respuesta;
        Boolean salir;

        salir= false;
        while (!salir){
                try{
                    producto = inputBuscarProducto();
                    cantidad = inputCantidad();
                    pedido.agregarProducto(producto, cantidad);
                    System.out.println("\nOperacion exitosa...\n");
                }catch (ProductoNotEncotradoException e){
                    System.out.println(e.getMessage());
                }
            respuesta = inputConfirmar("Agregar otro producto?");
            salir=!respuesta;
        }

        pedidos.agregar(pedido);

    }


    public static Cliente inputRegistrarCliente(){
        String nombre = inputNombre();
        String email = inputEmail();
        Cliente c = new Cliente(nombre, email);
        clientes.agregar(c);
        System.out.println("\nCliente registrado con exito...\n");
        return c;
    }

    public static Cliente inputBuscarCliente() throws NotEncotradoException {
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
