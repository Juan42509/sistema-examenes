
package com.sistemas.examanes.repositorios;

import com.sistemas.examanes.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public Usuario findByUsername(String username);
    
}
