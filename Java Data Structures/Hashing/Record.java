//Kelvin David 1345360
//-----------------------------------------
//This is the object that is being stored into
//the Hashtable. It holds a person's information

class Record
{
	private String name;
	private String phone;
	private String email;

	//This initilizes the class
	public Record(String _name, String _phone, String _email)
	{
		name = _name;
		phone = _phone;
		email = _email;	
	}
	//This overrides the standard hashCode that converts the name
	//of this record into a interger
	public int hashCode()
	{
		int hash = 0;
		char[] word = name.toCharArray();//convert the string into a array of chars
		for(char c : word)
		{
			int ascii = (int)c;	//Add all the ascii values together
			hash += ascii;
		}
		hash = ((hash % 10) * 100000) + (hash / 10); //Hash formulae
		return hash;
		
	}
}
