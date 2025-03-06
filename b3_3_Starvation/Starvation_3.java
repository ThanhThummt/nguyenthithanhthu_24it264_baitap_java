package b3_3_Starvation;

import java.util.concurrent.locks.ReentrantLock;

public class Starvation_3 {
	private static final ReentrantLock lock = new ReentrantLock(true);// chế độ cb
public static void main(String[] args) {
	Thread high = new Thread(()->{
		while(true) {
			lock.lock();
			try {
				System.out.println("High priority thread running...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}finally {
				lock.unlock();
			}
		}
	});
	
	Thread low = new Thread(()->{
		while(true) {
			lock.lock();
			try {
				System.out.println("Low priority thread running...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}finally {
				lock.unlock();
			}
		}
	});
	
	high.setPriority(Thread.MAX_PRIORITY);
	low.setPriority(Thread.MIN_PRIORITY);
	
	high.start();
	low.start();
}
}
