/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinstremiz.certificacioncentrocultural.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author marti
 */
public class HibernateSession {

    private static SessionFactory session;

    private HibernateSession() {
    }

    public static SessionFactory getInstance() {
        if (session == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // obtiene los valores de hibernate.cfg.xml
                    .build();
            try {
                session = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error al crear la sesion: \n" + e.getMessage());
                StandardServiceRegistryBuilder.destroy(registry);
                throw e;
            }
        }
        return session;
    }
}
