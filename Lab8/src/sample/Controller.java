package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;


public class Controller {
    @FXML
    private TableView tabView;
    @FXML
    private Button add;
    @FXML
    private TextField studID;
    @FXML
    private TextField assignment;
    @FXML
    private TextField midterm;
    @FXML
    private TextField finals;

    private File currentFilename;

    ObservableList<StudentRecord> data = DataSource.getAllMarks();
    @FXML
    private void initialize() {
        tabView.setItems(data);
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new StudentRecord(studID.getText(),
                        Float.parseFloat(assignment.getText()) ,
                        Float.parseFloat(midterm.getText()),
                        Float.parseFloat(finals.getText())));
                studID.clear();
                assignment.clear();
                midterm.clear();
                finals.clear();
            }
        });
    }

    @FXML
    private void onExitClick(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    private void newClick(ActionEvent e){
        tabView.getItems().clear();
    }

    @FXML
    private void save(ActionEvent e) throws IOException {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(currentFilename));
            for(StudentRecord students:data){
                String mark = students.getStudID() + ", " + students.getAssignment() + ", " +
                        students.getMidterm() +", " + students.getFinalExam() + "\n";
                writer.write(mark);
            }

        } catch (Exception e2){
            e2.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }

    }

    @FXML
    private void saveAs(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        currentFilename = fileChooser.showOpenDialog(tabView.getScene().getWindow());
        save(e);
    }
    @FXML
    private void onOpenClick(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        currentFilename = fileChooser.showOpenDialog(tabView.getScene().getWindow());
        if(currentFilename != null) {
            load();
        }
    }

    private void load() {
        String line = "";
        data = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(currentFilename));
            while((line = br.readLine()) != null) {
                String[] marks = line.split(",");
                StudentRecord students = new StudentRecord(marks[0],Float.parseFloat(marks[1]),
                        Float.parseFloat(marks[2]),Float.parseFloat(marks[3]));
                data.add(students);
            }
            tabView.setItems(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
