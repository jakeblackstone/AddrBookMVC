package com.blackstone.addrbookmvc.model;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * This class assists in converting objects to XML representation
 * for persistence. We are using the jakarta JAXB lib
 */
@XmlRootElement(name = "contacts")
public class XMLWrapper{
    private List<Contact> contacts;

    @XmlElement(name = "contact", type = Contact.class)
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
