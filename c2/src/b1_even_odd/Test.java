package b1_even_odd;

public class Test {
	 public static void main(String[] args) {
	        Even evenThread = new Even();
	        Odd oddThread = new Odd();

	        evenThread.start();
	        oddThread.start();
	    }
}
