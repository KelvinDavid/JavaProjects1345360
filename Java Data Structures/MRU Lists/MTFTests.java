//Kelvin David 1345360
//JUnit tests for WordList
//--------------------------------
//These are the JUnit tests for the WordList class
//Focus is on the methods add(), order(), find()

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MTFTests
{
	//==========================================================================
	// Setup for tests
	//==========================================================================
	WordList w;

	@Before
	public void setUp()
	{
		w = new WordList();
		w.add("E"); w.add("D"); w.add("C"); w.add("B"); w.add("A");
	}
	//==========================================================================
	// add() Tests
	//==========================================================================
	
   	@Test //<+test>: Checking if add() method works
   	      //Assumption: Should be added
  	public void PositiveTestAddOne() 
  	{
    		w.add("F");
     		assertEquals("<Testing if item added>",0, w.find("F"));
 	}
 	@Test //<+test>: Check if add() links to front of the list
 	      //Assumption: Should be in front of the list
   	public void PositiveTestAddTwo() 
  	{
    		w.add("F");
     		assertEquals("<Testing if item is front of the list>","F", w.getWord(0));
 	}
	@Test //<+test>: Check if the last item added is front of the list
	      //Assumption: Second item should be in front added to front first is pushed
  	public void PositiveTestAddThree() 
  	{
    		w.add("F");
    		w.add("G");
     		assertEquals("<Testing if last item added is in front>",0, w.find("G"));
 	}
 	@Test //<-test>: Check when adding a null value
 	      //Assumption: Doesn't add it 
 	public void NegativeTestAddOne()
 	{
 		int bCount = w.length();
 		w.add("");
 		assertTrue("<Testing when a null string is added>",w.length() == bCount);
 	}
 	@Test //<-test>: Check when a item is added that already exists 
 	      //expected not to add two copies
 	      //Assumption: Should not add another copy of the element
  	public void NegativeTestAddTwo()
 	{
 		int bCount = w.length();
 		w.add("B");
 		w.add("A");
 		assertTrue("<Testing for when a item that already exists is added>", w.length() == bCount);
 	}
 	@Test //<-test>: When the same string is added twice in a row
 	      //Assumption: Should not move from position 0
 	public void NegativeTestAddThree()
 	{
 		w.add("G");
 		w.add("G");
 		assertEquals("<Testing for when the same string is added twice>", 0, w.find("G"));
 	}
	//==========================================================================
	// order() Tests
	//==========================================================================	
	
	@Test //<+test>: Checking if order() method works
	      //Assumption: The given item shoul be in front
	public void PositiveTestOrderOne()
	{
		w.order("D");
		assertEquals("<Testing if item was ordered>",0, w.find("D"));
	}
	@Test //<+test>: Check if order() brings item back to front
	      //Assumption: The given item should be in front
	public void PositiveTestOrderTwo()
	{
		w.order("D");
		assertEquals("<Testing if item was brought to front>","D", w.getWord(0));
	}
	@Test //<+test>: Check if order() removes item from previous index
	      //Assumption: the orginal index should no longer equal the ordered item
	public void PositiveTestOrderThree()
	{
		int before = w.find("D");
		String letter = "D";
		w.order("D");
		assertFalse("<Testing if item was removed from prevous index>",letter.equals(w.getWord(before)));
	}
	@Test //<-test>: Check when item that does not exists is ordered
	      //Assumption: Should do nothing and not add the non existent element
	public void NegativeTestOrderOne()
	{
		w.order("Z");
		assertEquals("<Testing if a item is ordered that doesn't exsit in list>",-1, w.find("Z"));
	}
	@Test //<-test>: Check when given a null string
	      //Assumption: Should do nothing and not add the non exisitent element the head should stay the same
	public void NegativeTestOrderTwo()
	{
		String prevHead = w.getWord(0);
		w.order("");
		assertTrue("<Testing if a ordered item is empty>",w.getWord(0).equals(prevHead));
	}
	@Test //<-test>: Check if the given string is already in front
	      //Assumption: Should do nothing and leave ordered item in front
	public void NegativeTestOrderThree()
	{
		String prevHead = w.getWord(0);
		w.order("A");
		assertEquals("<Testing if a ordered item is already infront>", prevHead, w.getWord(0));		
	}
	//==========================================================================
	// find() Tests
	//==========================================================================
 	
 	@Test //<+test>: Check if find() method works
 	      //Assumption: return correct index
 	public void PositiveTestFindOne()
 	{
 		assertEquals("<Testing if item was found>",2, w.find("C"));
 	}
 	@Test //<+test>: Check if find() can find moved item
 	      //Assumption: return the front index
 	public void PositiveTestFindTwo()
 	{
 		w.order("C");
 		assertEquals("<Testing if item can be found even when moved>", 0 ,w.find("C"));
 	}
	@Test //<+test>: Check if find() recognizes a change in list 
	      //Assumption: The prevous index should not be the same as current after order()
	public void PositiveTestFindThree()
	{
		int prev = w.find("C");
		w.order("C");
		assertTrue("<Testing it can find a the same character in the change list>",prev != w.find("C"));
	}
 	@Test //<-test>: Check exception when item does not exist
 	      //Assumption: return -1 which means non-existant
 	public void NegativeTestFindOne()
 	{
 		assertEquals("<Testing for when item is not in list>",-1, w.find("K"));
 	}
  	@Test //<-test>: Check exception when trying to find empty
  	      //Assumption: return -1 means non-existant
 	public void NegativeTestFindTwo()
 	{
 		assertEquals("<Testing for when finding empty string>",-1, w.find(""));
 	}
 	@Test //<-test>: Check exception when find() on empty list
 	      //Assumptions: Do nothing return -1
 	public void NegativeTestFindThree()
 	{
 		WordList f = new WordList();
 		assertEquals("<Testing for finding in empty list>", -1, f.find("D"));
 	}
 	//==========================================================================
	// length() Tests
	//==========================================================================
	
	@Test //<+test>: Check if length works
	public void PositiveTestLengthOne()
	{
		assertEquals("<Testing if the correct length is been returned>",5, w.length());
	}
	//==========================================================================
	// getWord() Tests
	//==========================================================================
	
	@Test //<+test>: Check if a word can get retrieved
	public void PositiveTestGetWordOne()
	{
		assertEquals("<Testing if the correct word was retrieved>", "C", w.getWord(2));
	}
}
