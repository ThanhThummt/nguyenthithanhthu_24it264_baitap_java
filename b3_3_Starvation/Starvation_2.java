package b3_3_Starvation;

public class Starvation_2 {

	public static void main(String[] args) {
		Thread highPriority = new Thread(() -> {
			while (true) {
				System.out.println("High priority thread running...");
				Thread.yield(); // Nhường CPU cho thread khác
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread lowPriority = new Thread(() -> {
			while (true) {
				System.out.println("Low priority thread running...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		highPriority.setPriority(Thread.MAX_PRIORITY);
		lowPriority.setPriority(Thread.MIN_PRIORITY);
		highPriority.start();
		lowPriority.start();
	}
}
