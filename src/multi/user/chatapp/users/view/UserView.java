package multi.user.chatapp.users.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import multi.user.chatapp.users.dao.UserDAO;
import multi.user.chatapp.users.dto.UserDTO;
import multi.user.chatapp.utils.Userinfo;

public class UserView {
    public JFrame userViewFrame;
    private JTextField userIdText;
    private JPasswordField passwordFieldText;
    private JButton loginButton;

    public UserView(){
        initialize();
    }

    public static void main (String[] args){
        UserView userView = new UserView();
        userView.userViewFrame.setVisible(true);
    }

    public void doLogin(){
        String userid = userIdText.getText();
        char[] password = passwordFieldText.getPassword();

        UserDTO userDTO = new UserDTO(userid, password);
        UserDAO userDAO = new UserDAO();

        try{
            String fullName = userDAO.isLogin(userDTO);

            if(fullName.length()>0){
                JOptionPane.showMessageDialog(userViewFrame, "Welcome " + fullName);
                Userinfo.USER_NAME = fullName;
                Userinfo.USER_ID = userid;
                userViewFrame.setVisible(false);
                userViewFrame.dispose();
                DashBoard dashboardObj = new DashBoard(userid);
                dashboardObj.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(userViewFrame, "Entered User Id or Password is Invalid!");
            }

        }catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e){
            System.out.println("DB Error");
            e.printStackTrace();
        }

    }

    public void initialize(){
        userViewFrame = new JFrame();
        userViewFrame.setBackground(new Color(255, 255, 255));
        userViewFrame.setBounds(100, 100, 750, 360);
        userViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        userViewFrame.setResizable(false);
        userViewFrame.setTitle("Login Window");

        userViewFrame.getContentPane().setBackground(new Color(240, 248, 255));
        userViewFrame.getContentPane().setLayout(null);

        JLabel heading = new JLabel("Enter User Details");
        heading.setBounds(110, 32, 209, 44);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Lucida Grande", Font.BOLD, 22));
        userViewFrame.getContentPane().add(heading);

        JLabel userid = new JLabel("User Id :");
        userid.setHorizontalAlignment(SwingConstants.RIGHT);
        userid.setFont(new Font("PT Sans", Font.PLAIN, 16));
        userid.setBounds(40, 128, 83, 16);
        userViewFrame.getContentPane().add(userid);

        JLabel password = new JLabel("Password");
        password.setHorizontalAlignment(SwingConstants.RIGHT);
        password.setFont(new Font("PT Sans", Font.PLAIN, 16));
        password.setBounds(40, 177, 83, 16);
        userViewFrame.getContentPane().add(password);

        userIdText = new JTextField();
        userIdText.setBounds(147, 122, 227, 26);
        userIdText.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        userIdText.setHorizontalAlignment(SwingConstants.CENTER);
        userIdText.setColumns(10);
        userIdText.setBorder(new LineBorder(Color.black, 1, true));
        userIdText.setToolTipText("Enter Username");
        userViewFrame.getContentPane().add(userIdText);

        passwordFieldText = new JPasswordField();
        passwordFieldText.setBounds(147, 171, 227, 26);
        passwordFieldText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordFieldText.setToolTipText("Enter Password");
        passwordFieldText.setBorder(new LineBorder(Color.black, 1, true));
        passwordFieldText.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        passwordFieldText.setColumns(10);
        userViewFrame.getContentPane().add(passwordFieldText);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(91, 245, 125, 35);
        loginButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                doLogin();
            }
        });
        userViewFrame.getContentPane().add(loginButton);

        JButton register = new JButton("REGISTER");
        register.setBounds(232, 245, 125, 35);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        register.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        register.setBackground(new Color(0, 0, 0));
        register.setForeground(new Color(255, 255, 255));
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                userViewFrame.setVisible(false);
                userViewFrame.dispose();
                Register registerObj = new Register();
                registerObj.setVisible(true);
            }
        });
        userViewFrame.getContentPane().add(register);

        ImageIcon i1 = new ImageIcon(UserView.class.getResource("assets/UserLogin.png"));
        JLabel img = new JLabel(i1);
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setBounds(456, 27, 250, 275);
        userViewFrame.getContentPane().add(img);
    }
}
