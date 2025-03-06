package b1_odd_even;

public class TestOddEven {
public static void main(String[] args) {
	Thread o = new Odd();
	Thread e= new Even();
	
	e.start();
	o.start();
	
}
}
