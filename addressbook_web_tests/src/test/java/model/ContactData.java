package model;

public record ContactData(String firstname, String middlename, String lastname, String address, String mobile,
                          String email) {
    public ContactData() {
        this("", "", "", "", "", "");
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.address, this.mobile, this.email );
    }

    public ContactData withMiddlename(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname, this.address, this.mobile, this.email );
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.address, this.mobile, this.email );
    }
}



