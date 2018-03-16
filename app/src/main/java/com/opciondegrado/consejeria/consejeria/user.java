package com.opciondegrado.consejeria.consejeria;

import java.io.Serializable;

/**
 * Created by Admin on 24/02/2018.
 */

public class user implements Serializable {
    private String correo;
    private String password;
    private String nombres;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
