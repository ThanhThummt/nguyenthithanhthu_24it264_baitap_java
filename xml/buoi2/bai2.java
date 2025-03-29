package buoi2;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

/*2. Đọc dữ liệu từ XML với cấu trúc phức tạp:
Yêu cầu: đọc và hiển thị dữ liệu từ XML và hiển thị ra màn hình
*/
public class bai2 {
public static void main(String[] args) {
	File fileXML = new File("src\\buoi2\\company.xml");
	try {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(fileXML);
    
    //Lấy danh sách tất cả employee
    NodeList employeeList = doc.getElementsByTagName("employee");
    
    System.out.println("Company Employee Details:");
    System.out.println("-------------------------");
    
    //Duyệt từng nhân viên trong danh sách
    for(int i=0; i<employeeList.getLength(); i++) {
    	Node empNode = employeeList.item(i);
    	
    	if(empNode.getNodeType() == Node.ELEMENT_NODE) {
    		Element empElement = (Element) empNode;//trích dưữ liệu
    		
    		String id = empElement.getAttribute("id");
    		String name = empElement.getElementsByTagName("name").item(0).getTextContent();
    		
    		Element contact = (Element) empElement.getElementsByTagName("contact").item(0);
    		String email = contact.getElementsByTagName("email").item(0).getTextContent();
    		String phone = contact.getElementsByTagName("phone").item(0).getTextContent();
    		
    		Element department = (Element) empElement.getElementsByTagName("department").item(0);
    		String namede = department.getElementsByTagName("name").item(0).getTextContent();
    		String location = department.getElementsByTagName("location").item(0).getTextContent();
    		
    		System.out.println("Employee ID: " + id);
    		System.out.println("Name: " + name);
    		System.out.println("Contact---");
    		System.out.println("Email: " + email);
    		System.out.println("Phone: " + phone);
    		System.out.println("Department---");
    		System.out.println("Name: " + namede);
    		System.out.println("Location: " + location);
    		System.out.println("-------------------------");

    	}  
    }   
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
