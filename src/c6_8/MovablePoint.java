package c6_8;

public class MovablePoint implements Movable {
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;

	public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "), Speed=(" + xSpeed + "," + ySpeed + ")";
	}

	public void moveUp() {
		y -= ySpeed;
	}

	public void moveDown() {
		x += xSpeed;
	}

	public void moveLeft() {
		x -= xSpeed;
	}

	public void moveRight() {
		x += xSpeed;
	}
	}
