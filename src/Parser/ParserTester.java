package Parser;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
	public void test() {
		List<String> paths = new ArrayList<String>();
		paths.add("wsdl/WatchingMovie_Movie.wsdl");
		paths.add("wsdl/WatchingMovie_Showtime.wsdl");
		paths.add("wsdl/WatchingMovie_Theater.wsdl");
		
		BPELWriter writer = new BPELWriter();
		for (String string : paths) {
			WSDLParser parser = new WSDLParser(string);
			parser.parser();
			
			List<Operation> infos = parser.getOperationInfos();
			writer.addElements(infos);
		}
		
		System.out.println(writer.getXMLString());
	}

}
