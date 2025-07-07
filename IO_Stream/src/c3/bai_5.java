//Viết chương trình sử dụng lớp File để liệt kê tất cả các file 
//  trong 1 thư mục được chỉ định
package c3;

import java.io.File;

public class bai_5 {
public static void main(String[] args) {
	
	File directory = new File("D:\\java\\IO_Stream\\src\\c3");
	
	//Kiểm tra đường dẫn có phải là thư mục hay không?
	if(directory.isDirectory()) {
		File[] files = directory.listFiles();
		
		if(files != null && files.length > 0) {
			System.out.println("Danh sách các file trong thư mục: ");
			for (File file : files) {
				if(file.isFile()){
					System.out.println(file.getName());
				}
			}
			
		}else {
			System.out.println("Thư mục rộng hoạc không có  file nào!");
		}
		
	}else {
		System.out.println("Đường dẫn không phải là thư mục hoặc không tồn tại.");
	}
}
}
