package seminars.fifth.note;

import java.util.HashMap;

public class ContactRepositoryImp implements ContactRepository {

    private HashMap<Integer, Contact> contacts;

    public ContactRepositoryImp() {
        contacts = new HashMap<>();
    }

    @Override
    public void removeContact(Contact contact) {
        contacts.remove(contact.getId(), contact);
    }

    @Override
    public void updateContact(Contact contact) {
        (contacts.get(contact.getId())).setName(contact.getName());
        (contacts.get(contact.getId())).setSurname(contact.getSurname());
        (contacts.get(contact.getId())).setCompany(contact.getCompany());
        (contacts.get(contact.getId())).setPhone(contact.getPhone());
    }

    @Override
    public void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    @Override
    public boolean exists(Contact contact) {
        return contacts.containsValue(contact);
    }
}
