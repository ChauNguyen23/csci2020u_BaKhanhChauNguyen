package sample.server;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;


public class ServerController {

    public static ChatServer serverLog;

    @FXML
    public static TextArea clientMessages;


    public void init() throws IOException {
        serverLog = new ChatServer(10000);
        serverLog.loadServer();
    }

    public static void loadMessage(String message){
        if(clientMessages != null){
            String oldDialog = clientMessages.getText();
            clientMessages.setText(oldDialog + "\n" + message);
        }
        else{
            clientMessages.setText(message);
        }
    }
    @FXML
    private void onExitClick(ActionEvent e) {
        Platform.exit();
    }
}
