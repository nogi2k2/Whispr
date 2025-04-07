package multi.user.chatapp.users.dto;

public class UserDTO {
    private String FullName;
    private String userid;
    private char[] password;

    public UserDTO(){}

    public UserDTO(String FullName, String userid, char[] password){
        this.FullName = FullName;
        this.userid = userid;
        this.password = password;
    }

    public UserDTO(String userid, char[] password){
        this.userid = userid;
        this.password = password;
    }

    public UserDTO(String FullName, String userid){
        this.FullName = FullName;
        this.userid = userid;
    }

    public void setFullName(String FullName){
        this.FullName = FullName;
    }

    public String getFullName(){
        return this.FullName;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getUserid(){
        return this.userid;
    }

    public void setPassword(char[] password){
        this.password = password;
    }

    public char[] getPassword(){
        return password;
    }
}
