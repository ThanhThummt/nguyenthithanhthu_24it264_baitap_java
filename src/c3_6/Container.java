package c3_6;

public class Container {
	private int x1,y1,x2,y2;

	public Container(int x,int y,int width, int height) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x+width ;
		this.y2 = y+height;
	}

	public int getX() {
		return x1;
	}

	public int getY() {
		return y1;
	}

	public int getWidth() {
		return x2-x1;
	}

	
	public int getWieght() {
		return y2-y1;
	}

	public boolean collides(Ball ball) {
		boolean collided= false;
		
		if(ball.getX()-ball.getRadius()<=this.x1 || ball.getX() + ball.getRadius() >= this.x2) {
			ball.reflectHorizontal();  
            collided = true;
		}
		 if (ball.getY() - ball.getRadius() <= this.y1 ||
		            ball.getY() + ball.getRadius() >= this.y2) {
		            ball.reflectVertical();  
		            collided = true;
		        }

		        return collided;
	}
}
