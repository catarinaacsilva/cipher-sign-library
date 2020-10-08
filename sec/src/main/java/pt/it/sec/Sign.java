import java.utils.*;

public class Sign{
    
    // sign
    public static String sign(String plainText, PrivateKey privateKey) {
        Signature privateSignature = null;
        try {
            privateSignature = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateSignature.initSign(privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            privateSignature.update(plainText.getBytes(UTF_8));
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        byte[] signature = new byte[0];
        try {
            signature = privateSignature.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(signature);
    }
}
