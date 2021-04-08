package sample.server;

import sample.client.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MessageServer {
    public static void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            LinkedList<Thread> threads = new LinkedList<Thread>();
            Thread socketThread = new Thread(() -> {
                try {
                    while(true) {
                        Socket newSocket = serverSocket.accept();
                        ClientHandler handler =  new ClientHandler(newSocket);
                        Thread t = new Thread(handler);
                        t.start();
                        threads.add(t);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String line;
            socketThread.start();
            while((line = in.readLine()) == null || !line.equals("\\q")); // take care of the console input
            in.close();
            socketThread.interrupt();
            while(!threads.isEmpty()){
                threads.remove().interrupt();
            }
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
