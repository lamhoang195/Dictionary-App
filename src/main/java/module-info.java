module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    exports app;
    exports app.CommandLine;
    opens app.CommandLine to javafx.fxml;
}
