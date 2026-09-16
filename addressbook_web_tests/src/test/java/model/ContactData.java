package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String address,
                          String mobile, String email, String home, String work, String email2, String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withMiddlename(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.address, this.mobile, this.email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, address, this.mobile, this.email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, mobile, this.email, this.home, this.work, this.email2, this.email3);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, email, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, email, this.home, this.work, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, email, this.home, this.work, this.email2, email3);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, home, this.work, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, work, this.email2, this.email3);
    }

}



