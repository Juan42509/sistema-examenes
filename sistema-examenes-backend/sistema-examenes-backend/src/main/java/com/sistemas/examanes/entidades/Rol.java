package com.sistemas.examanes.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;
    
    private String nombre;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY,mappedBy = "usuario")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Rol() {
    }


    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    
    
    
}
