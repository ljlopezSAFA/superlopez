package com.app.superlopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private String dni;

    @Column(name = "codigo_validacion")
    private Integer codigoValidacion;

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonBackReference
    private Set<Pedido> pedidos = new HashSet<>(0);



}
