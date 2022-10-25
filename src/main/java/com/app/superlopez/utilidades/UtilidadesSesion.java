package com.app.superlopez.utilidades;

import com.app.superlopez.model.enums.Rol;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class UtilidadesSesion {

    public UtilidadesSesion() {
    }


    public static boolean hayUsuarioLogeado(HttpSession session){
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        return securityContext != null
                && securityContext.getAuthentication() != null
                && securityContext.getAuthentication().getPrincipal() !=null;
    }


    public static Rol getRolUsuarioLogueado(HttpSession session){
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Rol rol = Rol.valueOf((Collections.singletonList(securityContext.getAuthentication().getAuthorities()).get(0).toString().replace("[","").replace("]","")));
        return rol;
    }
}
