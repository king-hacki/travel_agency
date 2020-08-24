package dao;

import dao.common.CrudHibernateRepository;
import models.security_models.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudHibernateRepository<Role> {
}
