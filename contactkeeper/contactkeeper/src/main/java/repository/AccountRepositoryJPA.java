package repository;

import model.Account;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountRepositoryJPA extends GenericRepository<Account, Long> implements AccountRepository {

    public AccountRepositoryJPA() {
        super(Account.class);
    }

    @Override
    public Optional<Account> findByUserName(String userName) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.userName = :userName", Account.class);
            query.setParameter("userName", userName);
            Account accountFound = query.getSingleResult();
            return Optional.ofNullable(accountFound);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
}
