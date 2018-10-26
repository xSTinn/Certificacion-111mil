/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.modelo;

import java.util.Date;

/**
 *
 * @author marti
 */
public class Inscripcion {
    private int idInscripcion;
    private Curso curso;
    private Participante participante;
    private EstadoInscripcionEnum estado;
    private Date fechaInscripcion;

    public Inscripcion() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public EstadoInscripcionEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcionEnum estado) {
        this.estado = estado;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

  
    
}
