package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import model.Account;
import model.Contact;

public class DatabaseCreator {

	
	private static final String database = "contactKeeper";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	
	public static void insertDummyData() {

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(database);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Contact ct = new Contact(0l, "Mickey", "Mouse", "Disney Street", 787878787, "MickeyMouse@blabla.com", "Mickey",
				"Mickey the Mouse", "MK");
		Account ac = new Account(0l, "Rakrae", "123", "Adi Andrei", "Fin", "M", 29, ct);

		et.begin();
		em.persist(ac);
		et.commit();

		em.close();
		emf.close();

	}

}
