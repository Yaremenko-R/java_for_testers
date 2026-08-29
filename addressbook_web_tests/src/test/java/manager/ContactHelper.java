package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        openMainPage();
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openMainPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"), contact.photo());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModification(ContactData contact) {
        click(By.cssSelector("a[href^=\"edit.php?id=" + contact.id() + "\"]"));
    }

    private void openMainPage() {
        click(By.linkText("home"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isContactPresent() {
        openMainPage();
        return !manager.isElementPresent(By.name("selected[]"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openMainPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openMainPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openMainPage();
        var contacts = new ArrayList<ContactData>();
        var elements = manager.driver.findElements(By.name("entry"));
        for (var element : elements) {
            var cells = element.findElements(By.tagName("td"));
            var checkbox = element.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var firstname = cells.get(2).getText();
            var lastname = cells.get(1).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
