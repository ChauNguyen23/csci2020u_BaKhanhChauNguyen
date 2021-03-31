package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        try {
            MessageClient connection  = new MessageClient("localhost",8001);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while(connection.isAlive() && (line =in.readLine()) != null && !line.equals("\\q")) {
                System.out.println(connection.sendMessage(line));
            }
            if(line != null && line.equals("\\q")) {
                System.out.println(connection.sendMessage(line));
            }

            in.close();
            if(connection.isAlive()){
                connection.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
