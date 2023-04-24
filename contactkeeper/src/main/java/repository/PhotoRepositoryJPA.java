package repository;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Photo;

public class PhotoRepositoryJPA extends GenericRepository<Photo, Long> implements PhotoRepository {
	
	public PhotoRepositoryJPA() {
		super(Photo.class);
	}

	@Override
	public List<Photo> findByContactId(long contactId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("contactKeeper");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	    try {
	        TypedQuery<Photo> query = entityManager.createQuery(
	            "SELECT p FROM Photo p WHERE p.contact.id = :contactId", Photo.class);
	        query.setParameter("contactId", contactId);
	        return query.getResultList();
	    } finally {
	        entityManager.close();
	    }
	}

}
