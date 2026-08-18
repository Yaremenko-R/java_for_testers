package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().createContact(
                    new ContactData("Ivan", "Ivanych", "Ivanov", "Москва", "+7(495)577-05-13", "q@m.ru"));
        }
        app.contacts().removeContact();
    }
}
