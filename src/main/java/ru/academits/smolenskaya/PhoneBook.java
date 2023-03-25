package ru.academits.smolenskaya;

import ru.academits.smolenskaya.coverter.ContactConverter;
import ru.academits.smolenskaya.dao.ContactDao;
import ru.academits.smolenskaya.service.ContactService;

public class PhoneBook {
    public static ContactDao contactDao = new ContactDao();

    public static ContactService contactService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();
}
