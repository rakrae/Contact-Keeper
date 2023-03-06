package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Account;


public class account_RepositoryJPA implements account_Repository {

	private static final String PERSISTANCE_UNIT_NAME = "contactKeeper";

	@Override
	public void add(Account account) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(account);
		et.commit();

		em.close();
		emf.close();

		System.out.println("Added account");
	}

	@Override
	public Optional<Account> read(long id) {

		Account account = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		account = em.find(Account.class, id);
		et.commit();

		em.close();
		emf.close();

		return Optional.ofNullable(account);
	}

	@Override
	public List<Account> readAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		List<Account> accounts = new ArrayList<>();
		TypedQuery<Account> query = em.createNamedQuery("readAllAccounts", Account.class);
		accounts = query.getResultList();
		et.commit();

		em.close();
		emf.close();

		return accounts;
	}

	@Override
	public Account updateAccount(Account account) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Account mergedAccount = em.merge(account);
		et.commit();

		em.close();
		emf.close();

		return mergedAccount;
	}

	@Override
	public void delete(Account account) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		if(!em.contains(account)) {
			account = em.merge(account);
		} else {
			em.remove(account);
		}
		et.commit();
		
		em.close();
		emf.close();
		
	}

}
