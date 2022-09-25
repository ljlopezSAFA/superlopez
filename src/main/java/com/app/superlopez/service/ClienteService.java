package com.app.superlopez.service;

import com.app.superlopez.model.Cliente;
import com.app.superlopez.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Devuelve el listado de todos los clientes de la base de datos
     *
     * @return
     */
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }


    /**
     *
     * Devuelve el cliente con el id que se pasa como parámetro
     *
     * @param id
     * @return
     */

    public Cliente buscarPorId(Integer id){
        return clienteRepository.findById(id).orElse(null);
    }


    /**
     *
     * Actualiza los datos de un cliente existente o guarda uno nuevo
     *
     * @param cliente
     */
    public void guardarCliente(Cliente cliente){
        Cliente clienteBD ;
        if(cliente.getId() != null) {
            clienteBD = buscarPorId(cliente.getId());
            if(clienteBD == null){
                clienteBD= new Cliente();
            }
        }else{
            clienteBD= new Cliente();
        }

        //Volcado de Datos
        clienteBD.setNombre(cliente.getNombre());
        clienteBD.setApellidos(cliente.getApellidos());
        clienteBD.setDireccion(cliente.getDireccion());
        clienteBD.setDni(cliente.getDni());
        clienteRepository.save(clienteBD);
    }


    /**
     *
     * Elimina un cliente si este existe
     *
     * @param id
     */
    public void eliminarCliente(int id){
        Cliente clienteBD = buscarPorId(id);
        if(clienteBD!=null){
            clienteRepository.delete(clienteBD);
        }
    }
}
