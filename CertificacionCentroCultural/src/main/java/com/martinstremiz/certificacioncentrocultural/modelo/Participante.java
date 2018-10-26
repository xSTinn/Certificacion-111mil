/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.modelo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author marti
 */
public class Participante {

    private int idParticipante;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private String email;
    private String tutor;

    public Participante() {
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean esMenorDeEdad() {
        return this.calcularEdad() < 18;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " " + this.email;
    }

    private int calcularEdad() {
        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fechaNacimiento);

        int anios = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        int meses = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int dias = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);

        //Corrige si no ha cumplido los años para el año actual
        if (meses < 0 || (meses == 0 && dias < 0)) {
            anios -= 1;
        }
        return anios;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

}
