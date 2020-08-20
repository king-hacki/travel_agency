package dao.impl;

import dao.CountryDao;
import dao.common.AbstractCrudDao;
import models.Country;


public class CountryDaoImpl extends AbstractCrudDao<Country> implements CountryDao {

    public CountryDaoImpl() {
        super(Country.class);
    }

    @Override
    public Country create(Country entity) {
        return null;
    }
}
