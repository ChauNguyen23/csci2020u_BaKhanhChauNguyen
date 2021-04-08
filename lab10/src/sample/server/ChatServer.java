package sample.server;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class ChatServer {
    protected Socket clientSocket           = null;
    protected ServerSocket serverSocket     = null;
    protected ChatServerThread[] threads    = null;
    protected int numClients                = 0;
    protected String messages               = new String("Original message");

    public static int SERVER_PORT;
    public static int MAX_CLIENTS = 25;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.SERVER_PORT = port;
    }
    public void loadServer() {
        try {
            System.out.println("---------------------------");
            System.out.println("Chat Server Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+SERVER_PORT);
            threads = new ChatServerThread[MAX_CLIENTS];
            var thread = new Thread(() ->{
                try{
                    while(true) {
                        clientSocket = serverSocket.accept();
                        threads[numClients] = new ChatServerThread(clientSocket, messages);
                        threads[numClients].start();
                        numClients++;
                    }
            }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            thread.start();
        } catch (Exception e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public void setMessage(String message) {
        messages = message;
    }


    public static void main(String[] args){
        int port = 10000;
        try {
            ChatServer fileShareServer = new ChatServer(port);
            fileShareServer.loadServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

