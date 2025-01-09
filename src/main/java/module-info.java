module br.ufrn.imd.projeto_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.logging;


    opens br.ufrn.imd.view to javafx.fxml;
    opens br.ufrn.imd.controller to javafx.fxml;
    exports br.ufrn.imd.view;
    exports br.ufrn.imd.controller;
}