package c3_IO_Thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Bài tập 10: Tải dữ liệu từ URL bằng Thread
//Yêu cầu: Tạo Thread tải nội dung từ một URL và lưu vào file.

public class ex10 extends Thread {
	private String urlString;
	private String filePath;

	public ex10(String urlString, String filePath) {
		this.urlString = urlString;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try{
 // Tạo URL
		URL url = new URL(urlString);
//Mở kết nối
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");;
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
			
			String line;
			while((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
			System.out.println("Dơnload và lưu file thành công "+filePath);
		}catch (Exception e) {
			 System.out.println("Lỗi khi đọc hoặc ghi file.");
			e.printStackTrace();
		}
	}catch (Exception e) {
		  System.out.println("Lỗi khi tải dữ liệu từ URL.");
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		String urlString = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
		String filePath = "output.txt";
		
		ex10 ex = new ex10(urlString, filePath);
				
		ex.start();
	}
}
