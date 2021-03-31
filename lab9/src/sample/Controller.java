package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    @FXML
    Canvas canvas;
    @FXML
    private GraphicsContext gc;
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        URLLoader data1 = new URLLoader();
        ArrayList<Double> list1 = data1.downloadStockPrice("GOOG");
        URLLoader data2 = new URLLoader();
        ArrayList<Double> list2 = data2.downloadStockPrice("AAPL");
        drawLinePlot(list1,list2);
    }

    public void drawLinePlot(ArrayList<Double> list1, ArrayList<Double> list2){
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,50,50,750); // y-axis
        gc.strokeLine(50,750,950,750); // x-axis
        double maxValue1 = Collections.max(list1);
        double maxValue2 = Collections.max(list2);
        double maxValue = Math.max(maxValue1,maxValue2);
        linePlot(list1,Color.RED,maxValue);
        linePlot(list2,Color.BLUE,maxValue);
    }

    public void linePlot(ArrayList<Double> data, Color color, double maxValue) {
        int plotHeight = 650;
        int plotWidth = 900;
        double initialHeight = 750;
        double initialWidth = 50;
        ArrayList<Double> scaledData = new ArrayList<>();
        for(int i=0;i<data.size();i++){
            double scaledValue = data.get(i)/maxValue;
            scaledData.add(scaledValue);
            System.out.printf("%f \n", scaledValue);
        }
        for(int j=0;j<scaledData.size()-1;j++){
            double scaledValue1 = scaledData.get(j)* plotHeight;
            double scaledValue2 = scaledData.get(j+1)*plotHeight;
            double pointDistance = plotWidth/scaledData.size();
            gc.setStroke(color);
            gc.strokeLine(initialWidth,initialHeight-scaledValue1,initialWidth+ pointDistance,
                    initialHeight-scaledValue2);
            initialWidth+= pointDistance;

        }
    }
}
