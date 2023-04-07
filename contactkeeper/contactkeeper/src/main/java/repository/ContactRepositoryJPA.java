package repository;

import model.Contact;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ContactRepositoryJPA extends GenericRepository<Contact, Long> implements ContactRepository {

    public ContactRepositoryJPA() {
        super(Contact.class);
    }

    @Override
    public List<Contact> findByAccountIdAndName(Long accountId, String name) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c WHERE c.account.id = :accountId AND c.name LIKE :name", Contact.class);
            query.setParameter("accountId", accountId);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

	@Override
	public List<Contact> findByUserName(String userName) {
		EntityManager em = getEntityManager();

        try {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c WHERE c.account.userName = :userName", Contact.class);
            query.setParameter("userName", userName);
            return query.getResultList();
        } finally {
            em.close();
        }
	}
}
