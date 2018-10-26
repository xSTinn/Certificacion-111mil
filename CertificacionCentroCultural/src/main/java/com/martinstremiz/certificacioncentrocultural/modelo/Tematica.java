/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.modelo;

/**
 *
 * @author marti
 */
public class Tematica {
    private int idTematica;
    private String nombre;

    public Tematica() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }

    public int getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(int idTematica) {
        this.idTematica = idTematica;
    }
}
