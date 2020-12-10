//This is a child class of Data
//This is specifically for the data with
//float values instead of string

class ChemData extends Data
{
  private float value;
  
  //intilizes the data
  public ChemData(String id, String date, String parameter, float _value, String unit)
  {
    super(id, date, parameter, unit);
    value = _value;
  }

  //returns the FLOAT value of the data
  public float getValue()
  {
    return value;
  }
  
  //for debugging purposes printed to console
  public String toString()
  {
    return id + "," + date + "," + parameter + "," + value + "," +
    unit;
  }  
}
