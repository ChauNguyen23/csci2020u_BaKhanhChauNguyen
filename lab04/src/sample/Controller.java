package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;


public class Controller {
    @FXML private PasswordField passwordField;
    @FXML private TextField username;
    @FXML private Text actiontarget;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phoneNo;
    @FXML private DatePicker bornDate;
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        //System.out.println(passwordField);
        String userName = username.getText();
        String password = passwordField.getText();
        String realName = fullName.getText();
        String emailAddress = email.getText();
        String phoneNumber = phoneNo.getText();
        LocalDate dateOfBirth = bornDate.getValue();
        actiontarget.setText("Username: " + userName + "\n"
                + "Password: " + password + "\n"
                + "Full name: " + realName + "\n"
                + "Email address: " + emailAddress + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Date of Birth: " + dateOfBirth);
    }
}
