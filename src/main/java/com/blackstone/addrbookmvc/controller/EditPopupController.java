package com.blackstone.addrbookmvc.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import com.blackstone.addrbookmvc.model.Contact;
import java.util.Arrays;

/**
 * Secondary controller for new/edit functions
 */
public class EditPopupController {

    private boolean okIsClicked = false;

    private Stage editStage;

    private Contact contact;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField; // to hold list of US States and Territories

    @FXML
    private TextField zipField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    // ------ BEGIN CLASS METHODS ------//

    @FXML
    private void initialize() {
    }

    /**
     * Set the stage for this popup from another class
     *
     * @param editStage JavaFX Stage
     */
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    /**
     * If a person hits "edit", the fields in the popup will be filled with present values
     * @param contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        streetField.setText(contact.getStreet());
        stateField.setText(contact.getState());        // ChoiceBox set to current selection
        cityField.setText(contact.getCity());
        zipField.setText(((Integer) contact.getZip()).toString());
        phoneField.setText(contact.getPhone());
        emailField.setText(contact.getEmail());
    }

    /**
     * Validate inputs and require at least a first and last name
     * @return boolean
     */
    private boolean inputIsValid() {
        String err = "";
        if(firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            err = "First Name Required";
        }

        if(lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            err = "Last Name Required";
        }

        if(zipField.getText() == null || zipField.getText().length() == 0) {
            err = "Zip Code Required";
        } else {
            try {
                Integer.parseInt(zipField.getText());
            } catch(NumberFormatException e) {
                err = "Zip code must be numbers 0-9 only!";
            }
        }
        // check that zip is an int
        if(err.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editStage);
            alert.setTitle("Invalid Entries");
            alert.setHeaderText("Please correct your inputs to add a new contact!");
            alert.setContentText(err);
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Called when cancel clicked, closes popup
     */
    @FXML
    private void cancelHandler() {
        editStage.close();
    }

    /**
     * Determines whether OK button clicked or not
     * @return boolean
     */
    public boolean isOkButtonClicked() {
        return okIsClicked;
    }

    /**
     * Called when OK button clicked
     * Passes new or updated data
     */
    @FXML
    private void okHandler() {
        if(inputIsValid()) {
            contact.setFirstName(firstNameField.getText());
            contact.setLastName(lastNameField.getText());
            contact.setStreet(streetField.getText());
            contact.setCity(cityField.getText());
            contact.setState(stateField.getText());
            contact.setZip(Integer.parseInt(zipField.getText()));
            contact.setPhone(phoneField.getText());
            contact.setEmail(emailField.getText());

            okIsClicked = true;
            editStage.close();
        }
    }

}
