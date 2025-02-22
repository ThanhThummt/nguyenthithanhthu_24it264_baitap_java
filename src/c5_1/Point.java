package c5_1;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Point: (" + x + "," + y + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getXY() {
		return new int[] { x, y };
	}

	public void sextXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
