package fifth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.fifth.note.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactsTest {
    ContactRepositoryImp contacts;
    Contact contact;
    ContactRepository repo;
    ContactService contactService;

    @BeforeEach
    void testInit() {
        contacts = new ContactRepositoryImp();
        contact = mock(Contact.class);
        contacts.addContact(new Contact(1, "Mike", "Fry", "PC", 911));
        contacts.addContact(new Contact(2, "Nike", "Pry", "DC", 711));
        contacts.addContact(new Contact(3, "Slay", "Nash", "Odj", 654));
        contacts.addContact(contact);
        repo = mock(ContactRepository.class);
        contactService = new ContactService(repo);
    }

    /**
     * Проверить, что контакт добавляется в записную книжку
     */
    @Test
    void testAddContact() {
        contacts.addContact(contact);
        assertTrue(contacts.exists(contact));
    }

    /**
     * Проверить, что контакт удалется из записной книжки
     */
    @Test
    void testRemoveContact() {
        contacts.addContact(contact);
        assertTrue(contacts.exists(contact));
        contacts.removeContact(contact);
        assertFalse(contacts.exists(contact));
    }

    /**
     * Проверить, что контакт редактируется
     */
    @Test
    void testUpdateContact() {
        Contact c = new Contact(1, "Mike", "Fry", "PC", 555);
        contacts.updateContact(c);
        assertTrue(contacts.exists(c));
    }

    /**
     * Проверка, связи repository и service
     */

    @Test
    void testExistsContactMock() {
        when(repo.exists(contact)).thenReturn(true);
        assertTrue(contactService.exists(contact));
    }

}
