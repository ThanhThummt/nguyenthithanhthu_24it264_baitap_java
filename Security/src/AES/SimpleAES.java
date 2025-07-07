package AES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SimpleAES {
    public static void main(String[] args) throws Exception {
        String text = "hello123"; // 1. Văn bản cần mã hóa
        String key = "1234567890123456"; // 2. Khóa bí mật 16 ký tự (128-bit)
     // Mã hóa
        // 3. Tạo đối tượng khóa bí mật
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // 4. Tạo đối tượng Cipher và cài đặt chế độ mã hóa
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 5. Mã hóa văn bản thành mảng byte
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());

        // 6. Chuyển byte thành chuỗi Base64 để dễ in ra
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Đã mã hóa: " + encryptedText);
        // Giải mã
        // 7. Cài lại chế độ giải mã
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // 8. Giải mã từ chuỗi Base64 về mảng byte
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        // 9. Chuyển mảng byte về chuỗi gốc
        String decryptedText = new String(decryptedBytes);
        System.out.println("Đã giải mã: " + decryptedText);
    }
}

