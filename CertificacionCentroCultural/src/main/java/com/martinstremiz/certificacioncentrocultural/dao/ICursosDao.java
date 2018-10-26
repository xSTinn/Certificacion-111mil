/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao;

import com.martinstremiz.certificacioncentrocultural.modelo.Curso;
import java.util.List;

/**
 *
 * @author marti
 */
public interface ICursosDao {
    List<Curso> getCursosPorIdTematica(int idTematica);
}
