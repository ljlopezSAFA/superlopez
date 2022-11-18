package com.app.superlopez.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_realizacion")
    private LocalDateTime fechaRealizacion;

    @Column(name = "fecha_entrega_deseada")
    private LocalDateTime fechaEntregaDeseada;

    @Column(name = "preparado")
    private boolean preparado = false;

    @Column(name = "entregado")
    private boolean entregado = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Set<LineaPedido> lineasPedido = new HashSet<>();

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;





}
