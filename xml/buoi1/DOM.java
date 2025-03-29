package buoi1;
//Code đọc dữ liệu từ file XML

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOM {
	public static void main(String[] args) {
		try {
			// Tạo DocumentBuilderFactory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			// Phân tích tài liệu XML thành đối tượng Document
			Document doc = db.parse("D:\\java\\XML\\src\\buoi1\\products.xml");

			// Lấy danh sách các phần tử product
			NodeList productList = doc.getElementsByTagName("product");

			// Duyệt qua các phần tử product và hiển thị thông tin
			for (int i = 0; i < productList.getLength(); i++) {
				Element product = (Element) productList.item(i);
				String name = product.getElementsByTagName("name").item(0).getTextContent();
				String price = product.getElementsByTagName("price").item(0).getTextContent();
				System.out.println("Product: " + name + ",Price: " + price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
