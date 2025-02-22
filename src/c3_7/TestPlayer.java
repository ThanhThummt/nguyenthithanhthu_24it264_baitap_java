package c3_7;

public class TestPlayer {
public static void main(String[] args) {
	Ball b1 = new Ball(0, 0, 0);
	System.out.println(b1);
	
	Player p1 = new Player(7, 3, 4);
	
	p1.kick(b1);
	 System.out.println(b1);
	 
	 p1.move(3, 2);
	 p1.kick(b1);
	 
	 p1.jump(7);
	
}
}
