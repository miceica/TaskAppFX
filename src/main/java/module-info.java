module com.ceica.taskappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ceica.taskappfx to javafx.fxml;
    exports com.ceica.taskappfx;
    exports com.ceica.taskappfx.controllerViews;
    opens com.ceica.taskappfx.controllerViews to javafx.fxml;
}