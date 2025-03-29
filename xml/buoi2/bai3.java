package buoi2;
/*3. Tìm kiếm và xóa 1 trường dữ liệu trong XML
 Yêu cầu: Tạo 1 xml chứa các phần tử sinh viên như: id, tên, msv, lớp,…Nhập id cần xóa từ bàn phím và xóa phần tử đó trong XML
*/
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class bai3 {
    public static void main(String[] args) {
        File fileXML = new File("src\\buoi2\\students.xml");

        try {
            // Đọc tệp XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileXML);
            doc.getDocumentElement().normalize();

            // Nhập ID sinh viên cần xóa
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập ID student cần xóa: ");
            String idDelete = sc.nextLine();

            NodeList studentList = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < studentList.getLength(); i++) {
                Element student = (Element) studentList.item(i);
                String id = student.getAttribute("id"); // Lấy ID từ thuộc tính

                if (id.equals(idDelete)) {
                    student.getParentNode().removeChild(student); // Xóa phần tử student
                    found = true;
                    break; // Thoát vòng lặp vì đã tìm thấy
                }
            }

            if (found) {
                // Ghi lại vào file XML
                TransformerFactory trFactory = TransformerFactory.newInstance();
                Transformer transformer = trFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(fileXML);
                transformer.transform(source, result);

                System.out.println("Xóa học sinh thành công!");
            } else {
                System.out.println("Không tìm thấy học sinh!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
