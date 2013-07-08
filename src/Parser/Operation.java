package Parser;

import java.util.ArrayList;
import java.util.List;

public class Operation {
	private String partnerLink;
	private String portType;
	private String operation;
	private List<String> inputs;
	
	public Operation(String partnerLink, String portType) {
		this.partnerLink = partnerLink;
		this.portType = portType;
		inputs = new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return String.format("PortType = %s, \n Operation = %s, \n Inputs = %s", portType, operation, inputs.toString());
	}

	public String getPortType() {
		return portType;
	}
	public void setPortType(String portType) {
		this.portType = portType;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<String> getInputs() {
		return inputs;
	}
	public void setInputs(List<String> inputs) {
		this.inputs = inputs;
	}
	public String getPartnerLink() {
		return partnerLink;
	}
	public void setPartnerLink(String partnerLink) {
		this.partnerLink = partnerLink;
	}

	public void addInput(String input) {
		inputs.add(input);
	}
	public String getInputsString() {
		String inputString = null;
		
		if (inputs.size() > 0) {
			inputString = inputs.get(0);
		}
		for (int i = 1; i < inputs.size(); i++) {
			inputString += ("," + inputs.get(i));
		}
		
		return inputString;
	}
}
