package com.inodevs.app.security;

public class Verificar {
    private String username;
    private String password;
    private String codigo;

    public Verificar () {}

    public Verificar(String username, String password, String codigo) {
        this.username = username;
        this.password = password;
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}