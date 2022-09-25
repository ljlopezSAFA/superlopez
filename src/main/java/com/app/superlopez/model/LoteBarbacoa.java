package com.app.superlopez.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lote_barbacoa")
public class LoteBarbacoa {

    private int id;
    private String codigo;
    private Integer numLote;
    private String apodo;
    private Set<Producto> productos = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lote_producto" , joinColumns = @JoinColumn(name = "id_lote_barbacoa"), inverseJoinColumns =@JoinColumn(name = "id_producto") )
    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoteBarbacoa that = (LoteBarbacoa) o;
        return id == that.id && Objects.equals(codigo, that.codigo) && Objects.equals(numLote, that.numLote) && Objects.equals(apodo, that.apodo) && Objects.equals(productos, that.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, numLote, apodo, productos);
    }
}
