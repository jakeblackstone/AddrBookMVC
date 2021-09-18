package com.blackstone.addrbookmvc.model;

import javafx.beans.property.*;
/**
 * This is a  class to represent the data model for a Contact
 * We use JavaFX/JavaBeans Property wrappers for members for easier event handling
 */
public class Contact {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final StringProperty state;
    private final IntegerProperty zip;
    private final StringProperty phone;
    private final StringProperty email;

    /**
     * Default constructor
     */
    public Contact() {
        this(null,null,null,null,null,0,null,null);
    }


    /**
     * Constructor for Contact object requiring all parameters (can be "")
     * Assumes a US-based address
     * @param firstName Contact's first name
     * @param lastName Contact's last name
     * @param zip Contact's zip code
     */
    public Contact(String firstName, String lastName, String street, String city, String state, int zip, String email, String phone) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zip = new SimpleIntegerProperty(zip);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

    // GETTERS AND SETTERS
    // Properties use get() and set() to access wrapped members
    // there is an extra getter for Properties

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty getStreetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty getCityProperty() {
        return city;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public StringProperty getStateProperty() {
        return state;
    }

    public int getZip() {
        return zip.get();
    }

    public void setZip(int zip) {
        this.zip.set(zip);
    }

    public IntegerProperty getZipProperty() {
        return zip;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty getEmailProperty() {
        return email;
    }


}
