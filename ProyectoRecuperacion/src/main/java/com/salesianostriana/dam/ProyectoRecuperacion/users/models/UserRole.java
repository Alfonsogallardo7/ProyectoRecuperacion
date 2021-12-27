package com.salesianostriana.dam.ProyectoRecuperacion.users.models;

public enum UserRole {
    PROPIETARIO("PROPIETARIO"), GESTOR("GESTOR"), ADMIN("ADMIN");

    private String valor;

    private UserRole (String valor) {this.valor=valor;}
    public String getValor() {return valor;}
    public void setValor(String valor) {this.valor = valor;}
}
