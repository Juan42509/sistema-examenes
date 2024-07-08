package com.sistemas.examanes.servicios.impl;

import com.sistemas.examanes.entidades.Usuario;
import com.sistemas.examanes.entidades.UsuarioRol;
import com.sistemas.examanes.excepciones.UsuarioFoundException;
import com.sistemas.examanes.repositorios.RolRepository;
import com.sistemas.examanes.repositorios.UsuarioRepository;
import com.sistemas.examanes.servicios.UsuarioService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario UsuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(UsuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }else{
            for(UsuarioRol usuarioRol: usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            UsuarioLocal = usuarioRepository.save(usuario);
        }
        
        return UsuarioLocal;
    }

    @Override
    public Usuario ObtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    
    
    
}
