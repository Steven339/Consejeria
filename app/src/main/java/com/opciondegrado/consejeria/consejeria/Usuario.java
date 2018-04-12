package com.opciondegrado.consejeria.consejeria;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String Nombre,Correo,documento,fechanac,ciudadnac,genero,edad,direccion,ciudad,fijo,celular,estrato,nacionalidad,rh;

    public Usuario(String Nombre, String Correo,String documento,String fechanac,
                   String ciudadnac,String genero, String edad,String direccion,String ciudad,
                   String fijo,String celular,String estrato,String nacionalidad,String rh) {
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.documento = documento;
        this.fechanac = fechanac;
        this.ciudadnac = ciudadnac;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion ;
        this.ciudad = ciudad;
        this.fijo = fijo;
        this.celular = celular;
        this.estrato = estrato;
        this.nacionalidad = nacionalidad;
        this.rh = rh;

    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getCiudadnac() {
        return ciudadnac;
    }

    public void setCiudadnac(String ciudadnac) {
        this.ciudadnac = ciudadnac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
