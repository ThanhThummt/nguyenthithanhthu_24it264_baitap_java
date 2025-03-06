package b2_dem_nguoc;

public class Dem_nguoc implements Runnable{

	@Override
	public void run() {
		for(int i=10;i>=0;i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
		
	}
	public static void main(String[] args) {
		Thread d =new Thread(new Dem_nguoc());
		
		d.start();
	}

}
