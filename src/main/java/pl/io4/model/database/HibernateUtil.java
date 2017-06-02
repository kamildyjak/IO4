package pl.io4.model.database;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import java.io.InputStream;

/**
 * Created by jacob on 25.03.2017.
 */
public final class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    private HibernateUtil() {
        //Utility class - nie powinna być instancjonowana
    }

    static {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("hibernate.cfg.xml");
            SESSION_FACTORY = new Configuration()
                    .addInputStream(in)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return SESSION_FACTORY.openSession();
    }

    public static void closeSession() throws HibernateException {
        SESSION_FACTORY.close();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}
