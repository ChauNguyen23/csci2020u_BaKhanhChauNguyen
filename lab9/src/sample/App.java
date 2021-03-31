package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;

// lab 9 demo for gradle project
public class App {
    public static void main(String[] args) {
        final String ticker = "GOOG";
        long start = LocalDate.of(2020,1,1).
                atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        long end = LocalDate.of(2021,3,24).
                atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        System.out.printf("Start timestamp: %d\n End timestamp: %d",start, end);
        try {
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/GOOG?peri\n" +
                    "od1=1262322000&period2=1451538000&interval=1mo&events=history&\n" +
                    "includeAdjustedClose=true");
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while((line = rdr.readLine())!= null) {
                lines.append(line);
                lines.append('\n');
            }
            System.out.println(lines.toString()); // print out the whole csv file
            rdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//add main in gradle: mainClass = 'App'
// configuration as gradle
// only need close column