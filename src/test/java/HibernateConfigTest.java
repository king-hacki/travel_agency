import configs.HibernateConfig;
import models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class HibernateConfigTest {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Test
    public void testConnection() {
        Session session = sessionFactory.getCurrentSession();
        Country expected = new Country();
        expected.setId(1L);
        expected.setName("Hotel");
        session.save(expected);
        session.persist(expected);
        Country actual = session.find(Country.class, expected.getId());
        assertEquals(expected, actual);
    }

}