package b1_odd_even;

public class Odd extends Thread {

	public void run() {
		for (int i = 1; i <= 10; i += 2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Odd: " + i);
		}
	}
}
