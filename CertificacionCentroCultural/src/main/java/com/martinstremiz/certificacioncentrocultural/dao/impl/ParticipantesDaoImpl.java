/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao.impl;

import com.martinstremiz.certificacioncentrocultural.dao.IParticipantesDao;
import com.martinstremiz.certificacioncentrocultural.modelo.Participante;
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
public class ParticipantesDaoImpl implements IParticipantesDao {

    private final SessionFactory sessionFactory = HibernateSession.getInstance();

    @Override
    public Participante buscarParticipantePorEmail(String email) {
        Participante p;
        Session session;
        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Participante> query = builder.createQuery(Participante.class);
            Root<Participante> root = query.from(Participante.class);
            query.select(root);
            query.where(builder.equal(root.get("email"), email));
            p = session.createQuery(query).uniqueResult();
            session.close();
            return p;
        } catch (HibernateException e) {
            throw e;
        }
    }

}
