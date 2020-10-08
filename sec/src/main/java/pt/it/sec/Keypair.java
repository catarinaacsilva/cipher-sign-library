import java.utils.*;


public class Keypair{
    // Generate Key pair (public and private)
    public static KeyPair generateKeyPair() {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }
}
