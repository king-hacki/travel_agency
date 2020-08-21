package dao.impl;

import dao.RentDao;
import dao.common.AbstractCrudDao;
import models.Rent;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RentDaoImpl extends AbstractCrudDao<Rent> implements RentDao {

    public RentDaoImpl() {
        super(Rent.class);
    }


    @Override
    public Rent create(Rent entity) {
        return null;
    }
}
