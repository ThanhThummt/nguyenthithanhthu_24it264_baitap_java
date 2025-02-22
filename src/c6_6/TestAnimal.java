package c6_6;

public class TestAnimal {
	
	    public static void main(String[] args) {
	        Animal cat = new Cat("Kitty");
	        cat.greets(); 

	        Dog dog1 = new Dog("Buddy");
	        Dog dog2 = new Dog("Charlie");
	        dog1.greets(); 
	        dog1.greets(dog2); 

	        BigDog bigDog1 = new BigDog("Max");
	        BigDog bigDog2 = new BigDog("Rocky");

	        bigDog1.greets(); 
	        bigDog1.greets(dog1); 
	        bigDog1.greets(bigDog2); 
	    }
	}

