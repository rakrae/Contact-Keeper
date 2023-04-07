package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Account;
import model.Contact;

public class DatabaseCreator {

	private static final String PERSISTANCE_NAME = "contactKeeper";

	public static void insertDummyData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		String accountName = "Rakrae";
		String queryStr = "SELECT a FROM Account a WHERE a.account = :accountName";
		Account existingAccount = em.createQuery(queryStr, Account.class).setParameter("accountName", accountName)
				.getResultList().stream().findFirst().orElse(null);
		if (existingAccount == null) {
			Contact ct = new Contact("Mickey", "Mouse", "Disney Street", 787878787, "MickeyMouse@blabla.com",
					"Mickey", "Mickey the Mouse", "MK");
			Account ac = new Account(accountName, "123", "Adi Andrei", "Fin", "M", 29);
			ac.addContact(ct);
			et.begin();
			em.persist(ac);
			et.commit();
		}
		em.close();
		emf.close();
	}

}
