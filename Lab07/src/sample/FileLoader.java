package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    private String filename;

    // constructor
    public FileLoader(String filename){
        this.filename = filename;
    }
    // this function returns the array list that contains all of the warning type in the file
    public List<String> loadFile(){
        String line = "";
        List<String> warningColumn = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));

            while ((line = br.readLine())!=null){
                // split each word in the line with a comma
                String[] columns = line.split(",");
                //test the column added to the list
                System.out.println(columns[5]);

                // add warning type in that column to the array list
                warningColumn.add(columns[5]);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return warningColumn;
    }
}