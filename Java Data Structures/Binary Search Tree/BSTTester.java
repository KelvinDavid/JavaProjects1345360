//Kelvin David 1345360
//JUnit tests for BSTlex
//--------------------------------
//These are the JUnit tests for the BSTlex class
//Focus is on the methods add(), remove(), find()

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BSTTester
{
	//==========================================================================
	// Setup for tests
	//==========================================================================
	BSTlex b;

	@Before
	public void setUp()
	{
		b = new BSTlex();
		b.insert("the"); b.insert("fox"); b.insert("ran"); b.insert("home"); b.insert("today");
	}
	//==========================================================================
	// insert() Tests
	//==========================================================================
	
   	@Test //<+test>: Checking if insert() method works
   	      //Assumption: Should be inserted
  	public void PositiveTestInsertOne() 
  	{
    		b.insert("lost");
     		assertTrue("<Testing if item added>", b.find("lost"));
 	}
 	@Test //<+test>: Check if insert() adds to the correct place of the list
 	      //Assumption: 'cat' should be left child of 'fox'
   	public void PositiveTestInsertTwo() 
  	{
    		b.insert("cat");
     		assertEquals("<Testing if inserting to left leaf works correctly>","cat", b.findChild("fox", "Left"));
 	}
	@Test //<+test>: Check if insert() adds to the correct place of the list
	      //Assumption: 'snake' should be right child of 'ran'
  	public void PositiveTestAddThree() 
  	{
    		b.insert("snake");
     		assertEquals("<Testing if inserting to right leaf works correctly>","snake", b.findChild("ran", "Right"));
 	}
 	@Test //<-test>: Check when inserting a null value
 	      //Assumption: Doesn't insert it it
 	public void NegativeTestInsertOne()
 	{
		int bCount = b.height();
 		b.insert("");
 		assertTrue("<Testing when a null string is inserted>",b.height() == bCount);
 	}
 	@Test //<-test>: Check when a item is added that already exists 
 	      //expected not to add two copies
 	      //Assumption: Should not add another copy of the element
  	public void NegativeTestInsertTwo()
 	{
 		int bCount = b.height();
		b.insert("ran");
 		assertTrue("<Testing for when a item that already exists is added>", b.height() == bCount);
 	}
	//==========================================================================
	// remove() Tests
	//==========================================================================	
	
	@Test //<+test>: Checking if remove() method works
	      //Assumption: The item should no longer exist
	public void PositiveTestRemoveOne()
	{
		b.remove("fox");
		assertFalse("<Testing if item was removed>", b.find("fox"));
	}
	@Test //<+test>: Checking if remove() rearranges the BST
	      //Assumption: when 'fox' is removed 'ran' should take it's place
	public void PositiveTestRemoveTwo()
	{
		b.remove("fox");
		 assertEquals("<Testing if remove rearranges the BST>", "ran" ,b.findChild("the", "Left"));
	}
	@Test //<-test>: Check when item that does not exists is removed
	      //Assumption: Should do nothing
	public void NegativeTestRemoveOne()
	{
		int bCount = b.height();
		b.remove("lenny");
		assertFalse("<Testing if a non-existant item is removed>", b.find("lenny") && b.height() != bCount);
	}
	@Test //<-test>: Check when given a null string
	      //Assumption: Should do nothing and not add the non exisitent element the head should stay the same
	public void NegativeTestRemoveTwo()
	{
		int bCount = b.height();
		b.remove("");
		assertTrue("<Testing if a removing empty item from BST>",b.height() == bCount);
	}
	@Test //<-test>: Check if removing from a empty BST
	      //Assumption: Should do nothing
	public void NegativeTestRemoveThree()
	{
		BSTlex f = new BSTlex();
		f.remove("fox");
		assertEquals("<Testing if a removing item from empty BST>", 0,f.height());		
	}
	//==========================================================================
	// find() Tests
	//==========================================================================
 	
 	@Test //<+test>: Check if find() method works
 	      //Assumption: return correct index
 	public void PositiveTestFindOne()
 	{
 		assertTrue("<Testing if item was found>", b.find("fox"));
 	}
 	@Test //<-test>: Check exception when item does not exist
 	      //Assumption: return false meaning non-existant
 	public void NegativeTestFindOne()
 	{
 		assertFalse("<Testing for when item is not in list>", b.find("K"));
 	}
  	@Test //<-test>: Check exception when trying to find null
  	      //Assumption: return false meaning non-existant
 	public void NegativeTestFindTwo()
 	{
 		assertFalse("<Testing for when finding empty string>", b.find(""));
 	}
 	@Test //<-test>: Check exception when find() on empty BST
 	      //Assumptions: Do nothing return false
 	public void NegativeTestFindThree()
 	{
 		BSTlex f = new BSTlex();
 		assertFalse("<Testing for finding in empty list>", f.find("dog"));
 	}
}
