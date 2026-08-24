package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "contact name")) {
            for (var middlename : List.of("", "contact header")) {
                for (var lastname : List.of("", "contact footer")) {
                    result.add(new ContactData()
                            .withFirstname(firstname)
                            .withMiddlename(middlename)
                            .withLastname(lastname)
                            .withAddress("Москва")
                            .withMobile("+7(495)577-05-13")
                            .withEmail("q@m.ru"));
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 5))
                    .withMiddlename(randomString(i * 5))
                    .withLastname(randomString(i * 5))
                    .withAddress(randomString(i * 5))
                    .withMobile("+7(495)577-05-13")
                    .withEmail(randomString(i * 5) + "@m.ru"));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

}