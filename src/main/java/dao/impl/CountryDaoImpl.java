package dao.impl;

import dao.CountryDao;
import dao.common.AbstractCrudDao;
import lombok.Setter;
import models.Country;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CountryDaoImpl extends AbstractCrudDao<Country> implements CountryDao {

    public CountryDaoImpl() {
        super(Country.class);
    }

    @Override
    public Country create(Country entity) {
        return null;
    }
}
