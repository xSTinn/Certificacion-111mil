/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao.impl;

import com.martinstremiz.certificacioncentrocultural.modelo.Tematica;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.martinstremiz.certificacioncentrocultural.dao.ITematicasDao;
import org.hibernate.HibernateException;

/**
 *
 * @author marti
 */
public class TematicasDaoImpl implements ITematicasDao {

    private final SessionFactory sessionFactory = HibernateSession.getInstance();

    @Override
    public List<Tematica> getTematicas() {
        Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tematica> query = builder.createQuery(Tematica.class);
            Root<Tematica> root = query.from(Tematica.class);
            query.select(root);
            List<Tematica> lista = session.createQuery(query).list();
            session.close();
            return lista;
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public Tematica getTematicaPorNombre(String nombre) {
        Tematica tematicaAux = null;
        Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tematica> qry = builder.createQuery(Tematica.class);
            Root<Tematica> root = qry.from(Tematica.class);
            qry.select(root);
            qry.where(builder.equal(root.get("nombre"), nombre));
            tematicaAux = session.createQuery(qry).uniqueResult();
            return tematicaAux;
        } catch (HibernateException e) {
            throw e;
        }
    }

}
