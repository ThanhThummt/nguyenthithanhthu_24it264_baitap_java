package buoi1;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {
	public static void main(String[] args) {
		try {
			// Tạo SAXParserFactory và SAXParser->ptich XML
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			// Tạo handler để xử lí sự kiện SAX
			DefaultHandler handler = new DefaultHandler() {
				// Gặp thẻ mở
				public void startElement(String url, String localName, String qName, Attributes attributes) {
					System.out.println("Start Element: " + qName);
				}

				// Gặp thẻ đóng
				public void endElement(String url, String localName, String qName) {//chỉ có 3 tham số ko có attributes
					System.out.println("End Element: " + qName);
				}

				// Gặp dữa liệu văn bản
				public void characters(char ch[], int start, int length) {
					String content= new String(ch, start, length).trim();
					if(!content.isEmpty()) {
						System.out.println("Character: " + content);
					}
				}
			};
			
			//Dọc tài liệu XML
			saxParser.parse("src/buoi1/products.xml", handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
