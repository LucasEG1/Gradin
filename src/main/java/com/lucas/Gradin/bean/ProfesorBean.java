package com.lucas.Gradin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfesorBean {
    
    private String dni = "";    

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass = "";

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
