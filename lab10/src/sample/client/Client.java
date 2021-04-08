package sample.client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Frame {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 10000;

    public Client(){
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(socket == null){
            System.err.println("Socket is null");
        }else{
            try{
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                String message = in.readLine();
                System.out.println(message);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void readMessage(String username, String message) {

        out.println(username + ": " + message);
    }

}
