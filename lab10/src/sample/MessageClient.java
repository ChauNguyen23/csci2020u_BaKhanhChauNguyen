package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageClient {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;


    public MessageClient(String hostName,int port) throws IOException {
        this.socket = new Socket(hostName,port);
        this.out = new PrintWriter(this.socket.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public boolean isAlive() {
        return socket.isConnected();
    }

    public String sendMessage(String msg) {
        String res = null;
        out.println(msg);
        try {
            res = in.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return res;
    }

    public void close() {
        try {
            in.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }

        out.close();
        if(!socket.isClosed()){
            try {
                socket.close();
            }
            catch (IOException e) {
                 System.err.println(e.getMessage());
             }
        }
    }
}
