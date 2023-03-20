package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Contact;

public class ContactRepositoryJPA implements ContactRepository {

	private static final String PERSISTANCE_UNIT_NAME = "contactKeeper";

	@Override
	public void add(Contact contact) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(contact);
		et.commit();

		em.close();
		emf.close();

	}

	@Override
	public Optional<Contact> read(long id) {

		Contact contact = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		contact = em.find(Contact.class, id);
		et.commit();

		em.close();
		emf.close();

		return Optional.ofNullable(contact);
	}

	@Override
	public List<Contact> readAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		List<Contact> contacts = new ArrayList<>();
		TypedQuery<Contact> query = em.createNamedQuery("readAllContatcs", Contact.class);
		contacts = query.getResultList();
		et.commit();

		em.close();
		emf.close();

		return contacts;
	}

	@Override
	public Contact updateContact(Contact contact) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Contact mergedContact = em.merge(contact);
		et.commit();
		
		em.close();
		emf.close();
		
		return mergedContact;
	}

	@Override
	public void delete(Contact contact) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.remove(contact);
		et.commit();
		
		em.close();
		emf.close();
		
	}

	@Override
	public List<Contact> findByAccountAndName(Long accountId, String name) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			Query query = em.createQuery("SELECT c FROM Contact c WHERE c.account.id = :accountId AND c.name LIKE :name");
			query.setParameter("accountId", accountId);
			query.setParameter("name", "%" + name + "%");
			return query.getResultList();
			} finally {
			em.close();
			}		
		
	}

}
