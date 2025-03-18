package c3_IO_Thread;

import java.io.*;

// Tạo một chương trình sử dụng Thread 

//để đọc từng dòng từ một file văn bản và in ra màn hình.

public class ex1 extends Thread {
	private String filePath;

	public ex1(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(Thread.currentThread().getName() + " đọc " + line);

				Thread.sleep(500);// thời gian đọc
			}
		} catch (IOException | InterruptedException e) {
			System.err.println("Lỗi khi đọc file: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		String filePath = "input.txt";// Đảm bảo file này tồn tại trong thư mục chạy chương trình

		ex1 fileReaderThread = new ex1(filePath);

		fileReaderThread.start();
	}
}
