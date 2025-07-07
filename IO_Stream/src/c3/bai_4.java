package c3;
//Viết chương trình sử dụng DataOutputStream
//để ghi danh sách số nguyên vào file, sau đó dùng 
//DataInputStream để đọc lại các số đó.
import java.io.*;

public class bai_4 {
public static void main(String[] args) {
	int[] numbers = {10,9,8,7,6,5}; 
	
	 // Ghi danh sách số nguyên vào file
	try {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("input.txt"));
		
		for (int num : numbers) {
			dos.writeInt(num);
		}
		   System.out.println("Ghi dữ liệu vào file thành công.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	  // Đọc danh sách số nguyên từ file
	DataInputStream dis;
	try {
		dis = new DataInputStream(new FileInputStream("input.txt"));
		
		 System.out.println("Các số nguyên đọc từ file:");
		 
		 while(dis.available() > 0) {
			 System.out.println(dis.readInt());
		 }
	} catch (IOException e) {
		e.printStackTrace();
	}

}
}
