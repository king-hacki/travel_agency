package configs;

import configs.HibernateConfig;
import models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestTransaction;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class HibernateConfigTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testConnection() {
        Session session = sessionFactory.getCurrentSession();
        Country expected = new Country();
        expected.setId(1L);
        expected.setName("Hotel");
        session.save(expected);
        Country actual = session.find(Country.class, expected.getId());
        assertEquals(expected, actual);
    }


    @Test
    public void transactionCommitTest() {
        assertTrue(TestTransaction.isActive());

        Session session = sessionFactory.getCurrentSession();
        Country expected = new Country();
        expected.setId(1L);
        session.save(expected);
        Country actual = session.find(Country.class, expected.getId());
        assertNotNull(actual);
        assertTrue(TestTransaction.isFlaggedForRollback());
        TestTransaction.flagForCommit();
        TestTransaction.end();
        assertFalse(TestTransaction.isFlaggedForRollback());
        assertFalse(TestTransaction.isActive());

        TestTransaction.start();
        assertTrue(TestTransaction.isFlaggedForRollback());
        assertTrue(TestTransaction.isActive());
        session = sessionFactory.getCurrentSession();
        actual = session.find(Country.class, expected.getId());
        assertNotNull(actual);
        session.delete(actual);
        session.flush();
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());

        TestTransaction.start();
        session = sessionFactory.getCurrentSession();
        actual = session.find(Country.class, expected.getId());
        assertNotNull(actual);
        session.delete(actual);
        session.flush();
        assertTrue(TestTransaction.isActive());
        TestTransaction.flagForCommit();
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());

        TestTransaction.start();
        assertTrue(TestTransaction.isActive());
        session = sessionFactory.getCurrentSession();
        actual = session.find(Country.class, expected.getId());
        Assert.assertNull(actual);
    }

}
