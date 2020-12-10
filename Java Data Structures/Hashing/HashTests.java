//Kelvin David 1345360
//JUnit tests for put()method of the hashTable class
//--------------------------------
//These are the JUnit tests for the WordList class
//Focus is on the put() method

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HashTests
{
	//==========================================================================
	// Setup for tests
	//==========================================================================
	RHashtable w;

	@Before
	public void setUp()
	{
		w = new RHashtable(3);
		w.put(new Record("John", "123456789", "hello@blank.com")); 
		w.put(new Record("Chloe", "987654321", "hey@blank.com"));
	}
	//==========================================================================
	// put() Tests
	//==========================================================================
	
   	@Test //<+test>: Checking if put() method works
   	      //Assumption: Should be added
  	public void PositiveTestPutOne() 
  	{
    		w.put((new Record("Britany", "123459876", "hiya@blank.com")));
     		assertEquals("<Testing if item added>",3, w.getCount());
 	}
 	@Test //<+test>: Check if put() can add long hash codes
 	      //Assumption: Should be added
   	public void PositiveTestPutTwo() 
  	{
    		w.put((new Record("abcdefghigklmnopqrstuvwxyzabcdefghijklmnop", "123459876", "hiya@blank.com")));
     		assertEquals("<Testing if item added>",3, w.getCount());
 	}
 	@Test //<-test>: Check when adding a null value
 	      //Assumption: Doesn't add it 
 	public void NegativeTestPutOne()
 	{
 		Record r = new Record("", "", "");
 		w.put(new Record("", "", ""));
 		assertEquals("<Testing when a null string is added>", 2, w.getCount());
 	}
 	@Test //<-test>: When item is added when table is full 
 	      //Assumption: Should not add the element
  	public void NegativeTestAddTwo()
 	{
 		w.put((new Record("Britany", "123459876", "hiya@blank.com")));
 		assertEquals("<Testing for when adding into a full table>", false, w.put((new Record("Cory", "123459876", "yellow@blank.com"))));
 	}
}
