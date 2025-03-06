package b2_dem_nguoc;

public class dem_nguoc extends Thread {
	public void run() {
		for (int i = 10; i >= 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("hoan thanh dem nguoc!!");
	}

	public static void main(String[] args) {
		dem_nguoc t = new dem_nguoc();
		t.start();
	}
}
