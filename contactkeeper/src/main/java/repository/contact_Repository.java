package repository;

import java.util.List;
import java.util.Optional;

import model.Contact;

public interface contact_Repository {
	
	void add(Contact contact);
	
	Optional<Contact> read(long id);
	
	List<Contact> readAll();
	
	Contact updateContact(Contact contact);
	
	void delete(Contact contact);
	
}
