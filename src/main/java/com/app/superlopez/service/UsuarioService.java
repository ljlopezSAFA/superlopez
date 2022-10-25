package com.app.superlopez.service;

import com.app.superlopez.model.Usuario;
import com.app.superlopez.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean existeUsuairo(Usuario usuario){
        boolean existe = usuarioRepository.exists(Example.of(usuario));
        return existe;
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Recuperamos el usuario
        Usuario usuario = usuarioRepository.findTopByUsername(username);

        //Mapeamos los roles
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().toString()));

        //Creamos y devolvemos un UserDetails con los datos del usuario
        return new User(usuario.getUsername(), usuario.getPassword(),roles);
    }
}
