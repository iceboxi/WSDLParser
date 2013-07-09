package Parser;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ParserTester {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
		List<String> paths = new ArrayList<String>();
		paths.add("wsdl/WatchingMovie_Movie.wsdl");
		paths.add("wsdl/WatchingMovie_Showtime.wsdl");
		paths.add("wsdl/WatchingMovie_Theater.wsdl");
		
		BPELWriter writer = new BPELWriter();
		for (String string : paths) {
			WSDLParser parser = new WSDLParser(string);
			parser.parser();
			
			List<Operation> infos = parser.getOperationInfos();
			writer.appendElementList(infos);
		}
		
		System.out.println(writer.getXMLString());
	}

	@Test
	public void testSingle() {
		WSDLParser parser = new WSDLParser("wsdl/WatchingMovie_Movie.wsdl");
		parser.parser();
		
		BPELWriter writer = new BPELWriter();
		writer.appendElementList(parser.getOperationInfos());
		
		System.out.println(writer.getXMLString());
	}
	
	@Test
	public void testWriteFile() {
		WSDLParser parser = new WSDLParser("wsdl/WatchingMovie_Movie.wsdl");
		parser.parser();
		
		BPELWriter writer = new BPELWriter();
		writer.appendElementList(parser.getOperationInfos());
		
		Assert.assertTrue(writer.writeToFile("bpel/test1.txt"));
	}
}
