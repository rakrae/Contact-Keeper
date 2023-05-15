package application;

import model.Account;
import model.Contact;
import model.Model;
import model.Photo;
import repository.AccountRepository;
import repository.AccountRepositoryJPA;
import repository.ContactRepository;
import repository.ContactRepositoryJPA;
import repository.PhotoRepository;
import repository.PhotoRepositoryJPA;

public class ApplicationContext {

    private static ApplicationContext instance;

    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final PhotoRepository photoRepository;
    
    private static Account loggedInAccount;
    
    private static Contact selectedContact;

    private static Photo selectedPhoto;
    
    private static Model selectedModel;
    
    private ApplicationContext() {
        accountRepository = new AccountRepositoryJPA();
        contactRepository = new ContactRepositoryJPA();
        photoRepository = new PhotoRepositoryJPA();
        selectedModel = new Model();
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
    
    public static PhotoRepository getPhotoRepository() {
    	return getInstance().photoRepository;
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

	public static Photo getSelectedPhoto() {
		return selectedPhoto;
	}

	public static void setSelectedPhoto(Photo selectedPhoto) {
		ApplicationContext.selectedPhoto = selectedPhoto;
	}

	public static Model getSelectedModel() {
		return selectedModel;
	}

	public static void setSelectedModel(Model selectedModel) {
		ApplicationContext.selectedModel = selectedModel;
	}
    
}
