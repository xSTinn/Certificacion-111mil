/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao.impl;

import com.martinstremiz.certificacioncentrocultural.dao.ICursosDao;
import com.martinstremiz.certificacioncentrocultural.modelo.Curso;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author marti
 */
public class CursosDaoImpl implements ICursosDao {

    private final SessionFactory sessionFactory = HibernateSession.getInstance();

    @Override
    public List<Curso> getCursosPorIdTematica(int idTematica) {
         Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
            Root<Curso> root = query.from(Curso.class);
            query.select(root);
            query.where(builder.equal(root.get("tematica"), idTematica));
            List<Curso> lista = session.createQuery(query).list();
             session.close();
            return lista;
        } catch (HibernateException e) {
            throw e;
        } 
    }

}
