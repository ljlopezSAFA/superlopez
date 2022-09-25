package com.app.superlopez.controller;

import com.app.superlopez.model.Pedido;
import com.app.superlopez.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @RequestMapping("/new")
    private void  realizarPedido(@RequestBody Pedido pedido){

    }


}
