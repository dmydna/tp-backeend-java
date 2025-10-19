package com.cliente;

import static com.input.input.*;

public class Cliente {

    private String nombre;
    private String email;
    private int id;

    public static int cantidadClientes = 0;

    public  Cliente(String nombre, String email){
        if(nombre.trim().isEmpty() || !email.matches(EMAIL_REGEX)){
            String MENSAJE = nombre.trim().isEmpty() ?
                    ERROR_VACIO : ERROR_EMAIL_FORMATO;
            throw new IllegalArgumentException(MENSAJE);
        }
        this.nombre = nombre;
        this.email = email;
        this.id = cantidadClientes;
        cantidadClientes++;
    }

    public int getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        if(!email.matches(EMAIL_REGEX)){
            throw new IllegalArgumentException(ERROR_EMAIL_FORMATO);
        }
        this.email = email;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getID() {
        return this.id;
    }
}
