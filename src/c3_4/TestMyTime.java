package c3_4;

public class TestMyTime {
public static void main(String[] args) {
	MyTime t1 = new MyTime(23, 59, 58);
    System.out.println(t1);
    
    System.out.println(t1.nextSecond().nextSecond());
    System.out.println(t1.nextMinute());
    System.out.println(t1.nextHour());
    
    t1.setTime(12, 30, 45);
    System.out.println(t1);
    
    t1.setHour(5);
    t1.setMinute(15);
    t1.setSecond(25);
}

}
