package multi.user.chatapp.users.view;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DashBoard extends JFrame{
    private JPanel contentPane;

    public void logOut(){
        setVisible(false);
        dispose();
        UserView loginWindow = new UserView();
        loginWindow.userViewFrame.setVisible(true);
    }

    public DashBoard(String userid){
        setResizable(false);
        setBounds(100, 100, 801, 533);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle(userid);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu profile = new JMenu("Profile");
        menuBar.add(profile);

        JMenuItem details = new JMenuItem("User Details");
        details.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);;
                dispose();
                new UserDetails();
            }
        });
        profile.add(details);

        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(new ActionListener(){
            @Override
            public void  actionPerformed(ActionEvent e){
                JFrame logoutFrame = new JFrame("Logout");
                if (JOptionPane.showConfirmDialog(logoutFrame, "Confirm Logout?", userid, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    logOut();   
                }
            }
        });
        profile.add(logout);

        JMenu chat = new JMenu("Chat");
        menuBar.add(chat);

        JMenuItem openChat = new JMenuItem("Open Chat");
        openChat.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    setVisible(false);
                    dispose();
                    new ClientChatScreen();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        chat.add(openChat);

        JMenu exit = new JMenu("Exit");
        menuBar.add(exit);

        JMenuItem exitApp = new JMenuItem("Exit App");
        exitApp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame exitFrame = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog(exitFrame, "Confirm Exit?", userid, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        exit.add(exitApp);

        ImageIcon i1 = new ImageIcon(DashBoard.class.getResource("/assets/chit_chat.png"));
        JLabel imgContainer = new JLabel(i1);
        imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
        imgContainer.setBounds(5, 5, 791, 500);
        contentPane.add(imgContainer);

        JLabel heading = new JLabel("Messaging App DashBoard");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        heading.setBounds(99, 6, 599, 52);
        contentPane.add(heading);

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    
}
