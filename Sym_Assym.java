import java.security.KeyPair;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sym_Assym {

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

    // Encrypt - Assymetric
    public static String encryptAssym(String plainText, PublicKey publicKey){
        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
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

        return Base64.getEncoder().encodeToString(cipherText);
    }

    // Decrypt - Assymetric
    public static String decryptAssym(String cipherText, PrivateKey privateKey) {
        byte[] bytes = new org.apache.commons.codec.binary.Base64().decode(cipherText);

        Cipher decriptCipher = null;
        try {
            decriptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
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
