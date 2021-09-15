package com.blackstone.addrbookmvc;

import javafx.fxml.FXML;
import com.blackstone.addrbookmvc.Main;
import com.blackstone.addrbookmvc.model.Contact;
import javafx.scene.control.*;
import java.lang.String;

public class ContactController {
    // collection of members for table that will be set with data pulled from model and passed to view

    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    private TableColumn<Contact, String> firstNameCol;

    @FXML
    private TableColumn<Contact, String> lastNameCol;

    @FXML
    private TableColumn<Contact, Integer> zipCol;

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label lastNameLbl;

    @FXML
    private Label streetLbl;

    @FXML
    private Label cityLbl;

    @FXML
    private Label stateLbl;

    @FXML
    private Label zipLbl;

    @FXML
    private Label phoneLbl;

    @FXML
    private Label emailLbl;

    private Main main;      // reference main (app entry point)

    /**
     * Constructor - for convention
     */
    public ContactController() {}

    /**
     * This lets the main refer back to itself
     *
     * @param main
     */
    public void setMainRef(Main main) {
        this.main = main;
        contactTableView.setItems(main.getContactObservableList()); // load the contacts list
    }

    /**
     * This will pass the data from a given Contact obj in ObservableList to the labels on the right pane
     * In the case of an empty/null record, the labels will be blank/empty string
     *
     * @param contact Contact object
     */
    private void contactDataToLabels(Contact contact) {
        if (contact != null) {
            firstNameLbl.setText(contact.getFirstName());
            lastNameLbl.setText(contact.getLastName());
            streetLbl.setText(contact.getStreet());
            cityLbl.setText(contact.getCity());
            stateLbl.setText(contact.getState());
            zipLbl.setText(((Integer) contact.getZip()).toString());     // cast/wrap in Integer object to cast to String
            phoneLbl.setText(contact.getPhone());
            emailLbl.setText(contact.getEmail());
        } else {                                                        // null --> set all to empty
            firstNameLbl.setText("");
            lastNameLbl.setText("");
            streetLbl.setText("");
            cityLbl.setText("");
            stateLbl.setText("");
            zipLbl.setText("");
            phoneLbl.setText("");
            emailLbl.setText("");
        }
    }

    // ------ BEGIN FXML INJECTABLE METHODS ------ //
    /**
     * Like a "constructor" (but not really) for FXML - called after files are loaded
     * Loads data into table, makes use of lambda functions for readability
     */
    @FXML
    private void initialize(){

        firstNameCol.setCellValueFactory(cData -> cData.getValue().getFirstNameProperty());
        lastNameCol.setCellValueFactory(cData -> cData.getValue().getLastNameProperty());
        zipCol.setCellValueFactory(cData -> cData.getValue().getZipProperty().asObject());
        /* the asObject() call is due to a quirk of the JavaFX lib where IntegerProperty
         * implements Property<Number> and thus does not have the auto-unwrap props of Integer etc.
         */

        // blank table on startup
        contactDataToLabels(null); // TODO - Set startup to check if there is contact data saved and load it vs null accordingly

        // adds a listener to the table and updates the labels on the right with current selected record
        contactTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> contactDataToLabels(newValue));
    }

    /**
     * Called when a user clicks on the Delete button, deletes selected contact
     */
    @FXML
    private void deleteContactHandler() {
        int delIndex = contactTableView.getSelectionModel().getSelectedIndex();

        // if the table is empty, the "selected index" the handler is -1
        if(delIndex >= 0) {
            contactTableView.getItems().remove(delIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getStage());
            alert.setTitle("Error: No Selection");
            alert.setHeaderText("No Contact Selected or Empty Table");
            alert.setContentText("Please select a Contact record");
            alert.showAndWait();
        }

    }


}

