//Kelvin David 1345360
//WordList - MRU List
//------------------------------------
//This is the MRU list that the encoders 
//both use to hold the words extracted
//------------------------------------

class WordList
{

	//Holds the front of the list
	private Item head = null;
	private int first = 0;
	
	//Items to hold information about the words
	private class Item
	{
		private Item next = null; //Holds next item
		private int index = 0; //Holds Index
		private String value; //Holds content
		
		public Item(String s)
		{
			value = s;
		}
	}
	//This creates and adds an item to wordList
	public void add(String s)
	{
		//Checks if already part of list
		int itemIndex = find(s);
		//If it isn't
		if(itemIndex == -1)
		{
			if(s.equals(""))
			{
				return;
			}
			//If wordList is not empty
			if(head != null)
			{
				//re-order
				first = 1;
				order(s);
				first = 0;
				return;
			}
			//make item as head
			head = new Item(s);
			return;
		}
		//re-order
		order(s);
		return;		
	}

	//Find the given string
	public int find(String s)
	{
		Item curr = head;
		//Loop till end
		while(curr != null)
		{
			//if item has been found
			if(curr.value.equals(s))
			{
				return curr.index; //return value
			}
			curr = curr.next;
		}
		return -1;
	}
	
	//Bring the given String to the front
	public void order(String s)
	{
		int itemIndex = find(s);
		if (itemIndex == -1 && first == 1 || itemIndex > 0)
		{
			Item curr = head;
			Item prev = null; 
			//Loop till end
			while(curr != null)
			{
				//if it reached the MRU item
				if(curr.index == itemIndex)
				{
					//if already front do nothing
					if(prev == null)
					{
						return;	
					}
					//remove it
					prev.next  = curr.next;
					break;
				}
				//increment current item index
				curr.index += 1;
				//move current item up
				prev = curr;
				curr = curr.next;
			}
			//move item back to the front and make head
			curr = new Item(s);
			curr.next = head;
			head = curr;
			return;	
		}
		else
		{
			return;
		}
	}	
	
	//Get word at the given index
	public String getWord(int index)
	{
		String value;
		Item curr = head;
		while(curr != null)
		{
			//return the item with the given index
			if(curr.index == index)
			{
				return curr.value;
			}
			curr = curr.next;		
		}
		return null;
	}
	//Returns the amount of unique words
	public int length()
	{
		Item curr = head;
		int count = 0;
		while(curr != null)
		{
			count++;
			curr = curr.next;
		}
		return count;	
	}
	//Dumps the list in console #DEBUGGING PURPOSES#
	public void dump()
	{	
		Item curr = head;
		while(curr != null)
		{
			System.out.println(curr.index + ". " +curr.value);
			curr = curr.next;
		}
		System.out.println("======================================");
		return;
	}
}
