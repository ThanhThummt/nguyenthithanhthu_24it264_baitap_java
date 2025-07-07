package c3_IO_Thread;

import java.io.IOException;
import java.io.RandomAccessFile;

import c3_IO_Thread.ex4.ReaderThread;

//Bài tập 6: Đọc file lớn với nhiều Thread
//Yêu cầu: Chia file lớn thành các phần và dùng nhiều Thread để đọc song song.
public class ex6 extends Thread{
	private String fileName;
	private long start;
	private long length;
	
	public ex6(String fileName, long start, long length) {
		this.fileName = fileName;
		this.start = start;
		this.length = length;
	}
	@Override
	public void run() {
   try(RandomAccessFile raf = new RandomAccessFile(fileName,"r")){
	   raf.seek(start);
	   
	   byte[] buffer = new byte[(int) length];
	   raf.read(buffer);
	   
	   System.out.println(Thread.currentThread().getName()+" "+ new String(buffer).trim());
   }catch (Exception e) {
	e.printStackTrace();
}
	}
	
	public static void main(String[] args) throws IOException {
		String fileName ="input.txt";
		RandomAccessFile raf = new RandomAccessFile(fileName, "r");
		long fileLength = raf.length();
		long partSize = fileLength/2;
		
		ex6 t1 = new ex6(fileName,0,partSize);
		ex6 t2 = new ex6(fileName,partSize,fileLength);
		
		t1.start();
		t2.start();
		
	}
	
}
