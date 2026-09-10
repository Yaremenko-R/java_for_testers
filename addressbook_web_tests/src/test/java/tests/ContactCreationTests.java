package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import manager.hbm.ContactRecord;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData().withFirstname("Ivan").withMiddlename("Ivanych").withLastname("Ivanov")
                .withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru");

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("group name").withHeader("group header").withFooter("group footer"));
        }

        var group = app.hbm().getGroupList().get(0);
        var contactsInGroupBefore = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var contactsInGroupAfter = app.hbm().getContactsInGroup(group);
        var addedContact = contactsInGroupAfter.stream()
                .filter(c -> !contactsInGroupBefore.contains(c))
                .findFirst()
                .orElseThrow();

        assertThat(app.hbm().getGroupsInContact(addedContact), hasItem(group));

    }
}

