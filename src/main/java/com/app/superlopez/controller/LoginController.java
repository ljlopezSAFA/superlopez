package com.app.superlopez.controller;

import com.app.superlopez.model.RegisterForm;
import com.app.superlopez.model.Usuario;
import com.app.superlopez.model.enums.Rol;
import com.app.superlopez.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Collections;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) Boolean error) {
        if (error != null) model.addAttribute("error", true);
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(name = "error", required = false) Boolean error,
                           @ModelAttribute(name = "registerForm") RegisterForm form,
                           Model model, HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(form.getUsername());

        boolean existeUsuario = usuarioService.existeUsuairo(usuario);
        if (existeUsuario) {
            model.addAttribute("error", true);
            return "register";
        } else {
            usuario.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
            usuario.setRol(Rol.CLIENTE);
            usuarioService.guardarUsuario(usuario);



            //FORZAR A LOGUEARSE
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(),
                    usuario.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(Rol.CLIENTE.toString())));

            authToken.setDetails(new WebAuthenticationDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

            return "redirect:/inicio";
        }


    }


}
