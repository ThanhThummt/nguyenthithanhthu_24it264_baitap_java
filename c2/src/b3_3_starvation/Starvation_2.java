package b3_3_starvation;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Starvation_2 {
	    static final Lock lock = new ReentrantLock(true); 

	    public static void main(String[] args) {
	        Thread highPriority = new Thread(() -> {
	            while (true) {
	                lock.lock();
	                try {
	                    System.out.println("High priority thread running...");
	                } finally {
	                    lock.unlock();
	                }
	            }
	        });

	        Thread lowPriority = new Thread(() -> {
	            while (true) {
	                lock.lock();
	                try {
	                    System.out.println("Low priority thread running...");
	                } finally {
	                    lock.unlock();
	                }
	            }
	        });

	        highPriority.setPriority(Thread.MAX_PRIORITY);
	        lowPriority.setPriority(Thread.MIN_PRIORITY);

	        highPriority.start();
	        lowPriority.start();
	    
	}

}
