package c2;

public class Author3 {
private String name;
private String email;

public Author3(String name, String email) {
	this.name = name;
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "Author3 [name=" + name + ", email=" + email + "]";
}

}
