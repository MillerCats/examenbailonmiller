package encrypt;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKey;

public class Cifrados {

    private static final String SECRET_KEY = "lafedecuto"; // DES usa claves de 8 bytes

    public static String decrypt(String encryptedText) throws Exception {
        byte[] key = SECRET_KEY.getBytes("UTF-8");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(desKeySpec);

        byte[] decoded = Base64.getDecoder().decode(encryptedText);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] original = cipher.doFinal(decoded);
        return new String(original, "UTF-8");
    }

    public static String encrypt(String plainText) throws Exception {
        byte[] key = SECRET_KEY.getBytes("UTF-8");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static void main(String[] args) {
        try {
            System.out.println(encrypt("admin123"));
            System.out.println(decrypt("+vo6NcSVt1HxpXQhX4fweA=="));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
