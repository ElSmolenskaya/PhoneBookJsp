package ru.academits.smolenskaya.service;

import ru.academits.smolenskaya.PhoneBook;
import ru.academits.smolenskaya.dao.ContactDao;
import ru.academits.smolenskaya.model.Contact;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class ContactService {
    private final ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (StringUtils.isEmpty(contact.getFirstName())){
            contactValidation.setValid(false);
            contactValidation.setFirstNameError("Поле Имя должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getLastName())){
            contactValidation.setValid(false);
            contactValidation.setLastNameError("Поле Фамилия должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getPhone())){
            contactValidation.setValid(false);
            contactValidation.setPhoneError("Поле Телефон должно быть заполнено.");
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setGlobalError("Номер телефона не должен дублировать другие номера в телефонной книге.");
        }

        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public String deleteContacts(int[] ids) {
        int deletedContactsCount = contactDao.delete(ids);

        return deletedContactsCount > 0
                ? "Количество удаленных контактов: " + deletedContactsCount
                : "Ни один контакт не был удален";
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public void saveLastContact(Contact contact) {
        contactDao.saveLastContact(contact);
    }

    public Contact getLastContact() {
        return contactDao.getLastContact();
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        contactDao.saveLastContactValidation(contactValidation);
    }

    public ContactValidation getLastContactValidation() {
        return contactDao.getLastContactValidation();
    }
}