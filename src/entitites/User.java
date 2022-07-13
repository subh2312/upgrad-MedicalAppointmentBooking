package entitites;

import java.util.Date;

public class User {

    public static class Address {
        private String city;
        private String locality;
        private String state;
        private int pinCode;

        public Address(String city, String locality, String state, int pinCode) {
            this.city = city;
            this.locality = locality;
            this.state = state;
            this.pinCode = pinCode;
        }

        public Address(Address obj) {
            this.city = obj.city;
            this.locality = obj.locality;
            this.state = obj.state;
            this.pinCode = obj.pinCode;
        }

        public String getCity() {
            return city;
        }

        public String getLocality() {
            return locality;
        }

        public String getState() {
            return state;
        }

        public int getPinCode() {
            return pinCode;
        }
    }

    public static class Contact {
        private String email;
        private String phone;

        public Contact(String email, String phone) {
            this.email = email;
            this.phone = phone;
        }

        public Contact(Contact obj){
            this.email = obj.email;
            this.phone = obj.phone;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }

    public static int counter = 0;
    private int userId;

    private String firstName;
    private String lastName;
    private Address address;
    private Contact contact;

    private Date registrationTimestamp;

    public User(String firstName, String lastName, Address address, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = new Address(address);
        this.contact = new Contact(contact);
        this.registrationTimestamp = new Date();
        this.userId = counter;
        counter++;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public Date getRegistrationTime() {
        return registrationTimestamp;
    }

}
