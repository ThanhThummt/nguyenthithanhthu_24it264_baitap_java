package b3_3_Starvation;

public class Starvation_1 {
  public static void main(String[] args) {
	Thread highPriorityThread= new Thread(()->{
		while(true) { System.out.println("High priority thread running...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	});
	
	Thread lowPriorityThread= new Thread(()->{
		while(true) {
			System.out.println("Low priority thread running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	
	highPriorityThread.setPriority(Thread.MAX_PRIORITY);
	lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
	
	  highPriorityThread.start();
      lowPriorityThread.start();
}
}
