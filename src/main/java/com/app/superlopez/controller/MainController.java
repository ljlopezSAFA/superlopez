package com.app.superlopez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/inicio";
    }

    @RequestMapping("/inicio")
    public String inicio(HttpSession session) {
        return "inicio";
    }

    @RequestMapping("/sobreNosotros")
    public String sobreNosotros() {
        return "sobre_nosotros";
    }

    @RequestMapping("/carrito")
    public String carrito() {
        return "carrito";
    }

    @RequestMapping("/productos")
    public String productos() {
        return "productos";
    }

    @RequestMapping("/ofertas")
    public String ofertas() {
        return "ofertas";
    }

}
