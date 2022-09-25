package com.app.superlopez.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "linea_pedido")
public class LineaPedido {

    private int id;
    private Pedido pedido;
    private LoteBarbacoa loteBarbacoa;
    private Double cantidad;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @ManyToOne
    @JoinColumn(name = "id_lote_barbacoa")
    public LoteBarbacoa getLoteBarbacoa() {
        return loteBarbacoa;
    }

    public void setLoteBarbacoa(LoteBarbacoa loteBarbacoa) {
        this.loteBarbacoa = loteBarbacoa;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaPedido that = (LineaPedido) o;
        return id == that.id && Objects.equals(pedido, that.pedido) && Objects.equals(loteBarbacoa, that.loteBarbacoa) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido, loteBarbacoa, cantidad);
    }
}
