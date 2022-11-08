package com.app.superlopez.controller;

import com.app.superlopez.model.GraphqlInput;
import com.app.superlopez.model.LoteBarbacoa;
import com.app.superlopez.model.Producto;
import com.app.superlopez.service.LoteBarbacoaService;
import com.app.superlopez.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private LoteBarbacoaService loteBarbacoaService;

    @RequestMapping("/productos")
    public ModelAndView productos() {
        List<LoteBarbacoa> loteBarbacoas = loteBarbacoaService.findAll();
        ModelAndView modelAndView = new ModelAndView("productos");
        modelAndView.addObject("lotes", loteBarbacoas);
        return modelAndView;
    }

    @RequestMapping("/productos/comprar")
    public RedirectView productos(HttpSession session, @RequestParam(name = "idLote") Integer idLote) {
        LoteBarbacoa loteBarbacoas = loteBarbacoaService.findById(idLote);
        Map<LoteBarbacoa ,Integer > lotesSession;

        if(loteBarbacoas!=null) {
            if (session.getAttribute("lotes") != null) {
                lotesSession = (Map<LoteBarbacoa, Integer>) session.getAttribute("lotes");
                if (lotesSession.containsKey(loteBarbacoas)) {
                    lotesSession.put(loteBarbacoas, lotesSession.get(loteBarbacoas) + 1);
                }else{
                    lotesSession.put(loteBarbacoas, 1);
                }
            } else {
                lotesSession = new HashMap<>();
                lotesSession.put(loteBarbacoas, 1);
            }
           session.setAttribute("lotes",lotesSession);
        }
        return  new RedirectView("/productos");
    }

    @GetMapping("/productos/list")
    @QueryMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @PostMapping("/productos/save")
    @SchemaMapping(typeName = "Mutation", value = "guardarProducto")
    public String guardar(@RequestBody @Argument(name = "producto") GraphqlInput.ProductoInput input,
                          @RequestBody(required = false) @Argument(name = "idProducto") Integer idProducto) {

        Producto producto = new Producto();
        if(idProducto!=null){
            Producto productoBBDD = productoService.buscarPorId(idProducto);
            if (productoBBDD !=null){
                producto = productoBBDD;
            }
        }
        producto.setDescripcion(input.getDescripcion());
        productoService.guardarProducto(producto);
        return "Datos Guardadados correctamente";
    }

    @DeleteMapping("/productos/delete/{idProducto}")
    @MutationMapping
    public String borrarProducto(@PathVariable @Argument int idProducto) {
        productoService.eliminarProducto(idProducto);
        return "Datos eliminados correctamente";
    }

}
