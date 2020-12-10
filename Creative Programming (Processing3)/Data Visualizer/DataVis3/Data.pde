//This is the super class of all the data
//objects it holds all the common properties between
//each data object

abstract class Data
{
  protected String id;
  protected String date;
  protected String parameter;
  protected String unit;
  
  //this initlizes the data
  public Data(String _id, String _date, String _parameter, String _unit)
  {
    id = _id;
    date = _date;
    parameter = _parameter;
    unit = _unit;
  }
  
  //this function is for parameter comparision (like .equals())
  public boolean isParameter(String value)
  {
    if(parameter.equals(value))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  //Everything under here are gets and sets that allow
  //access to specific data
  protected String getParameter()
  {
    return parameter;
  }
  
  protected String getID()
  {
    return id;
  }
  
  protected String getDate()
  {
    return date;
  }
  
  protected String getUnit()
  {
    return unit;
  }
  //This method is a must method for all the shared
  //datatypes
  public abstract String toString();
  
  
}
