package com.blackstone.addrbookmvc;

import com.blackstone.addrbookmvc.controller.ContactController;
import com.blackstone.addrbookmvc.controller.EditPopupController;
import com.blackstone.addrbookmvc.controller.MenuController;
import com.blackstone.addrbookmvc.model.XMLWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.prefs.Preferences;
import com.blackstone.addrbookmvc.model.Contact;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

    // stage and root objects for program execution

    private Stage stage;            // stage is the main/base/root container of a JavaFX app
    private BorderPane rootView;    // we add this member to cast the root as BorderPane

    /**
     * This is the main list that will hold the contact data in memory during runtime
     * Observable so that the controller may update the view as changes are made to data
     */
    private final ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

    /**
     * Overrides JavaFX start() to initialize root layout and call overlays
     * @param stage primary stage object
     * @throws IOException if something causes the View to not load
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML to application
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/RootWrapper.fxml"));
        rootView = loader.load();

        // set up new scene
        this.stage.setTitle("Address Book");
        this.stage.setScene(new Scene(rootView));

        // controller access - Menu is attached to root pane
        MenuController controller = loader.getController();
        controller.setMainRef(this);
        this.stage.show();

        // attempt auto-load of most recent book
        File file = getFilePath();
        if (file != null) {
            loadContactFile(file);
        }

        // overlay the contact controller onto root
        overlayContactView();


    }

    /**
     * Loads and overlays Contact View onto the root stage & scene
     * @throws IOException if the contact view cannot load
     */
    public void overlayContactView() throws IOException {
        FXMLLoader contactViewLoader = new FXMLLoader(Main.class.getResource("view/ContactView.fxml"));              // same idea as root controller
        AnchorPane contactView = contactViewLoader.load();                  // cast loaded fxml to AnchorPane and store
        rootView.setCenter(contactView);                                    // center the contactView on root
        ContactController controller = contactViewLoader.getController();   // access controller
        controller.setMainRef(this);                                        // pass reference of this instance to the controller
    }

    /**
     * Overlays the edit popup and updates the view only if OK is hit
     * @param contact
     * @return boolean if OK was pressed or not
     * @throws IOException if the edit popup fails to load
     */
    public boolean overlayEditView(Contact contact){
        try {
            FXMLLoader popupLoader = new FXMLLoader(Main.class.getResource("view/EditPopupView.fxml"));
            AnchorPane popup = popupLoader.load();

            // will need a new stage
            Stage editStage = new Stage();
            editStage.setTitle("Edit/Add Contact");
            editStage.initModality(Modality.WINDOW_MODAL);      // Window modaility sets up the framework for a new window that will not
            editStage.initOwner(stage);                     // -> automatically send events to any other window
            editStage.setScene(new Scene(popup));

            // Get a reference to the appropriate controller
            EditPopupController controller = popupLoader.getController();
            controller.setEditStage(editStage);
            controller.setContact(contact);
            editStage.showAndWait();

            return controller.isOkButtonClicked();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * A getter for the stage in case it needs to be accessed outside the class
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * A getter for the root view in case it needs to be accessed outside the class
     * @return BorderPane
     */
    public BorderPane getRootView() {
        return rootView;
    }

    /**
     * A getter for the contact observable list
     * @return ObservableList
     */
    public ObservableList<Contact> getContactObservableList() {
        return contactObservableList;
    }

    // ------ Data Persistence helpers ------//

    /**
     * Retreives most recent file path to load to address book
     * @return File
     */
    public File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String path = prefs.get("filePath", null);
        if (path != null) {
            System.out.println(path);
            return new File(path);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the current file and sets it as part of stage title
     * @param file saved file
     */
    public void setPath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            stage.setTitle("Address Book - " + file.getName());
        } else {
            prefs.remove("filePath");
            stage.setTitle("Address Book");
        }
    }

    /**
     * Loads a specific file to application
     * @param file
     */
    public void loadContactFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Unmarshaller unmarsh = context.createUnmarshaller();
            XMLWrapper wrap = (XMLWrapper) unmarsh.unmarshal(file); // Read and convert FROM xml
            contactObservableList.clear();
            contactObservableList.addAll(wrap.getContacts());
            setPath(file);   // Save path
        } catch (JAXBException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load data!");
            alert.setContentText("Failed to load data from file:\n" + file.getPath());
            alert.showAndWait();

        }
    }

    /**
     * Saves contact data to file
     * @param file
     */
    public void saveContactFile(File file) {
        try {
            XMLWrapper wrap = new XMLWrapper();
            JAXBContext context = JAXBContext.newInstance(XMLWrapper.class);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            wrap.setContacts(contactObservableList);
            marsh.marshal(wrap, file);      // convert TO xml
            setPath(file);                  // Save path.
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to save data!");
            alert.setContentText("Failed to save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    /**
     * Main constructor - can manually add contacts for testing purposes
     */
    public Main() {
        //contactObservableList.add(new Contact("Jacob", "Blackstone", "914 E Lemon St", "Tempe", "AZ", 85281, "0", "@"));
    }

    public static void main(String[] args) {
        launch(args);   // called to start JavaFX app
    }
}