package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;
import model.Account;

import database.DatabaseCreator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseCreatorTest {

    @Test
    public void testInsertDummyData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contactKeeper");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        // Make sure the EntityManager is not null
        assertNotNull(em);

        // Insert dummy data
        DatabaseCreator.insertDummyData();

        // Verify that the data was inserted
        Account account = em.createQuery("SELECT a FROM Account a WHERE a.account = :accountName", Account.class)
                .setParameter("accountName", "Rakrae")
                .getSingleResult();

        // Make sure the account and its contact are not null
        assertNotNull(account);
        assertNotNull(account.getContacts());

        em.close();
        emf.close();
    }
}
