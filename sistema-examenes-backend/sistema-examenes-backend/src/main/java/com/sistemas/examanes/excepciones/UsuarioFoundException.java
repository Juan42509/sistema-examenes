package com.sistemas.examanes.excepciones;


public class UsuarioFoundException extends Exception{
  
    public UsuarioFoundException(){
        super("El usuario con ese Username ya existe en la base de datos, vuelva a intentar");
    }
    
    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
    
}
