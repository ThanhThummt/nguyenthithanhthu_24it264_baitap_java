package c3;
// Viết 1 chương trình sử dụng BufferedReader
//để đọc dữ liệu từ bàn phím và lưu vào file.
import java.io.*;

public class bai_2 {
    public static void main(String[] args) {
        // Đối tượng BufferedReader để đọc dữ liệu từ bàn phím
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Nhập nội dung cần lưu vào file (nhấn 'exit' đê thoát): ");
    	
    	try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			
			String line;
			while((line = br.readLine()) != null) {
				if("exit".equalsIgnoreCase(line)) {
					break;
				}
				
				bw.write(line);
				bw.newLine();// xuống dòng
			}
			 System.out.println("Dữ liệu đã được lưu vào output.txt");
		} catch (IOException e) {
			System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}


