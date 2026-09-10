package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData().withFirstname("Ivan").withMiddlename("Ivanych")
                            .withLastname("Ivanov").withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData().withFirstname("Ivan").withMiddlename("Ivanych")
                            .withLastname("Ivanov").withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("group name").withHeader("group header").withFooter("group footer"));
        }

        var groupToUse = app.hbm().getGroupList().get(0);
        var contactToUse = app.hbm().getContactList().get(0);

        var currentContactGroups = app.hbm().getGroupsInContact(contactToUse);
        if (!currentContactGroups.contains(groupToUse)) {
            app.contacts().addContactToGroup(contactToUse, groupToUse);
        }

        app.contacts().removeContactFromGroup(contactToUse, groupToUse);
        var groupsAfterRemove = app.hbm().getGroupsInContact(contactToUse);
        Assertions.assertFalse(groupsAfterRemove.contains(groupToUse));
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
                    new ContactData().withFirstname("Ivan").withMiddlename("Ivanych")
                            .withLastname("Ivanov").withAddress("Москва").withMobile("+7(495)577-05-13").withEmail("q@m.ru"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}
