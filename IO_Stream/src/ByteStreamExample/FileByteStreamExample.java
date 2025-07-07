package ByteStreamExample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileByteStreamExample {
    public static void main(String[] args) {
        String filename = "src/example.txt";  // Tên file để đọc/ghi dữ liệu
        
        // 1. Ghi dữ liệu vào file bằng FileOutputStream
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            String data = "Hello, đây là dữ liệu trong file!";
            fos.write(data.getBytes());  // Chuyển chuỗi thành byte và ghi vào file
            System.out.println("Dữ liệu đã ghi vào file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Đọc dữ liệu từ file bằng FileInputStream
        try (FileInputStream fis = new FileInputStream(filename)) {
            int i;
            System.out.print("Nội dung file: ");
            while ((i = fis.read()) != -1) {
                System.out.print((char) i);  // Chuyển byte thành ký tự
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

