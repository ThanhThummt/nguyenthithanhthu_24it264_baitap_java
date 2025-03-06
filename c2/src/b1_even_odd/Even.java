package b1_even_odd;

public class Even extends Thread{
	    public void run() {
	        for (int i = 2; i <= 10; i += 2) {
	            System.out.println("Even: " + i);
	            try {
	                Thread.sleep(500); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }


}
