package multi.user.chatapp.users.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import multi.user.chatapp.users.dto.UserDTO;
import multi.user.chatapp.utils.DBConnection;
import multi.user.chatapp.utils.Encryption;

public class UserDAO {

    public String isLogin(UserDTO userdto) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            String query = "Select * from users where userid = ? and password = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, userdto.getUserid());
            pst.setString(2, Encryption.getEncryptedPassword(new String(userdto.getPassword())));
            rs = pst.executeQuery();

            if (rs.next()){
                return rs.getString("FullName");
            }else{
                return "";
            }
        }finally{
            if(rs != null){rs.close();}
            if (pst != null){pst.close();}
            if (conn != null){conn.close();}
        }
    }

    public int add(UserDTO userdto) throws SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        try{
            conn = DBConnection.getConnection();
            String query1 = "Select * from users where userid = ?";
            pst1 = conn.prepareStatement(query1);
            pst1.setString(1, userdto.getUserid());
            rs = pst1.executeQuery();

            if(rs.next()){
                return 0;
            }else{
                String query2 = "Insert into users (FullName , userid, password) values(?, ?, ?)";
                pst2 = conn.prepareStatement(query2);
                pst2.setString(1, userdto.getFullName());
                pst2.setString(2, userdto.getUserid());
                pst2.setString(3, Encryption.getEncryptedPassword(new String(userdto.getPassword())));
                int ret = pst2.executeUpdate();
                return ret;
            }
        }finally{
            if (pst1 != null) pst1.close();
            if (pst2 != null) pst2.close();
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }
    }
    
    public int updateName(UserDTO userdto) throws SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = DBConnection.getConnection();
            String query = "Update users set FullName = ? where userid = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, userdto.getFullName());
            pst.setString(2, userdto.getUserid());
            int ret = pst.executeUpdate();
            return ret;
        }finally{
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }
    }

    public int updateUserid(UserDTO userdto) throws SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            String query1 = "Select * from users where userid = ?";
            pst1 = conn.prepareStatement(query1);
            pst1.setString(1, userdto.getUserid());
            rs = pst1.executeQuery();

            if(rs.next()){
                return 0;
            }else{
                String query2 = "Update users set userid = ? where FullName = ?";
                pst2 = conn.prepareStatement(query2);
                pst2.setString(1, userdto.getUserid());
                pst2.setString(2, userdto.getFullName());
                int ret = pst2.executeUpdate();
                return ret;
            }
        }finally{
            if (pst1 != null) pst1.close();
            if  (pst2 != null) pst2.close();
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }
    }

    public int deleteAccountDetails(UserDTO userdto) throws SQLException, ClassNotFoundException, Exception{
        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = DBConnection.getConnection();
            String query = "Delete from users where userid = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, userdto.getUserid());
            int ret = pst.executeUpdate();
            return ret;
        }finally{
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }
    }
}
