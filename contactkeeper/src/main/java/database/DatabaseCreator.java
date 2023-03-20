package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Account;
import model.Contact;

public class DatabaseCreator {

	private final static String PERSISTANCE_NAME = "contactKeeper";

	// to create/initialise a data base

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory(PERSISTANCE_NAME);

	public static EntityManagerFactory getEntityManagerFactory() {
		return ENTITY_MANAGER_FACTORY;
	}
	
	

	public static void insertDummyData() {

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_NAME);
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
