package c3_IO_Thread;
//Bài tập 2: Ghi dữ liệu vào file bằng nhiều Thread
//Yêu cầu: Tạo hai Thread, mỗi Thread ghi một chuỗi khác nhau
//vào cùng một file.


import java.io.*;

public class ex2 extends Thread {
private String filePath;
private String content;
private static final Object lock = new Object();//Khóa đồng bộ

public ex2(String filePath,String content) {
	this.filePath = filePath;
	this.content = content;
}

public void run() {
	synchronized (lock) {//đảm bảo chỉ 1 Thread ghi vào 1 file tại 1 thời điểm
		
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
		
		 bw.write( content+"\n");
        
         System.out.println(Thread.currentThread().getName() + " đã ghi xong.");
		
	} catch (IOException e) {
		e.printStackTrace();
	}}
}

public static void main(String[] args) {
	String filePath = "output.txt";
	
	ex2 thread1 = new ex2(filePath,"Xin chào các bạn! ");
	ex2 thread2 = new ex2(filePath,"Xin chào các bạn.....! ");
	
	thread1.start();
	thread2.start();
}
}
