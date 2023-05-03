package test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Account;
import model.Contact;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class AccountTest {

    @Test
    public void testAddContact() {
        Account account = new Account("user", "password", "John", "Doe", "male", 25);
        Contact contact = Mockito.mock(Contact.class);

        account.addContact(contact);

        assertEquals(Arrays.asList(contact), account.getContacts());
        verify(contact).setAccount(account);
    }

    @Test
    public void testRemoveContact() {
        Account account = new Account("user", "password", "John", "Doe", "male", 25);
        Contact contact = Mockito.mock(Contact.class);

        account.addContact(contact);
        account.removeContact(contact);

        assertEquals(0, account.getContacts().size());
        verify(contact).setAccount(null);
    }
}