//Kelvin David 1345360
//BSTlex binary search tree
//--------------------------------
class BSTlex
{
	//SETUP ================================================================
	private FNode root = null; //head of bst
	private int inner = 0; //to check if being called from within
	private String output = "";

	//This class is the holds the given items in the BST
	private class FNode
	{
		private String value;
		private FNode Left = null;
		private FNode Right = null;
		
		public FNode(String input)
		{
			value = input;
		}
	}

	public String getOutput()
	{
		return output;
	}
	//SETUP ================================================================
	//INSERT METHOD ========================================================

	//This is the public method accessible outside of the class
	public void insert(String s)
	{
		output = "";
		if(s  == null || s.equals(""))
		{
			return;
		}
		root = insertN(root, s);
		//System.out.println("");
	}

	//This is the helper method that processes the given item's insertion
	private FNode insertN(FNode n, String s)
	{
		if(n == null)//if current position is null
		{
			n = new FNode(s);
			output += n.value;
			return n;
		}
		output += (n.value + " "); //print current position
		if(s.compareTo(n.value) == 0)//if the same
		{
			return n;//do nothing
		}
		if(s.compareTo(n.value) < 0)//if less
		{
			//System.out.print(" LEFT ");
			n.Left = insertN(n.Left, s);
		}
		else //if more
		{
			//System.out.print(" RIGHT ");
			n.Right = insertN(n.Right, s);
		}
		return n;
	}
	//INSERT METHOD ========================================================

	//FIND METHOD ==========================================================

	//The public method accessible outside fo the class
	public boolean find(String s)
	{
		if(s  == null || s.equals(""))
		{
			return false;
		}
		//System.out.println(""); //DEBUG
		//System.out.print("(LOOKING FOR: " + s + ") "); //DEBUG
		boolean result = findN(root, s) != null;
		//System.out.println(result); //DEBUG
		return result;
	}
	//Helper method that processes the search for given item
	private	FNode findN(FNode n, String s)
	{
		if(n == null) //if current is empty
		{
			//System.out.print("<" + "null" + "> ");
			return null;
		}
		if(s.compareTo(n.value) == 0)//if found
		{
			//System.out.print("[" + n.value + "] " + "FOUND: ");;
			return n;
		}
		//System.out.print("<" + n.value + "> ");
		if(s.compareTo(n.value) < 0)//if less
		{
			//System.out.print("LEFT ");
			return findN(n.Left, s);
		}
		else//if more
		{
			//System.out.print("RIGHT ");
			return findN(n.Right, s);
		}

	}
	//FIND METHOD ==========================================================

	//REMOVE METHOD ========================================================

	//public method accessible outside the class
	public void remove(String s)
	{
		output = "";
		if(s  == null || s.equals(""))
		{
			return;
		}
		root = removeN(root, s);
		inner = 0;
		//System.out.println("");

	}
	//Finds finds the minimum of hence node
	private FNode farLeft(FNode n)
	{
		if(n.Left == null)
			return n;
		else
			return farLeft(n.Left);
	}
	//Helper class the processes the removal of given item 
	private FNode removeN(FNode n, String s)
	{
		if(n == null)//if tree is null or not found
		{
			return n;
		}
		if(s.compareTo(n.value) < 0)//if less
		{	
			if (inner == 0)output += (n.value + " ");
			n.Left = removeN(n.Left, s);
		}
		else if(s.compareTo(n.value) > 0)//if more
		{
			if (inner == 0)output += (n.value + " ");
			n.Right = removeN(n.Right, s);
		}
		else//if found
		{
			if(n.Left == null && n.Right == null)//if no children
			{
				if (inner == 0)output += n.value;
				n = null;
				return n;
			}
			else if(n.Left != null && n.Right == null)//if only left child
			{
				if (inner == 0)output += n.value;
				n = n.Left;
				return n;
			}
			else if(n.Right != null && n.Left == null)//if only right child
			{
				if (inner == 0)output += n.value;
				n = n.Right;
				return n;
			}
			else//if both children
			{
				if (inner == 0)output += n.value;
				FNode rightMin = farLeft(n.Right); //find sucessor
				n.value = rightMin.value;//replace
				inner = 1;//recall for inner remove
				n.Right =  removeN(n.Right, rightMin.value);//remove sucessor
			}
		}
		return n;
	}
	//REMOVE METHOD ========================================================

	//DUMP METHOD ==========================================================

	//Public method to print list to console
	public void dump()
	{
		System.out.println("");
		System.out.println("LEXICON:");
		traverse(root);
	}

	//Traverses the list in order to print out in order
	private void traverse(FNode n)
	{
		if(root == null)
		{
			return;
		}
		if(n.Left != null) traverse(n.Left);
		System.out.println(n.value);
		if(n.Right != null) traverse(n.Right);
	}

	//DUMP METHOD ==========================================================

	//FIND CHILD METHOD (JUNIT PURPOSES)====================================
	
	//For debuging to find the current child of the given string
	public String findChild(String s, String direction)
	{
		FNode parent = findN(root, s);
		if(direction.equals("Left"))
		{
			if (parent.Left == null)
			{
				return s + " null";
			}
			else
			{
				return parent.Left.value;
			}
		}
		else if(direction.equals("Right"))
		{
			if (parent.Left == null)
			{
				return s + " null";
			}
			else
			{
				return parent.Right.value;
			}
		}
		else
		{
			return null;
		}
	}
		
	//FIND CHILD METHOD (JUNIT PURPOSES)====================================

	//HEIGHT METHOD (JUNIT PURPOSES)========================================
	
	public int height()
	{
		int count = 0;
		if (root != null)
		{
			count = traverseHeight(root);
		}
		return count;
	}

	private int traverseHeight(FNode n)
	{
		int count = 1;
		if(n.Left != null)
		{
			count += traverseHeight(n.Left);
		}
		if(n.Right != null)
		{
			count += traverseHeight(n.Right);
		}
		return count;
	}
	
	//HEIGHT METHOD (JUNIT PURPOSES)========================================
}
