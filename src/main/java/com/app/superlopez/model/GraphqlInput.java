package com.app.superlopez.model;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GraphqlInput {


    @Data
    @AllArgsConstructor
    public class ProductoInput{
        private  String descripcion;
    }

}
