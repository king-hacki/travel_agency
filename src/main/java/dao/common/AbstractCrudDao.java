package dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import static java.lang.String.format;

@Repository
@Transactional
public abstract class AbstractCrudDao<T> implements CrudHibernateRepository<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractCrudDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T findOne(long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + clazz.getName()).list();
    }

    @SuppressWarnings("unchecked")
    public Long save(T entity) {
        return (Long) getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    public T update(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void deleteById(long id) {
        final T entity = findOne(id);
        delete(entity);
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
