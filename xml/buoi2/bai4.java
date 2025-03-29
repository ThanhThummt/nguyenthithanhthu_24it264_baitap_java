package buoi2;
/*4. Cập nhật dữ liệu trong file XML
Viết 1 chương trình cập nhật dữ liệu của 1 sinh viên dựa trên id
Yêu cầu: Tìm kiếm bằng id sinh viên nhập từ bàn phím
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

public class bai4 {
    public static void main(String[] args) {
        File fileXML = new File("src\\buoi2\\students.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileXML);
            doc.getDocumentElement().normalize();

            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập ID để cập nhật student: ");
            String idUpdate = sc.nextLine();

            NodeList studentList = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < studentList.getLength(); i++) {
                Element student = (Element) studentList.item(i);
                String id = student.getAttribute("id"); // Lấy ID từ thuộc tính

                if (id.equals(idUpdate)) {
                    System.out.print("Nhập tên mới: ");
                    String namenew = sc.nextLine();
                    System.out.print("Nhập tuổi mới: ");
                    String agenew = sc.nextLine();
                    System.out.print("Nhập lớp mới: ");
                    String classnew = sc.nextLine();

                    student.getElementsByTagName("name").item(0).setTextContent(namenew);
                    student.getElementsByTagName("age").item(0).setTextContent(agenew);
                    student.getElementsByTagName("class").item(0).setTextContent(classnew);

                    found = true;
                    break; // Thoát vòng lặp vì đã tìm thấy và sửa
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

                System.out.println("Cập nhật học sinh thành công!");
            } else {
                System.out.println("Không tìm thấy học sinh!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
