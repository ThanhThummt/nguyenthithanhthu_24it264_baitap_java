package c3_IO_Thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

//Bài tập 7: Ghi dữ liệu từ bàn phím vào file bằng Thread
//Yêu cầu: Tạo Thread ghi dữ liệu người dùng nhập từ bàn phím vào file.
public class ex7 extends Thread{
public static void main(String[] args) {

	Thread t = new Thread(()->{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt")) ){
			System.out.println("Nhập nội dung cần ghi vào file (gõ 'exit' để thoát!");
			Scanner sc = new Scanner(System.in);
			
			for(; ;) {
				String line = sc.nextLine();
				
				if("exit".equalsIgnoreCase(line)) {
					System.out.println("Đã lưu và đóng file!");
					break;
				}
				
				bw.write(line);
				bw.newLine();
				bw.flush();// Đảm bảo dữ liệu được ghi ngay lập tức
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	});
	
	t.start();
}
}
