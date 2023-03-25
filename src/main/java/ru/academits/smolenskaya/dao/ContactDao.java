package ru.academits.smolenskaya.dao;

import ru.academits.smolenskaya.model.Contact;
import ru.academits.smolenskaya.service.ContactValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anna on 17.06.2017.
 */
public class ContactDao {
    private final List<Contact> contactList = new ArrayList<>();
    private final AtomicInteger idSequence = new AtomicInteger(0);
    private Contact lastContact = new Contact();
    private ContactValidation lastContactValidation = new ContactValidation();

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    public int delete(int[] ids) {
        int deletedContactsCount = 0;

        if (ids!=null) {
            for (int id : ids) {
                for (int j = 0; j < contactList.size(); j++) {
                    if (Objects.equals(contactList.get(j).getId(), id)) {
                        contactList.remove(j);
                        deletedContactsCount++;

                        break;
                    }
                }
            }
        }

        return deletedContactsCount;
    }

    public void saveLastContact(Contact contact) {
        this.lastContact = contact;
    }

    public Contact getLastContact() {
        return lastContact;
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        this.lastContactValidation = contactValidation;
    }

    public ContactValidation getLastContactValidation() {
        return lastContactValidation;
    }
}
