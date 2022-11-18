package com.app.superlopez.service;

import com.app.superlopez.model.Producto;
import com.app.superlopez.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Devuelve el listado de todos los productos de la base de datos
     *
     * @return
     */
    public List<Producto> listar(){
        return productoRepository.findAll();
    }


    /**
     *
     * Devuelve el producto con el id que se pasa como parámetro
     *
     * @param id
     * @return
     */

    public Producto buscarPorId(Integer id){
        return productoRepository.findById(id).orElse(null);
    }


    /**
     *
     * Actualiza los datos de un producto existente o guarda uno nuevo
     *
     * @param producto
     */
    public Producto guardarProducto(Producto producto){
        Producto productoBD ;
        if(producto.getId() != null) {
            productoBD = buscarPorId(producto.getId());
            if(productoBD == null){
                productoBD= new Producto();
            }
        }else{
            productoBD= new Producto();
        }
        productoBD.setDescripcion(producto.getDescripcion());

        return productoRepository.save(productoBD);
    }


    /**
     *
     * Elimina un producto si este existe
     *
     * @param id
     */
    public void eliminarProducto(int id){
        Producto productoBD = buscarPorId(id);
        if(productoBD!=null){
            productoRepository.delete(productoBD);
        }
    }
}
