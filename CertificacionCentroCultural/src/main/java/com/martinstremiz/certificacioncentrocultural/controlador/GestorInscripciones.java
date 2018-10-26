/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.controlador;

import com.martinstremiz.certificacioncentrocultural.dao.ICursosDao;
import com.martinstremiz.certificacioncentrocultural.dao.IInscripcionesDao;
import com.martinstremiz.certificacioncentrocultural.dao.IParticipantesDao;
import com.martinstremiz.certificacioncentrocultural.dao.ITematicasDao;
import com.martinstremiz.certificacioncentrocultural.dao.impl.CursosDaoImpl;
import com.martinstremiz.certificacioncentrocultural.dao.impl.InscripcionesDaoImpl;
import com.martinstremiz.certificacioncentrocultural.dao.impl.ParticipantesDaoImpl;
import com.martinstremiz.certificacioncentrocultural.dao.impl.TematicasDaoImpl;
import com.martinstremiz.certificacioncentrocultural.modelo.Curso;
import com.martinstremiz.certificacioncentrocultural.modelo.Inscripcion;
import com.martinstremiz.certificacioncentrocultural.modelo.Participante;
import com.martinstremiz.certificacioncentrocultural.modelo.Tematica;
import java.util.List;

/**
 *
 * @author marti
 */
public class GestorInscripciones {
      private final ITematicasDao tematicasDao = new TematicasDaoImpl();
      private final ICursosDao cursosDao = new CursosDaoImpl();
      private final IParticipantesDao participantesDao = new ParticipantesDaoImpl();
      private final IInscripcionesDao inscripcionesDao = new InscripcionesDaoImpl();
      
      public List<Tematica> getTematicas(){
          return tematicasDao.getTematicas();
      }
          
      public List<Curso> getCursosPorTematica(String tematica){
          Tematica tematicaAux = tematicasDao.getTematicaPorNombre(tematica);
          return cursosDao.getCursosPorIdTematica(tematicaAux.getIdTematica());
      }
      
      public Participante buscarParticipantePorEmail(String email){
          return participantesDao.buscarParticipantePorEmail(email);
      }
      
      public int grabarInscripcion(Inscripcion i){
          return inscripcionesDao.grabarInscripcion(i);
      }
      
      public boolean puedeInscribirse(Participante p){
          return inscripcionesDao.puedeInscribirse(p);
      }
}
