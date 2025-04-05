package multi.user.chatapp.network;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread{
    Socket clientSocket;
    InputStream in;
    OutputStream out;
    Server server;
    PrintWriter pr;
    
    public ServerWorker(Socket clientSocket, Server server) throws IOException{
        this.clientSocket = clientSocket;
        this.in = clientSocket.getInputStream();
        this.out = clientSocket.getOutputStream();
        this.server = server;
        this.pr = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("New Client Thread Active");
    }

    @Override
    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try{
            while (true){
                line = br.readLine();

                if (line.equalsIgnoreCase("quit")){break;}

                for (ServerWorker worker: server.workers){
                    worker.pr.println(line);
                    // String lines = line+ "\n";
                    // worker.out.write(lines.getBytes());
                    // lines.replace("\n", "");
                    // System.out.println("Message Broadcasted");

                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{

                if (br!=null){br.close();}
                if(in!=null){in.close();}
                if(out!=null){out.close();}
                if (clientSocket!=null){clientSocket.close();}
                if(pr!=null){pr.close();}

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
