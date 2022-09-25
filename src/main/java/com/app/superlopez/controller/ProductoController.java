package com.app.superlopez.controller;

import com.app.superlopez.model.Producto;
import com.app.superlopez.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("/list")
    public List<Producto> listar() {
        return productoService.listar();
    }

    @PostMapping("/save")
    public void guardar(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
    }

    @DeleteMapping("/delete/{idProducto}")
    public void guardar(@PathVariable int idProducto) {
        productoService.eliminarProducto(idProducto);
    }

}
