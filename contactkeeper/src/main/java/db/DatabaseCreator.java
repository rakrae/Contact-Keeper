package db;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseCreator {
	
	private static final String PERSISTANCE_NAME = "contactKeeper";
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory(PERSISTANCE_NAME);

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

}
