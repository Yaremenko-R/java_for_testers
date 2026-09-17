package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        var extraGroups = newContacts.stream().filter(g -> !oldContacts.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newId));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData().withFirstname("Ivan").withMiddlename("Ivanych").withLastname("Grupkin")
                .withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru");

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("groupToAdd").withHeader("groupToAdd").withFooter("groupToAdd"));
        }

        var group = app.hbm().getGroupList().get(0);
        var contactsInGroupBefore = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var contactsInGroupAfter = app.hbm().getContactsInGroup(group);
        var addedContacts = contactsInGroupAfter.stream().filter(c -> !contactsInGroupBefore.contains(c)).toList();
        var addedContactId = addedContacts.get(0).id();
        var expectedList = new ArrayList<>(contactsInGroupBefore);
        expectedList.add(contact.withId(addedContactId));
        Assertions.assertEquals(Set.copyOf(contactsInGroupAfter), Set.copyOf(expectedList));
    }

    @Test
    void canAddContactToGroup() {
        var allContacts = app.hbm().getContactList();
        var allGroups = app.hbm().getGroupList();

        if (allGroups.isEmpty()) {
            app.hbm().createGroup(new GroupData().withName("group name").withHeader("group header").withFooter("group footer"));
            allGroups = app.hbm().getGroupList();
        }

        if (allContacts.isEmpty()) {
            app.hbm().createContact(new ContactData().withFirstname("Ivan").withMiddlename("Ivanych").withLastname("Ivanov")
                    .withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru"));
            allContacts = app.hbm().getContactList();
        }

        ContactData contactToUse = null;
        GroupData groupToUse = null;

        for (var group : allGroups) {
            var contactsInThisGroup = app.hbm().getContactsInGroup(group);
            for (var contact : allContacts) {
                if (!contactsInThisGroup.contains(contact)) {
                    contactToUse = contact;
                    groupToUse = group;
                    break;
                } else {
                    contactToUse = new ContactData().withFirstname("Petr").withMiddlename("Petrovich").withLastname("Petrov")
                            .withAddress("Углич").withMobile("+7(495)577-05-15").withEmail("p@m.ru");
                    app.hbm().createContact(contactToUse);
                    groupToUse = allGroups.get(0);
                }
            }
        }

        var contactsInGroupBefore = app.hbm().getContactsInGroup(groupToUse);
        app.contacts().addContactToGroup(contactToUse, groupToUse);
        var contactsInGroupAfter = app.hbm().getContactsInGroup(groupToUse);
        var addedContacts = contactsInGroupAfter.stream().filter(c -> !contactsInGroupBefore.contains(c)).toList();
        var addedContactId = addedContacts.get(0).id();
        var expectedList = new ArrayList<>(contactsInGroupBefore);
        expectedList.add(contactToUse.withId(addedContactId));
        Assertions.assertEquals(Set.copyOf(contactsInGroupAfter), Set.copyOf(expectedList));
    }

}

