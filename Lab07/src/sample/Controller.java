package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.*;


public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    // set of colouts for the pie chart
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON
    };
    // create a list of names of warning type
    private static String[] warningName = getCount("./weatherwarnings-2015.csv").keySet().toArray(new String[0]);

    // this function returns a map that contains the names of the warning type and their counts in alphabet order
    private static Map<String, Integer> getCount(String filePath){
        FileLoader warningType = new FileLoader(filePath);
        List<String> listWarning = warningType.loadFile();
        Map<String, Integer> count = new TreeMap();
        // this loop updates the count of each warning type
        for(int i=0;i< listWarning.size();i++){
            if(count.containsKey(listWarning.get(i))){
                int oldCount = count.get(listWarning.get(i));
                count.put(listWarning.get(i), oldCount+1);
            }
            else{
                count.put(listWarning.get(i),1);
            }

        }
        // create a new map to sort the map count above
        Map <String, Integer> sorted = new TreeMap<>();
        sorted.putAll(count);

        return sorted; // return map sorted in alphabet order
    }
    // initialize function to show the output
    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        drawPieChart(gc);
    }

    // this function draws a pie chart based on the map created above
    private void drawPieChart(GraphicsContext gc) {
        int countWarning = 0;
        //get the list of count from the map
        Map<String, Integer> valueList = getCount("./weatherwarnings-2015.csv");
        Collection<Integer> values = valueList.values();
        Integer[] typeCount = values.toArray(new Integer[4]);
        // get the total count
        for (int countWarnings: typeCount)
            countWarning += countWarnings;
        // set up legend and text
        GraphicsContext legend = canvas.getGraphicsContext2D();
        GraphicsContext tag = canvas.getGraphicsContext2D();
        // draw the pie chart, the legend and text
        double startAngle = 0.0;
        for (int i = 0; i < typeCount.length; i++) {
            double slicePercentage = (double) typeCount[i] / (double) countWarning;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(350, 50, 300, 300, startAngle, sweepAngle, ArcType.ROUND);
            legend.setFill(pieColours[i]);
            legend.fillRect(100, 80 + 30*i,50,20);
            tag.strokeText(warningName[i],160,93+30*i);
            startAngle += sweepAngle;
        }

    }


}
