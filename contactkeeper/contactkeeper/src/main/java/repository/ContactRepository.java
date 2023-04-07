package repository;

import java.util.List;

import model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	// Additional methods specific to ContactRepository go here
    List<Contact> findByAccountIdAndName(Long accountId, String name);
    
    List<Contact> findByUserName(String userName);
}

