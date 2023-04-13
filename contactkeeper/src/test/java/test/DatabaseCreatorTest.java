package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.ApplicationContext;
import database.DatabaseCreator;
import model.Account;
import repository.AccountRepository;

public class DatabaseCreatorTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        emf = mock(EntityManagerFactory.class);
        em = mock(EntityManager.class);
        et = mock(EntityTransaction.class);
        accountRepository = mock(AccountRepository.class);

        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(et);
        when(ApplicationContext.getAccountRepository()).thenReturn(accountRepository);
    }

    @Test
    public void testInsertDummyData() {
        String accountName = "Rakrae";
        Account existingAccount = new Account(accountName, "123", "Adi Andrei", "Fin", "M", 29);
        when(accountRepository.findByUserName(accountName)).thenReturn(java.util.Optional.of(existingAccount));

        DatabaseCreator.insertDummyData();

        // Verify that the data was inserted
        assertNotNull(existingAccount.getContacts());
    }
}