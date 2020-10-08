import java.utils.*;

public class Symmetric{

    //Encrypt - Symmetric
    public static String encryptSym(String plainText, SecretKey symKey){
        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, symKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] cipherText = new byte[0];
        try {
            cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return java.util.Base64.getEncoder().encodeToString(cipherText);
    }

    // Decrypt - Symmetric
    public static String decryptSym(String cipherText, SecretKey symKey) {
        byte[] bytes = new org.apache.commons.codec.binary.Base64().decode(cipherText);

        Cipher decriptCipher = null;
        try {
            decriptCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            decriptCipher.init(Cipher.DECRYPT_MODE, symKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            return new String(decriptCipher.doFinal(bytes), UTF_8);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
