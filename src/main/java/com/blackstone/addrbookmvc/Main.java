package com.blackstone.addrbookmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // stage and root objects for program execution

    private Stage stage;            // stage is the main/base/root container of a JavaFX app
    private BorderPane rootView;    // we add this member to cast the root as BorderPane

    /**
     * Overrides JavaFX start() to initialize root layout and call overlays
     * @param stage primary stage object
     * @throws IOException if something causes the View to not load
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML to application
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("view/RootWrapper.fxml"));
        rootView = (BorderPane) fxmlLoader.load();
        // we cast the loaded root fxml as BorderPane to match scene builder as we pass to Scene

        // Set up and display scene
        Scene scene = new Scene(rootView, 320, 240);
        this.stage.setTitle("Address Book");
        this.stage.setScene(scene);
        this.stage.show();

        // overlay the contact view onto root
        overlayContactView();
    }

    /**
     * Loads and overlays Contact View onto the root stage & scene
     * @throws IOException if the contact view cannot load
     */
    public void overlayContactView() throws IOException {
        FXMLLoader contactViewLoader = new FXMLLoader();                // same idea as root view
        contactViewLoader.setLocation(Main.class.getResource("view/ContactView.fxml"));
        AnchorPane contactView = (AnchorPane) contactViewLoader.load(); // cast loaded fxml to AnchorPane and store
        rootView.setCenter(contactView);                               // center the contactView on root
    }

    /**
     * A getter for the stage in case it needs to be accessed outside the class
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * A getter for the root view (cast as BorderPane) in case it needs to be accessed outside the class
     * @return
     */
    public BorderPane getRootView() {
        return rootView;
    }


    public static void main(String[] args) {
        launch(args);
    }
}