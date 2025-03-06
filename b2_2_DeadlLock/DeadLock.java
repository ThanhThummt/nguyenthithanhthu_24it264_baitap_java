package b2_2_DeadlLock;

public class DeadLock {
 static final Object resource1 = new Object();
 static final Object resource2 = new Object();
 
 public static void main(String[] args) {
	Thread t1 = new Thread(()->{
		synchronized (resource1) {
			System.out.println("Thread 1: Locked resource 1");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (resource2) {
			   System.out.println("Thread 1: Locked resource 2");
		}
	});
	
	Thread t2 = new Thread(()->{
		synchronized (resource2) {
			System.out.println("Thread 2: Locked resource 2");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (resource1) {
			   System.out.println("Thread 2: Locked resource 1");
		}
	});
	
	   t1.start();
       t2.start();
}
}
