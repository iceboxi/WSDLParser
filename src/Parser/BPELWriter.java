package Parser;

import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;

public class BPELWriter {	
	private Element rootElement;
	
	public BPELWriter() {
		rootElement = new Element("sequence");
	}
	
	public BPELWriter(Element rootElement) {
		this.rootElement = rootElement;
	}
	
	public void appendElement(Operation operation) {
		rootElement.appendChild(writeOperation(operation));
	}
	
	public void appendElementList(List<Operation> operations) {
		for (Operation operation : operations) {
			appendElement(operation);
		}
	}
	
	public String getXMLString() {
		return rootElement.toXML();
	}
	
	public boolean writeToFile(String filename) {
		WriteFile write = new WriteFile();
		return write.writeText(getXMLString(), filename, "utf8", false);
	}
	
	private Element writeOperation(Operation operation) {
		Element element = new Element("invoke");
		element.addAttribute(new Attribute("partnerLink", operation.getPartnerLink()));
		element.addAttribute(new Attribute("portType", operation.getPortType()));
		element.addAttribute(new Attribute("operation", operation.getOperation()));
		element.addAttribute(new Attribute("inputVariable", operation.getInputsString()));
		
		return element;
	}
}
