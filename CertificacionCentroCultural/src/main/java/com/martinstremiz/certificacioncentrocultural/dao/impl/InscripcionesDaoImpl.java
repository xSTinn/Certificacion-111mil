/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao.impl;

import com.martinstremiz.certificacioncentrocultural.dao.IInscripcionesDao;
import com.martinstremiz.certificacioncentrocultural.modelo.Inscripcion;
import com.martinstremiz.certificacioncentrocultural.modelo.Participante;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author marti
 */
public class InscripcionesDaoImpl implements IInscripcionesDao {

    private final SessionFactory sessionFactory = HibernateSession.getInstance();

    @Override
    public int grabarInscripcion(Inscripcion i) {
        Session session;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            int codigo = (int) session.save(i);
            transaction.commit();
            return codigo;
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public boolean puedeInscribirse(Participante p) {
        Session session;
        try {
            session = sessionFactory.openSession();
            String sql = "select count(*) from Inscripcion i, Participante p where i.participante = p and (i.estado = :A OR i.estado = :B) and  p.email = :C";
            Query query = session.createQuery(sql);
            query.setParameter("A", "ACEPTADA");
            query.setParameter("B", "CONDICIONAL");
            query.setParameter("C", p.getEmail());
            boolean resp = Integer.parseInt(query.getSingleResult().toString()) < 3;
            session.close();
            return resp;
        } catch (HibernateException e) {
            throw e;
        }
    }
}
