package dao.common;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudHibernateRepository<T> {

    T findOne(final long id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
