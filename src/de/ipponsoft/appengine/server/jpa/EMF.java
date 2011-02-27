package de.ipponsoft.appengine.server.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory getFactory() {
        return emfInstance;
    }

    public static EntityManager getManager() {
        return emfInstance.createEntityManager();
    }
}

