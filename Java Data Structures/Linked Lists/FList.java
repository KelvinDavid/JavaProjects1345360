//Kelvin David 1345360
//FList code - Part One - 

//This code creates a list class that joins elements together
//By linking them together by having each item remember whos
//In front of it.
class FList{

	private FNode head = null; //The first item in the list
	
	//This class represents the items in the list,
	//each item has the properties behind
	private class FNode{
		public float value;
		public FNode next = null;

		public FNode(float x){
			value = x;
		}
	}
	//This class adds an element to link list
	public void add(float x){
		FNode curr = new FNode(x); //creates new node
		curr.next = head; //Links new node to previous head
		head = curr; //makes new node the new head of the list
		return;
	}
	//Checks if given element exists in link list
	public boolean has(float x){
		FNode curr = head;
		while(curr != null){ //loop until the end of the list
			if(curr.value == x){ //checks if value is found
				return true;
			}
			else {
				curr = curr.next; //moves to next node in list
			}
		}
		return false;		
	}
	//Print out all the elements inside the list
	public void dump(){
		FNode curr = head; 
		while (curr != null){ //loop through whole list and print each node
			System.out.println(curr.value);
			curr = curr.next;
		}
		return;
	}
	//Returns the number of elements found in the link list
	public int length(){
		FNode curr = head;
		int count = 0;
		while(curr != null){ //loop through list and count each node
			curr = curr.next;
			count++;
		}
		return count;
	}
	//Finds and remove's given element
	public void remove(float x){
		FNode curr = head;
		if(!has(x)){ //check if node exists in list
			return;
		}
		if(length() == 1){ //check if there is only one item in list
			head = null;
			return;
		}
		if (curr.value == x){ //check if the head is being removed
			head = curr.next;
			curr.next = null;
			return;
		}
		while(curr != null){ //loop through list
			if (curr.next.value == x){ //if node has been found
				if (curr.next.next == null || curr.next == null){ //if second to/last element
					curr.next = null;
					return;
				}
				curr.next = curr.next.next;//discconect the element from list
				return;
			}
			curr = curr.next;
		}
		return;
	}
	//Checks if link list is empty
	public boolean isEmpty(){
		if (length() == 0){
			return true;
		}
		return false;
	}
		
}

