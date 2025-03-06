package b3_ban_an;

import java.util.LinkedList;

public class Ban_an {
    private final LinkedList<String> banAn = new LinkedList<>();
    private final int SLOTS = 5; 

    public void nauMon() throws InterruptedException {
        int count = 0;
        while (true) {
            synchronized (this) {
                while (banAn.size() == SLOTS) {
                    System.out.println("Bàn đầy, đầu bếp đợi...");
                    wait();
                }
                String mon = "Món " + (++count);
                banAn.add(mon);
                System.out.println("Đầu bếp đã nấu: " + mon);
                notify();
            }
            Thread.sleep(2000);
        }
    }

    public void anMon() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (banAn.isEmpty()) { 
                    System.out.println("Bàn trống, khách đợi...");
                    wait();
                }
                String monAn = banAn.removeFirst();
                System.out.println("Khách hàng đã ăn: " + monAn);
                notify(); 
            }
            Thread.sleep(3000);
        }
    }
}
