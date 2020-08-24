package dao.impl;

import dao.RoleDao;
import dao.common.AbstractCrudDao;
import models.security_models.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractCrudDao<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }
}
