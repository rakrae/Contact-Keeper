package repository;

import java.util.Optional;

import model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	// Additional methods specific to AccountRepository go here
	Optional<Account> findByUserName(String userName);
}
