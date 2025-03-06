package b2_2_DeadlLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock_3 {
	static final Lock lock1 = new ReentrantLock();
	static final Lock lock2 = new ReentrantLock();

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			while (true) {
				if (lock1.tryLock()) {
					try {
						System.out.println("Thread 1: Locked resource 1");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}

						if (lock2.tryLock()) {
							try {
								System.out.println("Thread 1: Locked resource 2");
								break;
							} finally {
								lock2.unlock();
							}
						}
					} finally {
						lock1.unlock();
					}

				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
			}
		});
		
		Thread t2 = new Thread(()->{
			while(true) {
				if(lock2.tryLock()) {
					try {
						System.out.println("Thread 2: Locked resource 2");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
						
						if(lock1.tryLock()) {
							try {
								System.out.println("Thread 2: Locked resource 1");
								break;
							}finally {
								lock1.unlock();
							}
						}
					}finally {
						lock2.unlock();
					}
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {}
			}
		});
		t1.start();
		t2.start();
	}
}
