package repository;

import java.util.List;

import model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long>{
	
	// Additional methods specific to ContactRepository go here
	List<Photo> findByContactId(long contactId);
}
