package database;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.mindrot.jbcrypt.BCrypt;

import application.ApplicationContext;
import model.Account;
import model.Contact;
import model.Gender;

public class DatabaseCreator {
	
	public static void insertDummyData() {

		EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();

		String accountName = "kratos";		

		if (!ApplicationContext.getAccountRepository().findByUserName(accountName).isPresent()) {
			Account ac = new Account(accountName, BCrypt.hashpw("testbB?9", BCrypt.gensalt()), "Adi Andrei", "Fin", null, 29);
			
			for (int i = 1; i <= 25; i++) {
                Contact ct = new Contact(
                        "FirstName" + i,
                        "LastName" + i,
                        null ,
                        LocalDate.of(1993, 12, 01),
                        i + " Disney Street",
                        "1000000000" + i,
                        "Email" + i + "blabla@example.com",
                        "Facebook" + i,
                        "Instagram" + i,
                        "LinkedIn" + i,
                        " " + i
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
