package seminars.fifth.note;

import java.util.List;

public interface ContactRepository {

    void removeContact(Contact contact);

    void addContact(Contact contact);

    void updateContact(Contact contact);

    boolean exists(Contact contact);
}
