package c3;

import java.io.*;

public class ReadContent {
	public static void main(String[] args) {
		try {
		 InputStreamReader isr = new InputStreamReader(new FileInputStream("input.txt"));
		  
		  int byteData;
		  
		  while((byteData = isr.read()) != -1) {
			  System.out.print((char) byteData);
		  }
		  isr.close();
		  }catch(IOException e) {
			  e.printStackTrace();
		  };
	}

}
