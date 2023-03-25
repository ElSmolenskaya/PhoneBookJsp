package ru.academits.smolenskaya.servlet;

import ru.academits.smolenskaya.PhoneBook;
import ru.academits.smolenskaya.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class DeleteContactsServlet extends HttpServlet {
    private final ContactService phoneBookService = PhoneBook.contactService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] idsInStrings = req.getParameterValues("id");

        int[] ids = null;

        if (idsInStrings != null) {
            ids = Arrays.stream(idsInStrings).mapToInt(Integer::parseInt).toArray();
        }

        String deletedContactsInformation = phoneBookService.deleteContacts(ids);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(deletedContactsInformation);

        resp.sendRedirect("/phonebook");
    }
}