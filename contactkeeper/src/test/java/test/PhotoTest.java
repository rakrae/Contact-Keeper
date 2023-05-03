package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.Contact;
import model.Photo;
import org.junit.Test;

public class PhotoTest {

    @Test
    public void testToString() {
        // mock EntityManager
        EntityManager em = mock(EntityManager.class);
        EntityTransaction et = mock(EntityTransaction.class);
        Query query = mock(Query.class);
        
        // create test data
        byte[] photoData = "test".getBytes();
        String filename = "test.jpg";
        Contact contact = new Contact();
        contact.setId(1L);
        Photo photo = new Photo(photoData, filename);
        photo.setContact(contact);
        
        // mock EntityManager behavior
        when(em.getTransaction()).thenReturn(et);
        when(et.isActive()).thenReturn(true);
        when(em.createQuery("SELECT c FROM Contact c WHERE c.id = :id")).thenReturn(query);
        when(query.setParameter("id", contact.getId())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(contact);
        
        // test toString() method
        String expected = "Photo [id=null, photoData=" + Arrays.toString(photoData) + ", filename=" + filename 
                + ", contact=" + contact.toString() + "]";
        assertEquals(expected, photo.toString());
    }

}
