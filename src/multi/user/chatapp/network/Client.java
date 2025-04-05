package multi.user.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.swing.JTextArea;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetAddress;
import static multi.user.chatapp.utils.ConfigReader.getValue;

public class Client {
    JTextArea textArea;
    InputStream in;
    OutputStream out;
    PrintWriter pr;
    Socket socket;
    ClientWorker worker;

    public Client(JTextArea textArea) throws UnknownHostException, IOException{
        this.textArea = textArea;
        InetAddress ipaddress = InetAddress.getLocalHost();
        int port = Integer.parseInt(getValue("PORT_NO"));
        socket = new Socket(ipaddress, port);
        in = socket.getInputStream();
        out = socket.getOutputStream();
        readMessages();
    }

    public void readMessages(){
        worker = new ClientWorker(textArea, in);
        worker.start();

    }

    public void sendMessages(String message) throws IOException{
        pr = new PrintWriter(out, true);
        pr.println(message);
    }
}
