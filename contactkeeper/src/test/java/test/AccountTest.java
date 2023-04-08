package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Account;

public class AccountTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("contactKeeper");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testCreateAccount() {
        // create an Account entity and persist it
        Account account = new Account("testAccount", "password", "John", "Doe", "Male", 30);
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();

        // retrieve the persisted Account entity
        Query query = em.createNamedQuery("readAllAccounts");
        List<Account> accounts = query.getResultList();
        Account persistedAccount = accounts.get(0);

        // verify that the retrieved entity matches the original entity
        assertEquals(account.getUserName(), persistedAccount.getUserName());
        assertEquals(account.getPassword(), persistedAccount.getPassword());
        assertEquals(account.getFirstName(), persistedAccount.getFirstName());
        assertEquals(account.getLastName(), persistedAccount.getLastName());
        assertEquals(account.getGender(), persistedAccount.getGender());
        assertEquals(account.getAge(), persistedAccount.getAge());

        // clean up
        em.getTransaction().begin();
        em.remove(persistedAccount);
        em.getTransaction().commit();
    }

}

