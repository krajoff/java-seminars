package seminars.fifth.note;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Contacts {
    private List<Contact> list = new ArrayList<>();

    public Contacts(List<Contact> list) {
        this.list = list;
    }

    public Contacts() {
        new Contacts(list);
    }

    public void add(Contact contact) {
        list.add(contact);
    }

    public void deleteById(int id) {
        ListIterator<Contact> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public Contact findById(int id) {
        for (Contact contact : list) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> findAllContacts(){
        return list;
    }

    public void editById(int id, Contact editedContact) {
        Contact contact = findById(id);
        if (contact != null) {
            if (!editedContact.getName().equals("")) {
                contact.setName(editedContact.getName());
            }
            if (!editedContact.getSurname().equals("")) {
                contact.setSurname(editedContact.getSurname());
            }
            if (editedContact.getPhone() != null) {
                contact.setPhone(editedContact.getPhone());
            }
            if (!editedContact.getCompany().equals("")) {
                contact.setCompany(editedContact.getCompany());
            }
        }
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "list=" + list +
                '}';
    }
}