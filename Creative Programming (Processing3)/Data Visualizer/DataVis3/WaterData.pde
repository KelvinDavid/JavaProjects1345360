//This is another child class of data
//but is specified with data with String
//values instead of flaots

class WaterData extends Data
{
  private String value;
  
  public WaterData(String id, String date, String parameter, String _value, String unit)
  {
    super(id, date, parameter, unit);
    value = _value;
  }

  public String getValue()
  {
    return value;
  }
  
  public String toString()
  {
    return id + "," + date + "," + parameter + "," + value + "," +
    unit;
  }  
}
