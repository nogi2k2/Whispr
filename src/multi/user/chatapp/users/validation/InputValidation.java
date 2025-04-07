package multi.user.chatapp.users.validation;

public class InputValidation {
    public InputValidation(){}

    public boolean lengthCheckValidateFullName(String FullName){
        return FullName.length()>=3;
    }

    public boolean lengthCheckValidateUserId(String userid){
        return userid.length()>=6;
    }

    public boolean lengthCheckValidateClientChatScreenText(String text){
        return text.length()>=1;
    }
}
