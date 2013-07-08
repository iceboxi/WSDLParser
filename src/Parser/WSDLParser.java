package Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


public class WSDLParser {
	private String partnerLink;
	private Element rootElement;
	private List<Operation> operationInfos;
	
	public WSDLParser(String path) {
		try {
			Document document = new Builder().build(path);
			rootElement = document.getRootElement();
			
			operationInfos = new ArrayList<Operation>();
			partnerLink = path.substring(path.lastIndexOf("/") + 1);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Operation> getOperationInfos() {
		return operationInfos;
	}
	
	public void parser() {
		Elements portTypeElements = rootElement.getChildElements("portType", rootElement.getNamespaceURI());
		for (int i = 0; i < portTypeElements.size(); i++) {
			Element portTypeElement = portTypeElements.get(i);
			String portType = portTypeElement.getAttributeValue("name");
			
			Elements operationElements = portTypeElement.getChildElements("operation", portTypeElement.getNamespaceURI());
			for (int j = 0; j < operationElements.size(); j++) {
				Element operationElement = operationElements.get(j);
				Operation operation = new Operation(partnerLink, portType);
				operation.setOperation(operationElement.getAttributeValue("name"));
				
				Element inputElement = operationElement.getFirstChildElement("input", operationElement.getNamespaceURI());
				String message = inputElement.getAttributeValue("message").split(":")[1];
				
				parserInputName(message, operation);
				
				operationInfos.add(operation);
			}
		}
	}
	
	private void parserInputName(String message, Operation operation) {
		Elements messageElements = rootElement.getChildElements("message", rootElement.getNamespaceURI());
		for (int i = 0; i < messageElements.size(); i++) {
			Element messageElement = messageElements.get(i);
			if (messageElement.getAttributeValue("name").equals(message)) {
				Elements partElements = messageElement.getChildElements("part", messageElement.getNamespaceURI());
				
				for (int j = 0; j < partElements.size(); j++) {
					Element partElement = partElements.get(j);
					operation.addInput(partElement.getAttributeValue("type").split(":")[1]);
				}
			}
		}
	}
}
