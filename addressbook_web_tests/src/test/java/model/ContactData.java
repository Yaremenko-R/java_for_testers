package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String address,
                          String mobile, String email, String home, String work) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work);
    }

    public ContactData withMiddlename(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.address, this.mobile, this.email, this.home, this.work);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.address, this.mobile, this.email, this.home, this.work);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, address, this.mobile, this.email, this.home, this.work);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, mobile, this.email, this.home, this.work);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, email, this.home, this.work);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, home, this.work);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.address, this.mobile, this.email, this.home, work);
    }

}



