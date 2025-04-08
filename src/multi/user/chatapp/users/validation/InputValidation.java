package multi.user.chatapp.users.validation;

public class InputValidation {
    public InputValidation(){}

    public static boolean lengthCheckValidateFullName(String FullName){
        return FullName.length()>=3;
    }

    public static boolean lengthCheckValidateUserId(String userid){
        return userid.length()>=6;
    }

    public static boolean lengthCheckValidateClientChatScreenText(String text){
        return text.length()>=1;
    }
}
