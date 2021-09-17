package com.blackstone.addrbookmvc;

import com.blackstone.addrbookmvc.controller.ContactController;
import com.blackstone.addrbookmvc.controller.EditPopupController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import com.blackstone.addrbookmvc.model.Contact;
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
        this.stage.show();

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
     * A getter for the root controller (cast as BorderPane) in case it needs to be accessed outside the class
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

    /**
     * Main constructor - can manually add contacts for testing purposes
     */
    public Main() {
        //contactObservableList.add(new Contact("Hans", "Muster", "914 E Lemon St", "Tempe", "AZ", 85281, "0", "@"));
    }

    public static void main(String[] args) {
        launch(args);   // called to start JavaFX app
    }
}