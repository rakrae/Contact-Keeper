package repository;

import java.util.List;
import java.util.Optional;

import model.Account;

public interface account_Repository {
	
	void add(Account account);
	
	Optional<Account> read(long id);
	
	List<Account> readAll();
	
	Account updateAccount(Account account);
	
	void delete(Account account);
	
	
}
