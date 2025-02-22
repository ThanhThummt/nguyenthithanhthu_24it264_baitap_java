package c4_2;

public class TestPerson {
public static void main(String[] args) {
	Person p = new Person("Lisa", "VietNam");
	System.out.println(p);
	
	Student s = new Student("LAn Anh", "Đà nẵng", "IT", 2024, 500);
	System.out.println(s);
	
	Staff st = new Staff("a", "QN", "VKU", 400);
	System.out.println(st);
}
}
