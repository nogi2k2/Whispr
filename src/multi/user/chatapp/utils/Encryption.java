package multi.user.chatapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public interface Encryption {
    public static String getEncryptedPassword(String plainPassword) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(plainPassword.getBytes());
        byte[] temp = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b: temp){
            sb.append(b);
        }
        return sb.toString();
    }
}
