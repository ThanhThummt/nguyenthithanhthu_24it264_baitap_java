package c3_IO_Thread;
// Sao chép file bằng Thread	
//Yêu cầu: Viết chương trình sử dụng Thread để sao chép nội dung từ file nguồn sang file đích.

import java.io.*;

public class ex3  {
	public static void main(String[] args) {
	Thread t = new Thread(()->{
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {

			String line;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
				System.out.println(Thread.currentThread().getName()+" đã sao chép "+line);
				Thread.sleep(500);
			}

			System.out.println("Đã sao chép file thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	});
	t.start();
}
}