package bt.bai4;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAEncryption implements Encryptable {
    private static final String ALGORITHM = "RSA";
    private KeyPair keyPair;

    public RSAEncryption() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(2048);
        keyPair = keyGen.generateKeyPair();
    }

    @Override
    public String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decoded = Base64.getDecoder().decode(input);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}

