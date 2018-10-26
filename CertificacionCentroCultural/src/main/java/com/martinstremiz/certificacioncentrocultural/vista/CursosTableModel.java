/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.vista;

import com.martinstremiz.certificacioncentrocultural.modelo.Curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marti
 */
public class CursosTableModel extends AbstractTableModel {

    private static final String[] NOMBRE_COLUMNAS = {"Nombre", "Profesor", "Aula", "Duración", "Carga Horaria", "Cupo"};
    private List<Curso> cursos;

    public CursosTableModel(List<Curso> cursos) {
        super();
        this.cursos = cursos;
    }

    @Override
    public int getRowCount() {
        return this.cursos.size();
    }

    @Override
    public int getColumnCount() {
        return NOMBRE_COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object valor = null;
        Curso curso = cursos.get(fila);
        switch (columna) {
            case 0:
                valor = curso.getNombre();
                break;
            case 1:
                valor = curso.getProfesor().toString();
                break;
            case 2:
                valor = curso.getAula().getNumero();
                break;
            case 3:
                valor = curso.getDuracion();
                break;
            case 4:
                valor = curso.getCargaHoraria();
                break;
            case 5:
                valor = curso.getCupo();
                break;
        }
        return valor;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
     public Curso getCursoPorIndex(int index) {
        return cursos.get(index);
    }

    @Override
    public String getColumnName(int index) {
        return NOMBRE_COLUMNAS[index];
    }
}
