package c3_7;

public class Player {
private int number;
private float x, y, z=0.0f;

public Player(int number, float x, float y) {
	this.number = number;
	this.x = x;
	this.y = y;
}
  public void move(float xDisp, float yDisp) {
	  x+=xDisp;
	  y+=yDisp;
  }
  public void jump(float zDisp) {
      this.z += zDisp;
  }

  public boolean near(Ball ball) {
      float dx = this.x - ball.getX();
      float dy = this.y - ball.getY();
      float dz = this.z - ball.getZ();
      double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
      return distance < 8;
  }

  public void kick(Ball ball) {
      if (near(ball)) {
          ball.setXYZ(ball.getX() + 5, ball.getY() + 5, ball.getZ() + 2);
          System.out.println("Ball kicked!");
      } else {
          System.out.println("Ball is too far to kick.");
      }
  }

 
}
