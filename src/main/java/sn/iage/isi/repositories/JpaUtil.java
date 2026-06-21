package sn.iage.isi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT = "bookPU";
    private static EntityManagerFactory emf;

    private JpaUtil() {}

    public static void getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            getEntityManagerFactory();
        }
        return emf.createEntityManager();
    }
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
