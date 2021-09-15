package com.blackstone.addrbookmvc.model;

import javafx.beans.property.*;
/**
 * This is a POJO class to represent the data model for a Contact
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
     * Constructor for Contact object requiring all params
     * Assumes a US-based address
     * @param firstName Contact's first name
     * @param lastName Contact's last name
     * @param street Contact's residential or work street address
     * @param city Contact's city
     * @param state Contact's state
     * @param zip Contact's zip code
     * @param phone Contact's cell phone - not an int due to different formatting styles
     * @param email Contact's email address
     */
    public Contact(String firstName, String lastName, String street, String city, String state, int zip, String phone, String email) {
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
