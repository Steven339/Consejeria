package com.opciondegrado.consejeria.consejeria;

import java.io.Serializable;

public class Auxilio implements Serializable {
    String titulo,descripcion;

    public Auxilio(){}

    public Auxilio(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
