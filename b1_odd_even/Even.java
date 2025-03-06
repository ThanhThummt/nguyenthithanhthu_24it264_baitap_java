package b1_odd_even;

public class Even extends Thread {

	@Override
	public void run() {
		for (int i = 2; i <= 10; i += 2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Even: " + i);
		}
	}

}
