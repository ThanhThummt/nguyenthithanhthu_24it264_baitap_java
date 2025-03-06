package b3_ban_an;

public class Test_ban_an {
public static void main(String[] args) {
	 Đb_Kh b = new Đb_Kh();
	Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				b.ĐB();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	});

	Thread t2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				b.KH();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	t1.start();
	t2.start();
	
}
}
