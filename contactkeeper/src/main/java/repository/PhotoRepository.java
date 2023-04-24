package repository;

import java.util.List;

import model.Contact;
import model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long>{
	
	// Additional methods specific to ContactRepository go here
	public List<Photo> findByContactId(long contactId);

}
