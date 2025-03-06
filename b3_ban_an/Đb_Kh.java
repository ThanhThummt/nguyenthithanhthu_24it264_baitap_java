package b3_ban_an;

import java.util.LinkedList;

public class Đb_Kh {
     int capaticy=5;
     LinkedList<Integer> table = new LinkedList<>();
   
     
     public void ĐB()  throws InterruptedException{
    	  int count=0;
    	 while(true) {
    		 synchronized (this) {
    		 while(table.size()==capaticy) {
    			 System.out.println("Bàn đầy! đầu bếp đợi....");
    			 wait();
    		 }
    		 System.out.println("Đầu bếp nấu: "+ count);
    		 table.add(count++);
    		 notify();
    		 }
    		 Thread.sleep(2000);
    	 }
     }
     
     public void KH () throws InterruptedException{
    	 while(true) {
    		 synchronized (this) {
				while(table.size()==0) {
					System.out.println("Bàn trống! Khách hàng đợi...");
					 wait();
				}
				
				System.out.println("Khách hàng chọn món:  "+ table.removeFirst());
				notify();
			}
    		 Thread.sleep(3000);
    	 }
     }
}



