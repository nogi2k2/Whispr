package multi.user.chatapp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

public class ClientWorker extends Thread{
    JTextArea textArea;
    InputStream in;
    public ClientWorker(JTextArea textArea, InputStream in){
        this.textArea = textArea;
        this.in = in;
    }

    @Override
    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try{

            while (true){
                line = br.readLine();
                textArea.setText(textArea.getText() + line + "\n\n");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{

                if (in!=null){in.close();}

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
