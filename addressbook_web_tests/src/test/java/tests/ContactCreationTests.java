package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(
                new ContactData("Ivan", "Ivanych", "Ivanov", "Москва", "+7(495)577-05-13", "q@m.ru"));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void canCreateMultipleContacts() {
        int n = 5;
        int contactCount = app.contacts().getCount();
        for (int i = 0; i < n; i++) {
            app.contacts().createContact(
                    new ContactData("Ivan", "Ivanych", "Ivanov", "Москва", "+7(495)577-05-13", "q@m.ru"));
        }
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + n, newContactCount);
    }
}