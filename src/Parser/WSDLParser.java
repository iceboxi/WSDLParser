package Parser;
import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


public class WSDLParser {
	public void parser(String path) {
		try {
			Document document = new Builder().build(path);
			
			Element rootElement = document.getRootElement();
			
			Element portTypeElement = rootElement.getFirstChildElement("portType", rootElement.getNamespaceURI());
			System.out.println("portType = " + portTypeElement.getAttributeValue("name"));
			
			Element operationElement = portTypeElement.getFirstChildElement("operation", portTypeElement.getNamespaceURI());
			System.out.println("operation = " + operationElement.getAttributeValue("name"));
			
			Element inputElement = operationElement.getFirstChildElement("input", operationElement.getNamespaceURI());
//			System.out.println(inputElement.getAttributeValue("message"));
			
			String message = inputElement.getAttributeValue("message").split(":")[1];
//			System.out.println(message);
			
			Elements messageElements = rootElement.getChildElements("message", rootElement.getNamespaceURI());
			for (int i = 0; i < messageElements.size(); i++) {
				Element messageElement = messageElements.get(i);
				if (messageElement.getAttributeValue("name").equals(message)) {
					Element partElement = messageElement.getFirstChildElement("part", messageElement.getNamespaceURI());
					System.out.println("inputName = " + partElement.getAttributeValue("type").split(":")[1]);
				}
			}
			
			
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
