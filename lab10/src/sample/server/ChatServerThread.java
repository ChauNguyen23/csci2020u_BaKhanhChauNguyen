package sample.server;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatServerThread extends Thread {
    protected Socket socket;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;
    public static String messages;

    public ChatServerThread(Socket socket, String messages) {
        super();
        this.socket = socket;
        this.messages = messages;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

    public void run() {
        // initialize interaction
        out.println("Connected to Chat Server");
        String line;
        try {
            while((line = in.readLine()) != null) {
                System.out.println(line);
                setMessages(line);
            }
        }catch(IOException e) {
            System.err.println("Error reading command from socket.");
        }

        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void setMessages(String message) {
        messages = message;
    }

    public static String getMessages(){
        return messages;
    }


}