package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testContactInfoOnMainPage() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData().withFirstname("Ivan").withMiddlename("Ivanych")
                            .withLastname("Ivanov").withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru"));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.email()
                                , contact.email2(), contact.email3(), contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var allInOne = app.contacts().getAllContactInfo();
        Assertions.assertEquals(expected, allInOne);
    }

}
