package test;

import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import database.PersistenceManager;

public class PersistenceManagerTest {

    private static EntityManagerFactory factory;

    @BeforeClass
    public static void setUp() {
        factory = PersistenceManager.getEntityManagerFactory();
    }

    @AfterClass
    public static void tearDown() {
        PersistenceManager.closeEntityManagerFactory();
    }

    @Test
    public void testEntityManagerFactory() {
        Assert.assertNotNull("EntityManagerFactory should not be null", factory);
        Assert.assertTrue("EntityManagerFactory should be open", factory.isOpen());
    }

}