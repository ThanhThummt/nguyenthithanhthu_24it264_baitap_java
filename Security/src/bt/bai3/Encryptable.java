package bt.bai3;

public interface Encryptable {
    String encrypt(String data) throws Exception;
    String decrypt(String encryptedData) throws Exception;
}
