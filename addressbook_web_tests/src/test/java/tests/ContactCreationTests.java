package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(
                new ContactData("Ivan", "Ivanych", "Ivanov", "Москва", "+7(495)577-05-13", "q@m.ru"));
    }

}