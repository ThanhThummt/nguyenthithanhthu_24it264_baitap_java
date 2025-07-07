package c3;
//Viết chương trình sử dụng BufferedReader
//để đọc một file văn bản và đếm số dòng trong 1 file

import java.io.*;


public class bai_3 {
public static void main(String[] args) {
	try {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		int lineCount =0;
		while((br.readLine()) != null) {
			lineCount++;
		}
		System.out.println("Số dòng trong file: "+ lineCount);
	} catch (IOException e) {
	System.err.println("Lỗi khi đọc file: "+ e.getMessage());
	}
}
}
