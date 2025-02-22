package c2_9;

public class MyPoint {
	
		private int x = 0;
		private int y = 0;

		public MyPoint() {
		}

		public MyPoint(int x, int y) {
			this.x = x;
			this.y = y;
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
		
		@Override
		public String toString() {
			 return "(" + x + ", " + y + ")";
		}

	// Phương thức getXY để trả về mảng chứa x và y
	 public int[] getXY() {
		 return new int[] {x,y};
	 }
	 public void setXY(int x, int y) {
		 this.x=x;
		 this.y=y;
	 }
	 //tính khoảng cách từ điểm hiện tại đến (x, y)
	 public double distance(int x, int y) {
		 return Double.parseDouble(String.format("%.2f", Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2))));
	 }
	  //tính khoảng cách từ điểm hiện tại đến điểm khác 
	 public double distance(MyPoint another) {
		 return distance(another.x,another.y);
	 }
	//tính khoảng cách từ điểm hiện tại đến điểm O(0,0)
	 
	 public double distance() {
		 return distance(0,0);
	 }
	}



