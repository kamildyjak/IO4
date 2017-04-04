package pl.io4.resources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * Created by jacob on 04.04.2017.
 */
public class Storage<T> {
    public static SessionFactory getSessionFactory() {
        return hibernateUtil.getSessionFactory();
    }

    private T entity;
    private Session session;

    public void beginTransaction() {
        session = Storage.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public void commit () {
        session.getTransaction().commit();
    }

    public Storage(T entity) {
        this.entity = entity;
    }

    public void update(T entity) {
        session.update(entity);
    }

    public Serializable insert(T entity) {
        return session.save(entity);
    }

    public void delete(T entity) {
        session.delete(entity);
    }

    // This call will issue a warning about the unchecked cast,
    // but we know the value returned will be of the right type because
    // we specify the entity (T) class in the call.
    //
    // Note that "get" will return a null if no value with this id fails
    @SuppressWarnings (value="unchecked")
    public T getById(int id) {
        return (T) session.get(entity.getClass(), id);
    }
}

