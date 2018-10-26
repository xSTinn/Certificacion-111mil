/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao;

import com.martinstremiz.certificacioncentrocultural.modelo.Tematica;
import java.util.List;

/**
 *
 * @author marti
 */
public interface ITematicasDao {
    List<Tematica> getTematicas();
    Tematica getTematicaPorNombre(String nombre);
}
