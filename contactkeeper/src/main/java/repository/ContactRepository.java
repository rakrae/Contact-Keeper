package repository;

import java.util.List;
import java.util.Optional;

import model.Contact;

public interface ContactRepository {
	
	void add(Contact contact);
	
	Optional<Contact> read(long id);
	
	List<Contact> readAll();
	
	Contact updateContact(Contact contact);
	
	void delete(Contact contact);
	
	 public List<Contact> findByAccountAndName(Long accountId, String name);
	
}
