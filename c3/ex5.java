package c3_IO_Thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

//Bài tập 5: Ghi log bằng Thread
//Yêu cầu: Tạo một Thread ghi thông tin log (thời gian + thông điệp) vào file.

public class ex5 extends Thread {
	private String message;
	private static final String logfile = "output.txt";

	public ex5(String message) {
		this.message = message;
	}

	@Override
	public void run() {
		synchronized (ex5.class) { // Đảm bảo ghi log không bị xung đột
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(logfile))) {
				PrintWriter pw = new PrintWriter(bw);
				String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				pw.print(timestamp + " - " + message);
				System.out.println("Logged: "+timestamp + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 5; i++) {
			new ex5(" Message " + i).start();
		}
	}
}
