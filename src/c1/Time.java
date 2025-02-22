package c1;

public class Time {
	private int hour;
	private int minute;
	private int second;
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	public void setTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d:",hour,minute,second);
	}
	
	public Time nextSecond() {
		second++;
		if(second==60) {
			second=0;
			minute++;
			if(minute==60) {
				minute=0;
				hour++;
				if(hour==24) {
					hour=0;
				}
			}
		}
		return this;
	}
	
	public Time previousSecond() {
		second--;
		if(second<0) {
			second=59;
			minute--;
			if(minute<0) {
				minute=59;
				hour--;
				if(hour<0) {
					hour=23;
				}
			}
		}
		return this;
	}
	
	public static void main(String[] args) {
		Time t1 = new Time(1,2,3);
		 System.out.println(t1);
		 
		 t1.setHour(4);
	      t1.setMinute(5);
	      t1.setSecond(6);
	      System.out.println(t1);  
	      System.out.println("Hour: " + t1.getHour());
	      System.out.println("Minute: " + t1.getMinute());
	      System.out.println("Second: " + t1.getSecond());
	      
	      t1.setTime(23, 59, 58);
	      System.out.println(t1);
 
	      System.out.println(t1.nextSecond());
	      System.out.println(t1.nextSecond().nextSecond());
	      
	      System.out.println(t1.previousSecond());
	      System.out.println(t1.previousSecond().previousSecond());
	}
	
}
