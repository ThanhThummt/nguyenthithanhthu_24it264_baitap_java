package c3_IO_Thread;
//Bài tập 8: Đọc và ghi đồng thời

//Yêu cầu: Tạo hai Thread: một đọc từ file nguồn, một ghi vào file đích.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ex8 {
	static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

//ĐỌC FILE
	static class Reader extends Thread {
		private String source;

		public Reader(String source) {
			this.source = source;
		}

		@Override
		public void run() {
			try (BufferedReader br = new BufferedReader(new FileReader(source))) {
				String line;

				while ((line = br.readLine()) != null) {
					queue.put(line);
				}
				queue.put("END");
				System.out.println("Hoàn thành đọc file!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//GHI FILE
	static class Writer extends Thread{
		private String destination;
		
		
		public Writer(String destination) {
			this.destination = destination;
		}


		@Override
		public void run() {
			try(FileWriter fw = new FileWriter(destination)){
				for(;;) {
					 String line = queue.take();
					 
					 if(line.equals("END")) break;
					 
					 fw.write(line+"\n");
				}
				System.out.println("Hoàn thành ghi file.");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Reader r = new Reader("input.txt");
		Writer w = new Writer("output.txt");
		
		r.start();
		w.start();
	}
}
