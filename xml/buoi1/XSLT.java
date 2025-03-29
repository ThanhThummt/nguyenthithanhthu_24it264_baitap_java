package buoi1;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLT {
public static void main(String[] args) {
	try {
	// Tạo TransformerFactory
	TransformerFactory factory = TransformerFactory.newInstance();
	// Tạo Transformer từ XSLT
	Source xslt = new StreamSource(new File("src//buoi1//products.xsl"));
	Transformer transformer = factory.newTransformer(xslt);
	
	//Chuyển đổi XML thành HTML
	Source xml = new StreamSource(new File("src//buoi1//products.xml"));
	transformer.transform(xml,new StreamResult(new File("src\\buoi1\\output.html")));
	System.out.println("Transformation completed.");
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
