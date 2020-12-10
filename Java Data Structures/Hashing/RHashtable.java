//Kelvin David 1345360
//-----------------------------------------
//This is the class for the open addressing 
//hash table that implements only a put(), getCollisions()
//and getLoad() methods

class RHashtable implements RHashtableInt
{
	private Record[] table; //Holds the records (The table itself)
	private int capacity; //The size of the table
	private int count; //The number of records in the table
	private int collisions; //Number of collisions
	
	//Initilizes the table
	public RHashtable(int size)
	{
		capacity = size;
		count = 0;
		collisions = 0;
		table = new Record[capacity];
	}
	
	//This calculates the index and inserts the
	//record into the hash table
	public boolean put(Record r)
	{
		int intialHash =  r.hashCode(); //get record hast code
		int skip  = 5; //This is the skip for re-hashing
		
		//if the record is empty 
		if(intialHash == 0) 
		{
			return false; //do not insert
		}
		//calculate the inital index of the record
		int key = intialHash % capacity;
		//Keep track of it's inital index
		int origin = key;
		//if the inital address is empty
		if(table[key] == null)
		{
			count++; //increment the counter
			table[key] = r; //add it to the table
			return true; //return it was sucessfull
		}
		else
		{
			//start linear probing (move to next address)
			key = (key + 1) % capacity;
			//probe until it does a full loop
			while(key != origin)
			{
				//if it finds a empty adress
				if(table[key] == null)
				{
					count++; //increment the counter
					table[key] = r; //add it to the table
					return true; //return it was sucessfull
				}
				//if it reaches the end of the table
				if(key == (capacity - 1))
				{
					key = 0; //loop back to the start of the table
				}
				else
				{
					key = (key + skip) % capacity; //move to next key
					skip += 4; //increment the skip size
				}
				collisions++; //increment collisions
			}
			return false; //else return that it's false
		}
	}
	//getter for the amount of records in the table
	public int getCount()
	{
		return count;
	}
	//getter for the collision amount
	public int getCollisions()
	{
		return collisions;
	}
	//calculate and get the load of the hash table
	public float getLoad()
	{
		return (float)count/(float)capacity;
	}
}
