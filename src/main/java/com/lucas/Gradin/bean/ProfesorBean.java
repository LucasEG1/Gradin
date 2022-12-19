package com.lucas.Gradin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProfesorBean {
    
    @Schema
    private String dni = "";
    @Schema
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password = "";

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
