package com.app.superlopez.controller;

import com.app.superlopez.model.enums.Rol;
import com.app.superlopez.utilidades.UtilidadesSesion;
import jdk.jshell.execution.Util;
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
        boolean estaLogeado = UtilidadesSesion.hayUsuarioLogeado(session);
        if (estaLogeado) {
            Rol rol = UtilidadesSesion.getRolUsuarioLogueado(session);
        }
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


    @RequestMapping("/ofertas")
    public String ofertas() {
        return "ofertas";
    }

}
