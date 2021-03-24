package midterm2021;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main extends Application {

    Scene mainScene;
    private Canvas canvas1,canvas2,canvas3;
    private double screenWidth = 800;
    private double screenHeight = 600;
    private int frameWidth = 32;
    private int frameHeight = 36;
    private int numVerticalFrames = 8;
    private int numHorizontalFrames = 3;
    private int sourceHeightOffset = 0;
    private int sourceWidthOffset = 0;
    private int frameHorizontalIndex = 0;
    private int frameVerticalIndex = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CSCI2020U - Midterm");
        GridPane grid = new GridPane();
        // main App Scene
        mainScene = new Scene(grid, 800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        canvas1 = new Canvas();
        canvas1.widthProperty().bind(primaryStage.widthProperty());
        canvas1.heightProperty().bind(primaryStage.heightProperty());

        canvas2 = new Canvas();
        canvas2.widthProperty().bind(primaryStage.widthProperty());
        canvas2.heightProperty().bind(primaryStage.heightProperty());

        canvas3 = new Canvas();
        canvas3.widthProperty().bind(primaryStage.widthProperty());
        canvas3.heightProperty().bind(primaryStage.heightProperty());

//      Creating the menu buttons
        Button btApp1 = new Button("Animation");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("2D Graphics");
        btApp2.setPrefWidth(200);
        Button btApp3 = new Button("About");
        btApp3.setPrefWidth(200);

//        setting the Event handlers for each buttons
        btApp1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                      Display the "Animation" in the CENTER,
//                      and a "Back to Main" on the TOP
                GridPane animationGrid = new GridPane();
                animationGrid.setAlignment(Pos.TOP_LEFT);
                Scene animationScene = new Scene(animationGrid, 800, 600);
                // handle event for the Animation button
                btApp1.setOnAction(e->primaryStage.setScene(animationScene));
                // create button to return to the main window
                Button returnMenu = new Button("Back to Main");
                returnMenu.setPrefWidth(200);
                // function that draw the animation on this scene
                drawAnimation(animationGrid);
                //add new button and animation to the layout
                animationGrid.getChildren().add(canvas1);
                animationGrid.getChildren().add(returnMenu);
                returnMenu.setOnAction(e -> primaryStage.setScene(mainScene));

                System.out.println("Clicked on Animation button");
            }
        });

        btApp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "2D Drawing" in the CENTER,
//                    and a "Back to Main" on the TOP
                GridPane scene2 = new GridPane();
                scene2.setAlignment(Pos.CENTER);
                Scene graphicScene = new Scene(scene2, 800, 600);
                // handle event for the Graphic button
                btApp2.setOnAction(e->primaryStage.setScene(graphicScene));
                Button returnMenu = new Button("Back to Main");
                returnMenu.setPrefWidth(200);
                returnMenu.setOnAction(e -> primaryStage.setScene(mainScene));
                // draw the initial on this scene
                drawInitials(scene2);
                scene2.getChildren().add(canvas2);
                scene2.getChildren().add(returnMenu);
                System.out.println("Clicked on Graphics 2D button");
            }
        });

        btApp3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "About" in the CENTER,
//                    and a "Back to Main" on the TOP
                GridPane scene3 = new GridPane();
                scene3.setAlignment(Pos.CENTER);
                Scene aboutScene = new Scene(scene3, 800,600);
                btApp3.setOnAction(e->primaryStage.setScene(aboutScene));
                Button returnMenu = new Button("Back to Main");
                returnMenu.setPrefWidth(200);
                returnMenu.setOnAction(e -> primaryStage.setScene(mainScene));
                // print out the information on this scene
                try {
                    printInfo(scene3);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                scene3.getChildren().add(canvas3);
                scene3.getChildren().add(returnMenu);
                System.out.println("Clicked on About button");
            }
        });

//        Add the menu buttons to the grid
        grid.add(btApp1, 0,1);
        grid.add(btApp2, 0,2);
        grid.add(btApp3, 0,3);


        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void drawAnimation(GridPane root){
        GraphicsContext gc = canvas1.getGraphicsContext2D();
//loading image sprite using relative path
        Image image = new Image(getClass().getClassLoader().getResource("ducks.png").toString());

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1333), new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
//                background rect fpr the characters
                gc.setFill(Color.BLACK);
                gc.fillRect(350, 250, frameWidth, frameHeight);

                gc.drawImage(image, sourceWidthOffset, sourceHeightOffset, frameWidth, frameHeight, 350, 250, frameWidth, frameHeight);

                frameHorizontalIndex = (frameHorizontalIndex + 1) % numHorizontalFrames;
//                we want to vary frameVerticalIndex from 0 to numFrames (not included) using the %


//                calculating the offset height based on the frameIndex
                sourceWidthOffset = frameWidth * frameHorizontalIndex;
                if (frameHorizontalIndex == numHorizontalFrames - 1) {
                    frameVerticalIndex = (frameVerticalIndex + 1) % numVerticalFrames;
                    sourceHeightOffset = frameHeight * frameVerticalIndex;
                }
            }
        }));
//        Starting the timeline
        timeline.playFromStart();

    }

    private void drawInitials(GridPane grid) {
        GraphicsContext gc = canvas2.getGraphicsContext2D();
        // label for letter C
        gc.setFill(Color.BLACK);
        Font font = new Font("Verdana", 18);
        gc.setFont(font);
        gc.fillText("Letter C", 320,370);
// draw letter C
        gc.setFill(Color.RED);
        gc.fillRect(300,200,20,100);

        gc.setFill(Color.RED);
        gc.fillRect(300,200,100,20);

        gc.setFill(Color.RED);
        gc.fillRect(300,300,100,20);

        // label for letter N
        gc.setFill(Color.BLACK);
        gc.setFont(font);
        gc.fillText("Letter N", 500,370);

        // draw letter N
        gc.setFill(Color.BLUE);
        gc.fillRect(450,200,20,120);

        gc.setFill(Color.BLUE);
        gc.fillRect(470,200,24,24);

        gc.setFill(Color.BLUE);
        gc.fillRect(495,224,24,24);

        gc.setFill(Color.BLUE);
        gc.fillRect(519,248,24,24);

        gc.setFill(Color.BLUE);
        gc.fillRect(543,272,24,24);

        gc.setFill(Color.BLUE);
        gc.fillRect(567,296,24,24);

        gc.setFill(Color.BLUE);
        gc.fillRect(591,200,20,120);

    }

    private void printInfo(GridPane grid) throws FileNotFoundException {
        GraphicsContext gc = canvas3.getGraphicsContext2D();
        InputStream inputStream = new FileInputStream("./resources/StudentInfo.xml");
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList1 = doc.getElementsByTagName("info");
            NodeList nodeList2 = doc.getElementsByTagName("software-description");
            for(int i =0;i < nodeList1.getLength();i++){
                Element itemElement = (Element) nodeList1.item(i);

                String id = getTagValue("id", itemElement);
                String name = getTagValue("name",itemElement);
                String email = getTagValue("email",itemElement);

                gc.setFill(Color.BLACK);
                Font font = new Font("Verdana", 18);
                gc.setFont(font);
                gc.fillText("Student id: " + id, 300,300);

                gc.setFill(Color.BLACK);
                gc.setFont(font);
                gc.fillText("Student name: " + name, 300,320);

                gc.setFill(Color.BLACK);
                gc.setFont(font);
                gc.fillText("Student email: " + email, 300,340);

            }
            String description = nodeList2.item(0).getTextContent();
            gc.setFill(Color.BLACK);
            Font font = new Font("Verdana",18);
            gc.setFont(font);
            gc.fillText( description, 250,260);
            inputStream.close();
        }


        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static String getTagValue(String tagName, Element elem){
        NodeList tags = elem.getElementsByTagName(tagName);
        if (tags.getLength()>0){
            return tags.item(0).getTextContent();
        }
        return null;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
