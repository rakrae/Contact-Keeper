package application;

import model.Account;
import model.Contact;
import repository.AccountRepository;
import repository.AccountRepositoryJPA;
import repository.ContactRepository;
import repository.ContactRepositoryJPA;

public class ApplicationContext {

    private static ApplicationContext instance;

    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    
    private static Account loggedInAccount;
    
    private static Contact selectedContact;

    private ApplicationContext() {
        accountRepository = new AccountRepositoryJPA();
        contactRepository = new ContactRepositoryJPA();
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public static AccountRepository getAccountRepository() {
        return getInstance().accountRepository;
    }

    public static ContactRepository getContactRepository() {
        return getInstance().contactRepository;
    }
    
    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account account) {
        loggedInAccount = account;
    }
    
    public static Contact getSelectedContact() {
        return selectedContact;
    }

    public static void setSelectedContact(Contact contact) {
        selectedContact = contact;
    }
}
