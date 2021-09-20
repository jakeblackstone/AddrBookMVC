package com.blackstone.addrbookmvc.controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import javafx.fxml.FXML;
import com.blackstone.addrbookmvc.Main;


public class MenuController
{
    private Main main;  // yet another main reference

    /**
     * This helps link the controller with the active instance of main as it does in other controller
     *
     * @param main
     */
    public void setMainRef(Main main) {
        this.main = main;
    }

    /**
     * Creates an empty address book
     */
    @FXML
    private void newBookHandler() {
        main.getContactObservableList().clear();
        main.setPath(null);     // null = empty book
    }

    /**
     * Opens the file dialog for saving
     */
    @FXML
    private void saveAsHandler() {
        FileChooser choose = new FileChooser();
        FileChooser.ExtensionFilter xmlOnly = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        choose.getExtensionFilters().add(xmlOnly);
        File file = choose.showSaveDialog(main.getStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {     // validity check/force xml format
                file = new File(file.getPath() + ".xml");
            }
            main.saveContactFile(file);
        }
    }

    /**
     * Saves to the current file
     */
    @FXML
    private void saveHandler() {
        File file = main.getFilePath();
        if (file != null) {
            main.saveContactFile(file);
        } else {
            saveAsHandler();
        }
    }

    /**
     * Shows a basic about dialog
     */
    @FXML
    private void aboutHandler() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Address Book - CFM Intern Assessment");
        alert.setHeaderText("About");
        alert.setContentText("Author: Jacob Blackstone\nWebsite: https://jakeblackstone.github.io\nCopyright 2021");
        alert.showAndWait();
    }

    /**
     * Opens system file choosing dialog to choose book
     */
    @FXML
    private void openHandler() {
        FileChooser choose = new FileChooser();

        // This forces the dialog to show only .xml files
        ExtensionFilter xmlOnly = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        choose.getExtensionFilters().add(xmlOnly);
        File file = choose.showOpenDialog(main.getStage()); // Starts actual overlay of dialog and passes file after

        if (file != null) {
            main.loadContactFile(file);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void kill() {
        System.exit(0);
    }
}
