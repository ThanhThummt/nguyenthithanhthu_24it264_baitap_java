package vd1;

import java.io.*;

public class WriteFile {
	public static void main(String[] args) throws IOException {
		String name = "Nguyễn Thị Thanh Thu";
		int age = 19;
		File file = new File("src/vd1/vd1out.txt");
		BufferedOutputStream bos=null;
		try {
			FileOutputStream fos = new FileOutputStream(file,true);
			 bos = new BufferedOutputStream(fos);
			bos.write(name.getBytes());
			bos.write("\n".getBytes());
			bos.write(String.valueOf(age).getBytes());// Chuyển số thành chuỗi trước khi ghi
			bos.write("\n".getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			bos.close();
		}
	}
}
