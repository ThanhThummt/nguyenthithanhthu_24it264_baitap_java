package c2;

public class TestBook2 {
	public static void main(String[] args) {
		Author2 authors[] = new Author2[2];
		authors[0] = new Author2("Tan Ah Teck", "AhTeck@somewhere.com", 'm');
		authors[1] = new Author2("Paul Tan", "Paul@nowhere.com", 'm');

		Book2 javaDummy = new Book2("Java for Dummy", authors, 19.99, 99);
		System.out.println(javaDummy);

		System.out.println("Danh sách tác giả: "+javaDummy.getAuthorNames());

	}
}
