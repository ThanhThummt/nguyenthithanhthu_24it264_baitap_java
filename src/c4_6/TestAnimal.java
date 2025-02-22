package c4_6;

public class TestAnimal {
 public static void main(String[] args) {
	 Cat cat = new Cat("Whiskers");
     Dog dog1 = new Dog("Rex");
     Dog dog2 = new Dog("Buddy");

     System.out.println(cat);
     cat.greets();

     System.out.println(dog1);
     dog1.greets();
     dog1.greets(dog2); 
}
}
