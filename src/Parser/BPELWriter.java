package Parser;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;

public class BPELWriter {	
	public String writeString(List<Operation> operations) {
		Element rootElement = new Element("sequence");
		for (Operation operation : operations) {
			rootElement.appendChild(writeOperation(operation));
		}
		
		return rootElement.toXML();
	}
	
	public Element writeOperation(Operation operation) {
		Element element = new Element("invoke");
		element.addAttribute(new Attribute("partnerLink", operation.getPartnerLink()));
		element.addAttribute(new Attribute("portType", operation.getPortType()));
		element.addAttribute(new Attribute("operation", operation.getOperation()));
		element.addAttribute(new Attribute("inputVariable", operation.getInputsString()));
		
		return element;
	}
}
