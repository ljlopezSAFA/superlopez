package com.app.superlopez.utilidades;

import com.app.superlopez.model.LineaPedido;
import com.app.superlopez.model.Pedido;
import com.app.superlopez.model.Producto;
import com.github.javafaker.Faker;
import org.springframework.security.core.parameters.P;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UtilidadesProducto {

    public UtilidadesProducto() {
    }

    private static Faker faker = new Faker();

    public static  Integer getTotalLotes(Pedido pedido){
        return pedido.getLineasPedido().stream().mapToInt(l-> l.getCantidad().intValue()).sum();
    }


    public static Pedido crearPedidoConLineasPrueba(){

        Pedido p = new Pedido();
        p.setId(faker.number().numberBetween(1,999));
        p.setCodigo(faker.code().ean8());
        p.setFechaRealizacion(faker.date().past(1, TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        p.setFechaEntregaDeseada(faker.date().future(120, TimeUnit.MINUTES).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        p.setPreparado(false);
        p.setEntregado(false);
        p.setLineasPedido(new HashSet<>());

        Integer num_lineas = faker.number().numberBetween(1,20);
        for(int i = 0 ; i <num_lineas; i++){
            LineaPedido lineaPedido = new LineaPedido();
            lineaPedido.setId(i);
            lineaPedido.setCantidad((double) faker.number().numberBetween(1,5));
            lineaPedido.setPedido(p);
            lineaPedido.setLoteBarbacoa(null);
            p.getLineasPedido().add(lineaPedido);
        }

        return p;

    }



    public static Producto crearProducto(){
        Producto producto = new Producto();
        producto.setDescripcion(faker.beer().name());
        producto.setLotesBarbacoa(new HashSet<>());
        return producto;
    }


    public static List<Producto> crearProducto(Integer numElementos){
        List<Producto> producto = new ArrayList<>();

        for(int i = 0; i <= numElementos; i ++){
            Producto p = crearProducto();
            producto.add(p);
        }
        return producto;
    }
}
