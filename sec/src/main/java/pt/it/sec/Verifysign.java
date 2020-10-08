import java.utils.*;

public class Verifysign{
    // Verify signature
    public static boolean verify(String plainText, String signature, PublicKey publicKey){
        Signature publicSignature = null;
        try {
            publicSignature = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            publicSignature.initVerify(publicKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            publicSignature.update(plainText.getBytes(UTF_8));
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        try {
            return publicSignature.verify(signatureBytes);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }
}
