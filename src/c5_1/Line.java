package c5_1;

public class Line {
	 private Point begin;    
	private Point end; 
	
	public Line(Point begin, Point end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	  public Line (int beginX, int beginY, int endX, int endY) {
	      begin = new Point(beginX, beginY);
	      end = new Point(endX,endY);
	      }
	@Override
	public String toString() {
		return "Line [begin=" + begin + ", end=" + end + "]";
	}
	public Point getBegin() {
		return begin;
	}
	public void setBegin(Point begin) {
		this.begin = begin;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	public int getBeginX() {
		return begin.getX();
	}
	public void setBeginX(int x) {
		this.begin.setX(x);;
	}
	public int getBeginY() {
		return begin.getY();
	}
	public void setBeginY(int y) {
		this.begin.setX(y);;
	}
	public int getEndY() {
		return end.getY();
	}
	public void setEndY(int y) {
		this.end.setY(y);;
	}
	public int getEndX() {
		return end.getX();
	}
	public int[] getBeginXY() {
		return begin.getXY();
	}
	public void setBeginXY(int x,int y) {
		this.begin.sextXY(x, y);
	}
	public int[] getEndXY() {
		return end.getXY();
	}
	public void setEndXY(int x, int y) {
		this.end.sextXY(x, y);
	}
	
	int xDiff = getEndX()-getBeginX();
	 int yDiff = getEndY()-getBeginY();
	 public int getLength() {
		 return (int) Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	 }
	 public double getGradient() { 
		return Math.atan2(yDiff, xDiff);
	 } 
	 
}
