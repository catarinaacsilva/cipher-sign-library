import java.utils.*;

public class Symkey{
    
    // Generate Symmetric key
    public static String generateSymKey(String pwd) {
        byte[] salt = new byte[1];
        salt[0] = 0;
        PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd.toCharArray(), salt, 1000, 128);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] keyData = new byte[0];
        try {
            keyData = skf.generateSecret(pbeKeySpec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        SecretKey secretKey = new SecretKeySpec(keyData, "AES");
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return encodedKey;
    }
}
