package bt.bai2;

public interface Encryptable {
    String encrypt(String plainText) throws Exception;
    String decrypt(String encryptedText) throws Exception;
}
