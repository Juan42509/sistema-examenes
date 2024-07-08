package com.sistemas.examanes.controller;

import com.sistemas.examanes.configuraciones.JwtUtil;
import com.sistemas.examanes.entidades.JwtRequest;
import com.sistemas.examanes.entidades.JwtResponse;
import com.sistemas.examanes.entidades.Usuario;
import com.sistemas.examanes.excepciones.UsuarioNotFoundException;
import com.sistemas.examanes.servicios.impl.UserDetailsServiceImpl;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager anthenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtils;
    
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsuarioNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void autenticar(String username, String password) throws Exception{
        try {
            anthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("Usuario Deshabilitado " + e.getMessage());
        }catch(BadCredentialsException b){
            throw new Exception("Credenciales Invalidas " + b.getMessage());
        }
    }
    
    @GetMapping("/actual-usuario")
    public Usuario ObtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
}
