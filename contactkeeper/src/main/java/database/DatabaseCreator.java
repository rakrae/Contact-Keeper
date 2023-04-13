package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import application.ApplicationContext;
import model.Account;
import model.Contact;

public class DatabaseCreator {
	
	public static void insertDummyData() {

		EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();

		String accountName = "Rakrae";		

		if (!ApplicationContext.getAccountRepository().findByUserName(accountName).isPresent()) {
			Account ac = new Account(accountName, "123", "Adi Andrei", "Fin", "M", 29);
			
			for (int i = 1; i <= 25; i++) {
                Contact ct = new Contact(
                        "FirstName" + i,
                        "LastName" + i,
                        i + " Disney Street",
                        "1000000000" + i,
                        "Email" + i + "@example.com",
                        "Facebook" + i,
                        "Instagram" + i,
                        "LinkedIn" + i
                );
                ac.addContact(ct);
            }

			et.begin();
			em.persist(ac);
			et.commit();
		}

		em.close();
	}

}
