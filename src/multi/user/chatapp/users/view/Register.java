package multi.user.chatapp.users.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import multi.user.chatapp.users.dao.UserDAO;
import multi.user.chatapp.users.dto.UserDTO;
import multi.user.chatapp.users.validation.InputValidation;

public class Register extends JFrame{
    private JTextField fullNameText;
    private JTextField userIdText;
    private JPasswordField passwordField;
    private JPanel contentPane;

    public void doRegister(){
        String fullName = fullNameText.getText();
        String userid = userIdText.getText();
        char[] password = passwordField.getPassword();

        try{

            UserDAO dao = new UserDAO();
            UserDTO dto = new UserDTO(fullName, userid, password);

            if (!InputValidation.lengthCheckValidateFullName(fullName)){
                JOptionPane.showMessageDialog(this, "Invalid Name, Name must be longer than 3 characters");
            }else if (!InputValidation.lengthCheckValidateUserId(userid)){
                JOptionPane.showMessageDialog(this, "Invalid Username, Username must be longer than 6 characters");
            }else{
                int rows = dao.add(dto);

                if(rows == 0){
                    JOptionPane.showMessageDialog(this, "User Id Already Exists, Choose A Different User Id");
                }else if (rows > 0){
                    JOptionPane.showMessageDialog(this, "User Registered Successfully");
                    this.setVisible(false);
                    this.dispose();
                    UserView loginWindow = new UserView();
                    loginWindow.userViewFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(this, "Registration Failed Due To An Unknown Error");
                }   
            }
            
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("DB Connection error, or Driver class Loading error");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loginPage(){
        this.setVisible(false);
        this.dispose();
        UserView loginWindow = new UserView();
        loginWindow.userViewFrame.setVisible(true);
    }

    public Register(){
        setResizable(false);
        setTitle("Registration Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        setBounds(100, 100, 750, 360);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel fullname = new JLabel("Full Name :");
        fullname.setHorizontalAlignment(SwingConstants.RIGHT);
        fullname.setFont(new Font("PT Sans", Font.BOLD, 16));
        fullname.setBounds(27, 107, 92, 22);
        contentPane.add(fullname);

        fullNameText = new JTextField();
        fullNameText.setHorizontalAlignment(SwingConstants.CENTER);
        fullNameText.setBorder(new LineBorder(new Color(0, 0, 0)));
        fullNameText.setBackground(new Color(255, 255, 255));
        fullNameText.setBounds(139, 104, 232, 26);
        fullNameText.setColumns(10);
        contentPane.add(fullNameText);

        JLabel userid = new JLabel("User Id :");
        userid.setHorizontalAlignment(SwingConstants.RIGHT);
        userid.setFont(new Font("PT Sans", Font.BOLD, 16));
        userid.setBounds(27, 152, 92, 22);
        contentPane.add(userid);

        userIdText = new JTextField();
        userIdText.setHorizontalAlignment(SwingConstants.CENTER);
        userIdText.setBorder(new LineBorder(new Color(0, 0, 0)));
        userIdText.setBackground(new Color(255, 255, 255));
        userIdText.setColumns(10);
        userIdText.setToolTipText("annonymous@123");
        userIdText.setBounds(139, 149, 232, 26);
        contentPane.add(userIdText);

        JLabel password = new JLabel("Password :");
        password.setHorizontalAlignment(SwingConstants.RIGHT);
        password.setFont(new Font("PT Sans", Font.BOLD, 16));
        password.setBounds(27, 198, 92, 22);
        contentPane.add(password);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBounds(139, 195, 232, 26);
        contentPane.add(passwordField);

        JButton register = new JButton("Register");
        register.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        register.setBounds(219, 260, 129, 37);
        register.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                doRegister();
            }
        });
        contentPane.add(register);

        JLabel heading = new JLabel("Enter User Details");
        heading.setFont(new Font("Arial", Font.PLAIN, 20));
        heading.setBounds(10, 16, 245, 51);
        contentPane.add(heading);

        JButton login = new JButton("Login");
        login.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.setBounds(75, 260, 129, 37);
        login.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                loginPage();
            }
        });
        contentPane.add(login);

        ImageIcon img = new ImageIcon(Register.class.getResource("/assets/register.png"));
        JLabel imgContainer = new JLabel(img);
        imgContainer.setHorizontalAlignment(SwingConstants.LEFT);
        imgContainer.setBounds(409, 6, 335, 315);
        contentPane.add(imgContainer);
    }
}
