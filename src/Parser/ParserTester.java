package Parser;
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
		WSDLParser parser = new WSDLParser("wsdl/WatchingMovie_Movie.wsdl");
		parser.parser();
		
		List<Operation> infos = parser.getOperationInfos();
		System.out.println(new BPELWriter().writeString(infos));
	}

}
