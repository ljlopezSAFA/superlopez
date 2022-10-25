package com.app.superlopez.controller;

import com.app.superlopez.model.RegisterForm;
import com.app.superlopez.model.Usuario;
import com.app.superlopez.model.enums.Rol;
import com.app.superlopez.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

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
                           Model model, HttpSession session) {
        Usuario usuario = new Usuario();
        usuario.setUsername(form.getUsername());

        boolean existeUsuario = usuarioService.existeUsuairo(usuario);
        if (existeUsuario) {
            model.addAttribute("error", true);
            return "register";
        } else {
            usuario.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
            usuario.setRol(Rol.CLIENTE);
            Usuario usuearioRegistrado = usuarioService.guardarUsuario(usuario);
            return "redirect: /login";
        }


    }


}
