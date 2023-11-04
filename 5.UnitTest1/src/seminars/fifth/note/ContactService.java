package seminars.fifth.note;

public class ContactService {
    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public void removeContact(Contact contact) {
        contactRepository.removeContact(contact);
    }

    public void updateContact(Contact contact) {
        contactRepository.updateContact(contact);
    }

    public void addContact(Contact contact) {
        contactRepository.addContact(contact);
    }

    public boolean exists(Contact contact){
        return contactRepository.exists(contact);
    }
}
