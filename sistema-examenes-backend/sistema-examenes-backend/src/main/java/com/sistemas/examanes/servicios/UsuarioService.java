package com.sistemas.examanes.servicios;

import com.sistemas.examanes.entidades.Usuario;
import com.sistemas.examanes.entidades.UsuarioRol;
import java.util.Set;


public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    
    public Usuario ObtenerUsuario(String username);
    
    public void eliminarUsuario(Long usuarioId);
    
}
