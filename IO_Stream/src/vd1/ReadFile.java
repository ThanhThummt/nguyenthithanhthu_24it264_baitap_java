package vd1;

import java.io.*;

public class ReadFile {
	public static void main(String[] args) throws IOException {
		File file = new File("src/vd1/vd1out.txt");
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			while (bis.available() > 0) {
				char c = (char) bis.read();
				System.out.print(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
