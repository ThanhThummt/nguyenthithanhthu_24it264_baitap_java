package buoi1;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLTreeViewer {
public static void main(String[] args) {
	try {
		//Taọ DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		//Tạo Document
		Document doc = db.parse("src/buoi1/products.xml");
		
		//Tạo cây từ tài liêu XML
		DefaultMutableTreeNode root = createTree(doc.getDocumentElement());
		//doc.getDocumentElement().Lấy phần tử gốc của tài liệu XML
		
		//Hiển thị JTree
		JTree tree = new JTree(root);
		JFrame frame = new JFrame("XML JTree Viewer");
		frame.add(new JScrollPane(tree));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,400);
		frame.setVisible(true);	   
	}catch(Exception e){
		e.printStackTrace();
	}
}

// Hàm đệ quy tạo cây từ nút XML
public static DefaultMutableTreeNode createTree(Node node) {
//Tạo node cho cây:
  DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.getNodeName());
 //Lấy danh sách các node con
  NodeList nodeList = node.getChildNodes();
  //Duyệt qua từng node con
  for(int i=0; i<nodeList.getLength(); i++) {
	  Node childNode = nodeList.item(i);
	//Kiểm tra node loại ELEMENT_NODE
      if(childNode.getNodeType() == Node.ELEMENT_NODE) {
    	  treeNode.add(createTree(childNode));
      }
  }
  return treeNode;
  }
}

