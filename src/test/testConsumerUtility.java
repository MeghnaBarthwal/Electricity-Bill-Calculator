package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import javafiles.Consumer;
import jdbc.ConsumerUtility;

public class testConsumerUtility {

	@Test
	public void testValidateConsumer() throws ClassNotFoundException, SQLException {
		Consumer cobj = new Consumer(11,"test", "test", "test", "test", "test");
		assertEquals( true, cobj.equals(ConsumerUtility.validateConsumer(11, "test")));
	}
	
//	@Test
//	public void 
}
