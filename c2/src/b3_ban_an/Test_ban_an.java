package b3_ban_an;

public class Test_ban_an {
	public static void main(String[] args) {
        Ban_an banAn = new Ban_an();

        Thread dauBep = new Thread(() -> {
            try {
                banAn.nauMon();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread khachHang = new Thread(() -> {
            try {
                banAn.anMon();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        dauBep.start();
        khachHang.start();
    }
}
