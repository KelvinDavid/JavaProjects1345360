//This class is assiossiation of Data holding a
//arraylist of data objects, it is also repsonsible for
//dividing the data into specific parameters

class DataSet
{
  private String parameter;
  private ArrayList<Data> dataList;
  private int index;
  private float Tmax;//The max of all the collective data of the same parameter
  private float indvmax; //The max of THIS Dataset
  Button Lselect;
  
  //This initilizes the Data set
  public DataSet(String _parameter, ArrayList<Data> groupData)
  {
    index = 0;
    parameter = _parameter;
    dataList = new ArrayList<Data>();
    Lselect = new Button(width/2 - 250, 20, "view more", #7AA4D8, 100, 20);
    //println(parameter + " ME" + groupData.size());
    
    //This extracts the data from the passed arraylist
    //filtering by it;s parameter
    for(Data da : groupData)
    {
      if(da.isParameter(_parameter))
      {
        dataList.add(da);
        //println("counted");
      }
    }
    //This gets the datasets FLOAT max leaving WaterData objects alone
    if(!parameter.equals("Water_Clarity") && !parameter.equals("Water_Colour"))
    {
      indvmax = getMax();    
    }
  }
  //This method is mainly for DataSets
  //holding WaterData it calculates
  //the color of the current looked at data
  private color changeColor()
  {
    WaterData wd = (WaterData)dataList.get(index);
    
    //For water color data
    if(parameter.equals("Water_Colour"))
    {
      if(wd.getValue().equals("brown/yellow"))
      {
        return #7D7E53;
      }
      else if(wd.getValue().equals("clear"))
      {
        return #C4E5FF;
      }
      else if(wd.getValue().equals("milky/grey"))
      {
        return #97A7B4;
      }
      else if(wd.getValue().equals("green"))
      {
        return #4F8348;
      }
      else
      {
        return #094300;
      }
    }
    //for water clarity data
    else if(wd.getParameter().equals("Water_Clarity"))
    {
      if(wd.getValue().equals("opaque"))
      {
       return #02247C;
      }
      else if(wd.getValue().equals("turbid"))
      {
        return #5F8BFF;
      }
      else
      {
        return #DEE7FF;
      }
    }
    else
    {
      return 0;
    }
  }
  //This draws a visulization primarly
  //for WaterData objects
  public void drawWaterData()
  {
    rectMode(CENTER);
    fill(changeColor());
    stroke(0);
    if(parameter.equals("Water_Colour")) //drawing for water color
    {
      rect(width/2 - 500, height/2, 100, 600);
      fill(0);
      text("Water Colour", width/2 - 500, height/2 - 325);
      text(dataList.get(index).getDate(), width/2 - 500, height/2 + 325); 
    }
    else
    {
      rect(width/2 - 250, height/2, 100, 600); //drawing for water calrity
      fill(0);
      text("Water Clarity", width/2 - 250, height/2 - 325); 
      text(dataList.get(index).getDate(), width/2 - 250, height/2 + 325); 
    }
  }
  
  //set max value
  public void setMax(float value)
  {
    Tmax = value;
  }
  //get individual max
  public float getIndiMax()
  {
    return indvmax;
  }
  //This calculates it's value and returns it
  public float getMax()
  {
    println(dataList.get(0).toString() + " is Chem: " + (dataList.get(0) instanceof ChemData));
    ChemData cd = (ChemData)dataList.get(0);
    float max = cd.getValue();
    for(Data da : dataList)
    {
      println(da.toString() + " is Chem: " + (da instanceof ChemData));
      ChemData cdd = (ChemData)da;
      if(cdd.getValue() > max)
      {
        max = cdd.getValue();
      }
    }
    //println(parameter + ": " + max);
    return max;
  }
  //This is responsible for draw a bar
  //graph in a singular format (FORMATED FOR
  //BEFORE CURRENT AFTER)
  public void drawSingBar(int x, int y)
  {
    fill(0);
    textAlign(CENTER);
    textSize(20);
    text("==== Previous Record <- Current Record -> Future Record ====",  x + 500, y - 25);
    textSize(15);
    for(int i = index-1; i < index + 2; i++)
    {
      line(x, y, x + width/2 - 50, y);
      if(i < 0 || i > dataList.size() -1)
      {
        fill(0);
        textAlign(LEFT);
        text(parameter,  x, y + 25);
        textAlign(CORNER);
        fill(0);
        text("Date: N/A", x + 240, y + 25);
        fill(0);
        text("Value: N/A",x + 480, y + 25);
        text("Max:  N/A",x + width/2 - 100, y + 25);        
      }
      else
      {
        ChemData cd = (ChemData)dataList.get(i);
        float barTS = indvmax/ 920;
        float Bwidth = cd.getValue() / barTS;
        float Bheight = 15;
        fill(#A00000);
        rectMode(CORNER);
        rect(x,y - Bheight/2, Bwidth, Bheight);
        fill(0);
        textAlign(LEFT);
        text(parameter,  x, y + 25);
        textAlign(CORNER);
        fill(0);
        text("Date: " + cd.getDate(), x + 240, y + 25);
        fill(0);
        text("Value: " + nfc(cd.getValue(), 4) + cd.getUnit(),x + 480, y + 25);
        text("Max:  " + indvmax,x + width/2 - 100, y + 25); 
      }
      y += 45;
    }
  }
  //This is responsible for draw it's bar graph of
  //its entire dataset
  public void drawBar(int x, int y)
  {
    Lselect.ChangeVals(x + width/2 - 250, y + 20, "view more", #7AA4D8, 100, 20);
    //int y = 50;
    //int x = height/2 - 200;
    ChemData cd = (ChemData)dataList.get(index);
    
    //calculates the bar size
    float barTS = Tmax/ 920; //width/2 - 50;
    float Bwidth = cd.getValue() / barTS;
    float Bheight = 15;
    
    //draws everything
    line(x, y, x + width/2 - 50, y);
    fill(#A00000);
    rectMode(CORNER);
    rect(x,y - Bheight/2, Bwidth, Bheight);
    textAlign(CORNER);
    fill(0);
    text("Date: " + cd.getDate(), x + 240, y + 25);
    fill(0);
    text("Value: " + nfc(cd.getValue(), 4) + cd.getUnit(),x + 480, y + 25);
    text("Max:  " + Tmax,x + width/2 - 150, y + 25);
    Lselect.drawButton();
    //text("Max: 0.1", x + 1025, y);
    //y += 20;
  }
  //get for Units
  public String getUnit()
  {
    return dataList.get(index).getUnit();
  }
  //retuns the current looked at data value
  public float curSpecificVal()
  {
    ChemData cd = (ChemData)dataList.get(index);
    return cd.getValue();
  }
  //Checks if 'more info' is clicked
  public boolean isClicked(int x, int y)
  {
    if(Lselect.isClicked(x,y))
    {
      return true;
    }
    return false;
  }
  //this moves along to its next data
  public void nextData()
  {
    if(!(index == dataList.size() - 1))
    {
      index += 1;    
    }
  }
  //moves back to its previous data
  public void prevData()
  {
    if(!(index == 0))
    {
      index -= 1;
    }
  }
  //the size of the data set
  public int Count()
  {
    return dataList.size();
  }
  //the data of the current data 
  public String getDate()
  {
    return dataList.get(index).getDate();
  }
  //the dataSets parameter type
  public String getParameter()
  {
    return parameter;
  }
  
  public Data getData(int index)
  {
    return dataList.get(index);
  }
  
  public void printData()
  {
    for(Data da : dataList)
    {
      println(da.toString());
    }
  }
}
