package com.techlab.utiles;

import com.techlab.cliente.Cliente;
import com.techlab.excepciones.NotEncotradoException;
import com.techlab.excepciones.ProductoNotEncotradoException;
import com.techlab.pedido.Pedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Coffee;
import com.techlab.productos.Producto;
import com.techlab.productos.Te;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.techlab.app.Main.*;
import static com.techlab.utiles.input.inputContinuar;
import static com.techlab.utiles.input.inputId;
import static com.techlab.utiles.inputPedidos.*;
import static com.techlab.utiles.inputProductos.*;
import static java.lang.Integer.parseInt;

public class inputMenu {

    public  static void menu()  {
        String bloque = """
        
        1) Agregar producto
        2) Listar productos
        3) Buscar/Actualizar producto
        4) Eliminar producto
        5) Crear un pedido
        6) Listar pedidos
        7) Ver Pedido
        8) [Agregar Muestra]
        
        0) Salir
        Elija una opción:
        """;


        Boolean salir = false;

        while(!salir){
            System.out.println(bloque);
            String respuesta = sc.nextLine().trim();

            switch (respuesta){
                case "8":  menu_opcion8();break; // Agregar Muestra
                case "1":  menu_opcion1();break; // Agregar producto
                case "2":  menu_opcion2();break; // Listar productos
                case "3":  menu_opcion3();break; // Buscar/Actualizar producto
                case "4":  menu_opcion4();break; // Eliminar producto
                case "5":  menu_opcion5();break; // Crear un pedido
                case "6":  menu_opcion6();break; // Listar pedidos
                case "7":  menu_opcion7();break; // Ver Pedido
                case "0":  salir = true;break;
            }
        }
    }

    // Agregar productos
    public static void menu_opcion1() {
        String bloque = """
                Agregar Producto:
                -----------------
                1) Cafe
                2) Te
                3) Bebidas
                0) Salir
                Elija una opción:
                """;
        System.out.println(bloque);
        String respuesta = sc.nextLine().trim();
        switch (respuesta) {
            case "1": inputRegistarProducto("Cafe"); break;
            case "2": inputRegistarProducto("Te"); break;
            case "3": inputRegistarProducto("Bebida");break;
            case "0": ;
        }
    }


    // Listar Productos
    public static void menu_opcion2() {
        System.out.println("""
        \nLista Productos:
        ------------------
        \n""");
        catalogo.listarProductos(p->true);
        System.out.println("\n");
        inputContinuar();
    }

    /* Buscar/Actualizar producto */
    public static void menu_opcion3() {
        Scanner sc = new Scanner(System.in);
        String bloque = """
                Buscar/Actualizar producto:
                ---------------------------
                1) Buscar 
                2) Actualizar
                0) Salir
                Elija una opción:                
                """;

        System.out.println(bloque);
        String respuesta = sc.nextLine().trim();

        switch (respuesta) {
            case "1": opcion_buscarProducto(); break;
            case "2": opcion_actualizarProducto(); break;
            case "0": ;
        }

    }


    //Eliminar producto
    public static void menu_opcion4() {
        try {
            Producto p = inputBuscarProducto();
            catalogo.quitarProducto(p.getID());
            System.out.println("Se elimino el producto: " + p.getNombre() );
        }catch (ProductoNotEncotradoException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Operacion finalizada...");
            inputContinuar();
        }


    }

    // Crear un pedido
    public static void menu_opcion5(){

        try{
            crearPedidoMenu();
        }catch (NotEncotradoException e){
            System.out.println(e.getMessage());
        }

    }

    // Listar pedidos
    public static void menu_opcion6() {
        pedidos.listarPedidos(p->true);
        inputContinuar();
    }

    // Ver Pedido
    public  static void menu_opcion7() {
       try{
           int iD = inputId();
           pedidos.listarProductosEnPedido(iD);
       }catch (NotEncotradoException e){
           System.out.println(e.getMessage());
        }finally{
           System.out.println("Operacion finalizada...");
           inputContinuar();
       }
    }

    // Agregar Muestra
    public static void menu_opcion8(){

        ArrayList<Producto> list_productos = new ArrayList<>();
        ArrayList<Cliente> list_clientes = new ArrayList<>();
        ArrayList<Pedido> list_pedidos = new ArrayList<>();

        Producto cafe_1 = new Coffee("Expresso Fuerte", 22.99, 15, 250);
        Producto cafe_2 = new Coffee("Late Suave", 18.50, 80, 750);
        Producto te_1 = new Te("Te Chai Especiado", 10.75, 45, 500);
        Producto te_2 = new Te("Te Blanco Premium", 35.00, 10, 500);
        Producto bebida_1 = new Bebida("Agua Mineral", 3.50, 120, 1000);
        Producto bebida_2 = new Bebida("Jugo Naranja 2L", 8.90, 25, 2000);

        list_productos.addAll(List.of(cafe_1, cafe_2, te_1, te_2, bebida_1, bebida_2));
        registrarProductos(list_productos);


        Cliente adrian  = new Cliente("Adrian", "adrian@mail.com");
        Cliente juan    = new Cliente("Juan", "juan@mail.com");
        Cliente maria   = new Cliente("Maria", "maria.perez@dominio.com");
        Cliente lucas   = new Cliente("Lucas", "lucas.g@empresa.net");
        Cliente sofia   = new Cliente("Sofia", "sofia88@correo.es");
        Cliente pedro   = new Cliente("Pedro", "pedro.alvarez@info.org");

        list_clientes.addAll(List.of(adrian, juan, maria, lucas, sofia, pedro));
        registrarClientes(list_clientes);


        Pedido pedido1_adrian = new Pedido(adrian);
        Pedido pedido1_juan = new Pedido(juan);
        Pedido pedido2_juan = new Pedido(juan);
        Pedido pedido1_sofi = new Pedido(sofia);
        Pedido pedido1_pedro = new Pedido(pedro);


        pedido1_adrian.agregarProducto(cafe_2, 30);
        pedido1_adrian.agregarProducto(bebida_2, 5);

        pedido1_juan.agregarProducto(te_1, 2);
        pedido1_juan.agregarProducto(bebida_1, 10);

        pedido2_juan.agregarProducto(cafe_1, 12);

        pedido1_sofi.agregarProducto(te_2, 1);
        pedido1_sofi.agregarProducto(cafe_2, 5);

        pedido1_pedro.agregarProducto(bebida_1, 25);
        pedido1_pedro.agregarProducto(te_1, 15);


        list_pedidos.addAll(List.of(pedido1_adrian, pedido1_juan, pedido2_juan, pedido1_sofi, pedido1_pedro));
        registrarPedidos(list_pedidos);

        System.out.println("Muestra agregada con existo....\n");
        System.out.printf("productos agregados: %d\nclientes agregados: %d\npedido agregados: %d\n",
                catalogo.cantidadProductos,
                clientes.cantidadClientes,
                pedidos.cantidadPedidos
        );

        inputContinuar();

    }
}
