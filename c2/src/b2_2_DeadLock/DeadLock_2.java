package b2_2_DeadLock;

public class DeadLock_2 {

	    static final Object resource1 = new Object();
	    static final Object resource2 = new Object();

	    public static void main(String[] args) {
	        Thread t1 = new Thread(() -> {
	            synchronized (resource1) {
	                System.out.println("Thread 1: Locked resource 1");
	                try { Thread.sleep(100); } catch (InterruptedException e) {}
	                synchronized (resource2) {
	                    System.out.println("Thread 1: Locked resource 2");
	                }
	            }
	        });

	        Thread t2 = new Thread(() -> {
	            synchronized (resource1) {  
	                System.out.println("Thread 2: Locked resource 1");
	                try { Thread.sleep(100); } catch (InterruptedException e) {}
	                synchronized (resource2) {
	                    System.out.println("Thread 2: Locked resource 2");
	                }
	            }
	        });

	        t1.start();
	        t2.start();
	    }
	}


