package sample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

public class URLLoader {
    public static ArrayList<Double> downloadStockPrice(String ticker) {
        ArrayList<String[]> closePrice = new ArrayList<>();
        try{
            long start = LocalDate.of(2010,1,1).
                    atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
            long end = LocalDate.of(2015,12,31).
                    atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
            String sURL = "https://query1.finance.yahoo.com/v7/finance/download/" + ticker+
                "?period1="+ start + "&period2=" + end + "&interval=1mo&events=history&" +
                "includeAdjustedClose=true";
            URL netURL = new URL(sURL);
            URLConnection conn = netURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            InputStream inStream = conn.getInputStream();
            Scanner s = new Scanner(inStream);
            String result = "";
            while(s.hasNext()){
                String line = s.nextLine();
                result += line + "\n";
                String[] columns = line.split(",");
                closePrice.add(columns);
            }

            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Double> closeList = new ArrayList<>();
        boolean first= true;
        for(int i = 0; i < closePrice.size(); i++){
            if(!first){
                closeList.add(Double.valueOf(closePrice.get(i)[4]));
                System.out.println(closeList.get(i-1));
            } else {
                first = false;
            }
        }
        return closeList;
    }
}
