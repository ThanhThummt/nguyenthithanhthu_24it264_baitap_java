package c3_IO_Thread;
//Bài tập 9: Đếm ký tự trong file bằng Thread
//Yêu cầu: Tạo Thread đếm số ký tự trong file và ghi kết quả vào file khác.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ex9 {
static BlockingQueue< Integer> queue = new LinkedBlockingQueue<Integer>();

//ĐẾM KÍ TỰ
static class CharCountThread extends Thread{
	private String source;

	public CharCountThread(String source) {
		this.source = source;
	}

	@Override
	public void run() {
  try(BufferedReader br = new BufferedReader(new FileReader(source))){
	  int count = 0;
	  int ch;
	  
	 while((ch = br.read()) != -1) {
		  count++;
	  }
	  System.out.println("Số từ trong file "+ source+" là: "+count);
	  queue.put (count); // Đưa kết quả vào hàng đợi
	}catch (Exception e) {
		e.printStackTrace();
		} 
	}
}

//GHI FILE
static class Writerthread extends Thread{
	private String destination;

	public Writerthread(String destination) {
		this.destination = destination;
	}

	@Override
	public void run() {
  try(FileWriter fw = new FileWriter(destination)){
	 
		  int result = queue.take();
		  fw.write("Số ký tự trong file: " + result);
		  System.out.println("Đã ghi kết quả vào file "+destination);

  } catch (IOException|InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
	}
	
	public static void main(String[] args) {
		CharCountThread c = new CharCountThread("input.txt");
		Writerthread w = new Writerthread("output.txt");
		
		c.start();
		w.start();
	}
	
}
}
