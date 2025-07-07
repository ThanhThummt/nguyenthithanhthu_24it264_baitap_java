package bt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*Áp dụng thuật toán MD5 hoặc SHA để bảo mật mật khẩu
 *  trong ứng dụng đăng nhập.*/
public class bai1 {
  public static void main(String[] args) throws NoSuchAlgorithmException {
	String password = "@13453";
	MessageDigest digest = MessageDigest.getInstance("SHA-256");//MD5
	byte[] hashedBytes = digest.digest(password.getBytes());
	
	StringBuilder hexString = new StringBuilder();
	for (byte b : hashedBytes) {
		hexString.append(String.format("%02x",b));
	}
	System.out.println("Mật khẩu đã mã hóa: "+hexString.toString());
	}
}
