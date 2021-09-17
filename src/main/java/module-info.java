module com.blackstone.addrbookmvc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.prefs;
    requires jakarta.xml.bind;

    opens com.blackstone.addrbookmvc to javafx.fxml, jakarta.xml.bind;
    exports com.blackstone.addrbookmvc;
    exports com.blackstone.addrbookmvc.controller;
    opens com.blackstone.addrbookmvc.controller to javafx.fxml;
    opens com.blackstone.addrbookmvc.model to jakarta.xml.bind;
}