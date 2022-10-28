package com.app.superlopez.service;

import com.app.superlopez.model.Pedido;
import com.app.superlopez.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void guardarNuevoPedido(Pedido pedido){
        pedidoRepository.save(pedido);

    }

}
