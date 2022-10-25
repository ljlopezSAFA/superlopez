package com.app.superlopez.repository;

import com.app.superlopez.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Usuario findTopByUsername(String username);


    <S extends Usuario> boolean exists(Example<S> example);
}
