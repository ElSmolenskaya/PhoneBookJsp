package ru.academits.smolenskaya.servlet;

import ru.academits.smolenskaya.PhoneBook;
import ru.academits.smolenskaya.coverter.ContactConverter;
import ru.academits.smolenskaya.model.Contact;
import ru.academits.smolenskaya.service.ContactService;
import ru.academits.smolenskaya.service.ContactValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {
    private final ContactService contactService = PhoneBook.contactService;
    private final ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Contact contact = contactConverter.convertFormStringParam(contactParams);

        ContactValidation contactValidation = contactService.addContact(contact);
        contactService.saveLastContactValidation(contactValidation);
        if (contactValidation.isValid()) {
            contactService.saveLastContact(new Contact());
        } else {
            contactService.saveLastContact(contact);
        }

        resp.sendRedirect("/phonebook");
    }
}