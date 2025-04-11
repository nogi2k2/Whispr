package multi.user.chatapp.users.view;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.net.UnknownHostException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.Font;

import multi.user.chatapp.network.Client;
import multi.user.chatapp.users.validation.InputValidation;
import multi.user.chatapp.utils.Userinfo;

public class ClientChatScreen extends JFrame{
    private JScrollPane messageScrollPane;
    private JPanel contentPane;
    private JTextArea displayTextArea;
    private JTextArea messageTextArea;
    private Client client;

    public void sendMsg(){
        String message = messageTextArea.getText();

        if (!InputValidation.lengthCheckValidateClientChatScreenText(message)){
            System.out.println("Invalid Message");
        }else{
            try{
                client.sendMessages(Userinfo.USER_NAME + " - " + message);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void focus(){
        messageScrollPane.setViewportView(messageTextArea);
        EventQueue.invokeLater(() -> messageTextArea.requestFocusInWindow());
    }

    public ClientChatScreen() throws UnknownHostException, IOException{
        messageScrollPane = new JScrollPane();
        displayTextArea = new JTextArea();
        messageTextArea = new JTextArea();
        client = new Client(displayTextArea);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);;

        JMenu option = new JMenu("Chat");
        menuBar.add(option);

        JMenuItem exitChat = new JMenuItem("Exit");
        exitChat.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                setVisible(false);
                dispose();
                DashBoard dashBoard = new DashBoard(Userinfo.USER_ID);
                dashBoard.setVisible(true);
            }
        });
        option.add(exitChat);

        displayTextArea.setEditable(false);;
        displayTextArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        displayTextArea.setBounds(20, 02, 645, 250);
        displayTextArea.setLineWrap(true);

        JScrollPane displayScrollPane = new JScrollPane();
        displayScrollPane.setViewportView(displayTextArea);
        displayScrollPane.setBounds(20, 6, 658, 200);
        contentPane.add(displayScrollPane);

        JButton sendMessage = new JButton("SEND");
        sendMessage.setBounds(560, 308, 117, 29);
        sendMessage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                sendMsg();
                messageTextArea.setText("");
                focus();
            }
        });
        contentPane.add(sendMessage);

        messageTextArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        messageTextArea.setBounds(30, 303, 518, 39);
        messageTextArea.setLineWrap(true);

        messageScrollPane = new JScrollPane();
        messageScrollPane.setBounds(30, 303, 518, 39);
        contentPane.add(messageScrollPane);

        focus();
        setVisible(true);
        setBounds(100, 100, 700, 417);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Userinfo.USER_NAME);
    }
}
