/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao;
import com.martinstremiz.certificacioncentrocultural.modelo.Inscripcion;
import com.martinstremiz.certificacioncentrocultural.modelo.Participante;

/**
 *
 * @author marti
 */
public interface IInscripcionesDao {
    int grabarInscripcion(Inscripcion i);
    boolean puedeInscribirse(Participante p);
}
