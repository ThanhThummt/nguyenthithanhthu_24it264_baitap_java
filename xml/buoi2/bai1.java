package buoi2;

import java.io.*;
import java.lang.annotation.ElementType;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/*1. Viết 1 chương trình ghi dữ liệu vào 1 file XML
Yêu cầu: Chương trình cho phép nhập các thông tin của sinh viên: id, name, age, major,….
Sau khi nhập vào thì thêm thông tin của sinh viên mới vào file XML, nếu file XML chưa tồn tại thì tự động tạo file mới.
*/

public class bai1 {
public static void main(String[] args) {
	File file = new File("src\\buoi2\\students.xml");
	Scanner sc = new Scanner(System.in);
	
	//Nhập thông tin student từ bàn phím
	System.out.println("Enter id: ");
	String id = sc.nextLine();
	System.out.println("Enter name: ");
	String name = sc.nextLine();
	System.out.println("Enter age: ");
	String age = sc.nextLine();
	System.out.println("Enter major: ");
	String major = sc.nextLine();
	 sc.close(); // Đóng scanner để tránh rò rỉ tài nguyên

	
	try {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc;
	//Kiểm tra file xml có tồn tại
	if(file.exists() && file.length()>0) {
		doc = builder.parse(file);
		doc.getDocumentElement().normalize();//trả về root element bỏ qua dư thừa
	}else {
		doc = builder.newDocument();//tạo tài liệu mới
		Element rootElement = doc.createElement("students");//tạo phần tử gốc
		doc.appendChild(rootElement);//thêm rootElement vào tài liệu
	}
	
	//Thêm student vào doc XML
	Element student = doc.createElement("student");
	student.setAttribute("id", id);
	
	Element nameElement = doc.createElement("name");
	nameElement.appendChild(doc.createTextNode(name));// Tạo một nút văn bản chứa giá trị của biến name(Nguyễn Van A,...)
	student.appendChild(nameElement);
	
	Element ageElement = doc.createElement("age");
	ageElement.appendChild(doc.createTextNode(age));
	student.appendChild(ageElement);
	
	Element majorElement = doc.createElement("major");
	majorElement.appendChild(doc.createTextNode(major));
	student.appendChild(majorElement);
	
	doc.getDocumentElement().appendChild(student);

	
	//Ghi lại vào file
	TransformerFactory trFactory = TransformerFactory.newInstance();
	Transformer transformer = trFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(file);
	transformer.setOutputProperty(OutputKeys.INDENT,"yes");//định dạng XML
	transformer.transform(source, result);//ghi dữ liệu XML từ bộ nhớ vào file
	
	System.out.println("Student information added successfully.");
	}catch (Exception e) {
		e.printStackTrace();
	}
	
}
}
