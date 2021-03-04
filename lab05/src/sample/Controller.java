package sample;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML
    private TableView tabView;

    @FXML
    private void initialize() {
        tabView.setItems(DataSource.getAllMarks());
    }
}
