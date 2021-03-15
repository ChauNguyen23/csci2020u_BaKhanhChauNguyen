package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    // pie chart data
    private static String[]ageGroups = {
        "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
        648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
        Color.AQUA, Color.GOLD, Color.DARKORANGE,
                Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    // bar chart data
    private static double[] avgHousingPricesByYear = {
        247381.0,264171.4,287715.3,294736.1,
                308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
        1121585.3,1219479.5,1246354.2,1295364.8,
                1335932.6,1472362.0,1583521.9,1613246.3
    };


    @FXML
    private Canvas canvas;
    @FXML
    public GraphicsContext gc;
    @FXML
    private void initialize() {
        gc = canvas.getGraphicsContext2D();
        drawPieChart(gc);
        drawBarGraph(0,100, 350, avgCommercialPricesByYear, avgHousingPricesByYear, Color.BLUE, Color.RED);
    }

    public void drawBarGraph(double x, int w, int h, double[] data1, double[] data2, Color color1, Color color2) {
        gc.setFill(color1);

        double maxVal1 = Double.NEGATIVE_INFINITY, minVal1 = Double.MAX_VALUE;
        for (double val : data1) {
            if (val > maxVal1)
                maxVal1 = val;
            if (val < minVal1)
                minVal1 = val;
        }
        double x1 = x + 12;
        double barWidth1 = w / data1.length;
        for (double val : data1) {
            double barHeight = (val / maxVal1) * h;
            gc.fillRect(x1, (h - barHeight), barWidth1, barHeight);
            x1 += 2.2*barWidth1;
        }

        gc.setFill(color2);

        double maxVal2 = Double.NEGATIVE_INFINITY, minVal2 = Double.MAX_VALUE;
        for (double val : data2) {
            if (val > maxVal2)
                maxVal2 = val;
            if (val < minVal2)
                minVal2 = val;
        }

        double barWidth2 = w / data2.length;
        for (double val : data2) {
            double barHeight2 = ((val / maxVal1)) * h;
            gc.fillRect(x, (h - barHeight2), barWidth2, barHeight2);
            x += 2.2*barWidth2;
        }
    }

    private void drawPieChart(GraphicsContext gc) {
        int purchases = 0;
        for (int purchase: purchasesByAgeGroup)
            purchases += purchase;
        GraphicsContext legend = canvas.getGraphicsContext2D();
        GraphicsContext tag = canvas.getGraphicsContext2D();
        double startAngle = 0.0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            double slicePercentage = (double) purchasesByAgeGroup[i] / (double) purchases;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(350, 50, 300, 300, startAngle, sweepAngle, ArcType.ROUND);
            legend.setFill(pieColours[i]);
            legend.fillRect(700, 50 + 30*i,50,20);
            tag.strokeText(ageGroups[i], 760, 65     +30*i);
            startAngle += sweepAngle;
        }

    }
}
