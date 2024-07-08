package com.sistemas.examanes;

import com.sistemas.examanes.entidades.Rol;
import com.sistemas.examanes.entidades.Usuario;
import com.sistemas.examanes.entidades.UsuarioRol;
import com.sistemas.examanes.excepciones.UsuarioFoundException;
import com.sistemas.examanes.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner { //

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(SistemaExamenesBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        try {
//            Usuario usuario = new Usuario();
//
//            usuario.setNombre("Juan");
//            usuario.setApellido("Maldonado");
//            usuario.setUsername("jmaldonado");
//            usuario.setPassword(bCryptPasswordEncoder.encode("admin"));
//            usuario.setEmail("juan@gmail.com");
//            usuario.setTelefono("45456456");
//            usuario.setPerfil("foto.png");
//
//            Rol rol = new Rol();
//            rol.setRolId(1L);
//            rol.setNombre("ADMIN");
//
//            Set<UsuarioRol> usuarioRoles = new HashSet<>();
//            UsuarioRol usuarioRol = new UsuarioRol();
//            usuarioRol.setRol(rol);
//            usuarioRol.setUsuario(usuario);
//            usuarioRoles.add(usuarioRol);
//
//            Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
//            System.out.println(usuarioGuardado.getUsername());
//        } catch (UsuarioFoundException e) {
//            e.printStackTrace();
//        }

    }

}
