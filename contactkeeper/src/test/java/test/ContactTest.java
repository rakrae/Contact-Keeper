package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.Contact;

public class ContactTest {

  @Test
  public void testContactProperties() {
    Contact contact = new Contact("John", "Doe", "123 Main St", "1234567890", "johndoe@example.com", "johndoe", "johndoe", "johndoe");
    assertEquals("John", contact.getFirstName());
    assertEquals("Doe", contact.getLastName());
    assertEquals("123 Main St", contact.getAddress());
    assertEquals(1234567890, contact.getPhoneNumber());
    assertEquals("johndoe@example.com", contact.getEmail());
    assertEquals("johndoe", contact.getFacebook());
    assertEquals("johndoe", contact.getLinkedIn());
    assertEquals("johndoe", contact.getInstagram());
  }

}