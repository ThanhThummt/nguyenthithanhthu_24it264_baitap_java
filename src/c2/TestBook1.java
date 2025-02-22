package c2;

public class TestBook1 {
	   public static void main(String[] args) {
		   Author1 ahTeck = new Author1("Tan Ah Teck", "ahteck@nowhere.com", 'm');
		   System.out.println(ahTeck);  

		   Book1 dummyBook = new Book1("Java for dummy", ahTeck, 19.95, 99); 
		   System.out.println(dummyBook);  

		   dummyBook.setPrice(29.95);
		   dummyBook.setQty(28);
		   System.out.println("name is: " + dummyBook.getName());
		   System.out.println("price is: " + dummyBook.getPrice());
		   System.out.println("qty is: " + dummyBook.getQty());
		   System.out.println("Author is: " + dummyBook.getAuthor());  
		   System.out.println("Author's name is: " + dummyBook.getAuthor().getName());
		   System.out.println("Author's email is: " + dummyBook.getAuthor().getEmail());

		   Book1 anotherBook = new Book1("more Java", 
		         new Author1("Paul Tan", "paul@somewhere.com", 'm'), 29.95);
		   System.out.println(anotherBook);  
	}
	    }
	


