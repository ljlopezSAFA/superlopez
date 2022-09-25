package com.app.superlopez.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pedido")
public class Pedido {

    private int id;
    private String codigo;
    private Integer numLote;
    private String apodo;
    private Set<LineaPedido> lineasPedido = new HashSet<>();
    private Cliente cliente;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNumLote() {
        return numLote;
    }

    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    public Set<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void setLineasPedido(Set<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && Objects.equals(codigo, pedido.codigo) && Objects.equals(numLote, pedido.numLote)
                && Objects.equals(apodo, pedido.apodo) && Objects.equals(lineasPedido, pedido.lineasPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, numLote, apodo, lineasPedido);
    }
}
