package Parser;
import static org.junit.Assert.*;

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
		WSDLParser parser = new WSDLParser();
		parser.parser("wsdl/WatchingMovie_Movie.wsdl");
	}

}
