package multi.user.chatapp.users.view;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import multi.user.chatapp.utils.Userinfo;
import multi.user.chatapp.users.validation.InputValidation;
import multi.user.chatapp.users.dao.UserDAO;
import multi.user.chatapp.users.dto.UserDTO;

public class UserDetails extends JFrame{
    private JFrame frame;
    private JTextField fullNameText;
    private JTextField userIdText;
    private JPanel contentPane;
    private JPasswordField passwordFieldText;

    
    public UserDetails(){
        initialize();
    }

    public void updateName(){
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(fullNameText.getText(), userIdText.getText());

        try{
            if (!InputValidation.lengthCheckValidateFullName(fullNameText.getText())){
                JOptionPane.showMessageDialog(this, "Invalid Name Update, Name must be longer than 3 characters!");
            }else{
                int returned = userDAO.updateName(userDTO);
                if (returned > 0){
                    JOptionPane.showMessageDialog(this, "Name Updated Successfully!");
                }else{
                    JOptionPane.showMessageDialog(this, "Name Updation Failed!");
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("DB Related Error");
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateUserId(){
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(fullNameText.getText(), userIdText.getText());

        try{
            if (!InputValidation.lengthCheckValidateUserId(userIdText.getText())){
                JOptionPane.showMessageDialog(this, "Invalid User Id, User Id must be longer than 6 characters!");
            }else{
                int returned = userDAO.updateUserid(userDTO);
                if (returned > 0){
                    JOptionPane.showMessageDialog(this, "User Id Updated Successfully!");
                }else{
                    JOptionPane.showMessageDialog(this, "Failed to Update User Id");
                }
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteDetails(){
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO(fullNameText.getText(), userIdText.getText());

        try{
            int returned = userDAO.deleteAccountDetails(userDTO);

            if (returned > 0){
                JOptionPane.showMessageDialog(this, "User Account Details Deleted Successfullt!");
                frame.setVisible(false);
                frame.dispose();
                Register registrationWindow = new Register();
                registrationWindow.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Account Details Deletion Failed!");                
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 360);
        frame.setResizable(false);
        frame.setTitle("User Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(null);

        JLabel fullName = new JLabel("Full Name :");
        fullName.setHorizontalAlignment(SwingConstants.RIGHT);
        fullName.setFont(new Font("PT Sans", Font.PLAIN, 16));
        fullName.setBounds(27, 36, 92, 22);
        contentPane.add(fullName);

        fullNameText = new JTextField();
        fullNameText.setHorizontalAlignment(SwingConstants.CENTER);
        fullNameText.setBounds(139, 33, 232, 26);
        fullNameText.setColumns(10);
        fullNameText.setBorder(new LineBorder(new Color(0, 0, 0)));
        fullNameText.setBackground(new Color(255, 255, 255));
        fullNameText.setText(Userinfo.USER_NAME);
        contentPane.add(fullNameText);

        JLabel userId = new JLabel("User Id :");
        userId.setHorizontalAlignment(SwingConstants.RIGHT);
        userId.setFont(new Font("PT Sans", Font.PLAIN, 16));
        userId.setBounds(27, 115, 92, 22);
        contentPane.add(userId);

        userIdText = new JTextField();
        userIdText.setHorizontalAlignment(SwingConstants.CENTER);
        userIdText.setBorder(new LineBorder(new Color(0, 0, 0)));
        userIdText.setBackground(new Color(255, 255, 255));
        userIdText.setColumns(10);
        userIdText.setText(Userinfo.USER_ID);
        userIdText.setBounds(139, 112, 232, 26);
        contentPane.add(userIdText);

        JLabel passwordField = new JLabel("Password :");
        passwordField.setFont(new Font("PT Sans", Font.PLAIN, 16));
        passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordField.setBounds(27, 192, 92, 22);
        contentPane.add(passwordField);

        passwordFieldText = new JPasswordField();
        passwordFieldText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setText("●●●●●●●");
        passwordFieldText.setBackground(new Color(255, 255, 255));
        passwordFieldText.setBorder(new LineBorder(new Color(0, 0, 0)));
        passwordFieldText.setBounds(139, 189, 232, 26);
        contentPane.add(passwordFieldText);

        JButton saveDetails = new JButton("SAVE");  
        saveDetails.setBounds(75, 265, 129, 37);
        saveDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        saveDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveDetails.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                if (Userinfo.USER_NAME.equals(fullNameText.getText()) && Userinfo.USER_ID.equals(userIdText.getText())){
                    frame.setVisible(false);
                    frame.dispose();
                    DashBoard dashBoardWindow = new DashBoard(Userinfo.USER_ID);
                    dashBoardWindow.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "User Details Updated Successfully!");
                    frame.setVisible(false);
                    frame.dispose();
                    UserView loginWindow = new UserView();
                    loginWindow.userViewFrame.setVisible(true);
                }
            }
        });
        contentPane.add(saveDetails);

        JButton deleteDetails = new JButton("DELETE");
        deleteDetails.setForeground(Color.red);
        deleteDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        deleteDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteDetails.setBounds(219, 265, 129, 37);
        deleteDetails.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                JFrame deleteFrame = new JFrame();
                if (JOptionPane.showConfirmDialog(deleteFrame, "Confirm Delete User Details", "Delete Details", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    deleteDetails();
                }
            }
        });
        contentPane.add(deleteDetails);

        ImageIcon i1 = new ImageIcon(UserDetails.class.getResource("/assets/update3.png"));
        JLabel imgContainer = new JLabel(i1);
        imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
        imgContainer.setBounds(383, 6, 361, 315);
        contentPane.add(imgContainer);

        JButton updateFullName = new JButton("Update Name");
        updateFullName.setBounds(254, 59, 117, 29);
        updateFullName.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                updateName();
            }
        });
        contentPane.add(updateFullName);

        JButton updateUserId = new JButton("Update UserId");
        updateUserId.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                updateUserId();
            }
        });
        updateUserId.setBounds(254, 138, 117, 29);
        contentPane.add(updateUserId);

        frame.setVisible(true);
    }
}
