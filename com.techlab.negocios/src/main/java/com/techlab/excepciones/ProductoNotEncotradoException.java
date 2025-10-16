package com.techlab.excepciones;

public class ProductoNotEncotradoException extends Exception{
    public ProductoNotEncotradoException(String mensaje){
        super(mensaje);
    }
}
