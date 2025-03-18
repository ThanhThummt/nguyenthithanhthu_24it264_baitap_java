package c3_IO_Thread;
//Bài tập 4: Đọc và xử lý dữ liệu song song
//Yêu cầu: Tạo hai Thread: một Thread đọc file, một Thread xử lý dữ liệu đọc được (ví dụ: đếm số từ).

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ex4 {
	static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

 //Đọc dòng
	static class ReaderThread extends Thread {
		private String filePath;

		public ReaderThread(String filePath) {
			this.filePath = filePath;
		}

		@Override
		public void run() {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;

				while ((line = br.readLine()) != null) {
					queue.put(line);// Đưa dòng vào hàng đợi
				}
				queue.put("END");
				System.out.println("Đã đọc xong file!");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	// Lấy dòng
	static class ProcessorThread extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					String line = queue.take();// Lấy dữ liệu từ hàng đợi

					if (line.equals("END"))
						break;

					int wordCount = line.split("\\s+").length;
					System.out.println("Số từ: " + wordCount);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ReaderThread r = new ReaderThread("input.txt");
		ProcessorThread p = new ProcessorThread();

		r.start();
		p.start();
	}
}
