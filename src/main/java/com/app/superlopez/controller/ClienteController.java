package com.app.superlopez.controller;

import com.app.superlopez.model.Cliente;
import com.app.superlopez.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping("/list")
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @PostMapping("/save")
    public void guardar(@RequestBody Cliente cliente) {
        clienteService.guardarCliente(cliente);
    }

    @DeleteMapping("/delete/{idCliente}")
    public void guardar(@PathVariable int idCliente) {
        clienteService.eliminarCliente(idCliente);
    }



}
