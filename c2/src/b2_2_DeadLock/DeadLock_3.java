package b2_2_DeadLock;

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
						Thread.sleep(50);
						if (lock2.tryLock()) {
							try {
								System.out.println("Thread 1: Locked resource 2");
								break;
							} finally {
								lock2.unlock();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock1.unlock();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		});

		Thread t2 = new Thread(() -> {
			while (true) {
				if (lock2.tryLock()) {
					try {
						System.out.println("Thread 2: Locked resource 2");
						Thread.sleep(50);
						if (lock1.tryLock()) {
							try {
								System.out.println("Thread 2: Locked resource 1");
								break;
							} finally {
								lock1.unlock();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock2.unlock();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		});

		t1.start();
		t2.start();
	}

}
