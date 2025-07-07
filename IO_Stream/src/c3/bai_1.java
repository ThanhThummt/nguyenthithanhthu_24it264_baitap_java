package c3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class bai_1 {
public static void main(String[] args) throws IOException {

	try {
		FileInputStream fis = new FileInputStream("input.txt");
		FileOutputStream fos = new FileOutputStream("output.txt");
		
		int byteData;
		while((byteData = fis.read()) != -1) {
			fos.write(byteData);//Ghi byte vào tệp đích
		}
		 System.out.println("Sao chép tệp thành công!");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
}
