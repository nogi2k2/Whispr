package multi.user.chatapp.network;

import java.util.ArrayList;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import static multi.user.chatapp.utils.ConfigReader.getValue;

public class Server{
    ServerSocket serverSocket;
    ArrayList<ServerWorker> workers = new ArrayList<>();

    public Server() throws IOException{
        int port = Integer.parseInt(getValue("PORT_NO"));
        serverSocket = new ServerSocket(port);
        System.out.println("Server Active and listening for Clients");
        handleClientRequest();
    }

    public void handleClientRequest() throws IOException{
        while (true){
            Socket clientSocket = serverSocket.accept();
            ServerWorker serverWorker = new ServerWorker(clientSocket, this);
            workers.add(serverWorker);
            serverWorker.start();
        }
        
    }

    public static void main(String[] args) throws IOException{
        new Server();
    }
}