module com.blackstone.addrbookmvc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens com.blackstone.addrbookmvc to javafx.fxml;
    exports com.blackstone.addrbookmvc;
}