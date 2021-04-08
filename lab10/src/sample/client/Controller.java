package sample.client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import sample.server.ServerController;
public class Controller {
    @FXML
    TextField username;

    @FXML
    TextField message;

    Client serverClient = null;

    public void initialize() {
        serverClient = new Client();
    }

    @FXML
    private void onExitClick(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    private void sendMessage(ActionEvent e) {
        String userName = username.getText();
        String clientMessage = message.getText();
        if(userName != null && clientMessage != null) {
            serverClient.readMessage(userName,clientMessage);
            username.clear();
            message.clear();
        }
        else {
            System.err.println("There is no username or message given.");
        }


    }
}
