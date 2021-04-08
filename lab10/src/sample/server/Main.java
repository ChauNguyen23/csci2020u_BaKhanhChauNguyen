package sample.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static ServerController controllerHandler;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("server.fxml"));
        Parent root = loader.load();
        controllerHandler = loader.getController();
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        controllerHandler.init();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
