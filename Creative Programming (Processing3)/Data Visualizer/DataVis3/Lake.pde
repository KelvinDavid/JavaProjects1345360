//This is the Lake object that holds all the main
//lake information whilist holding a associaiton of
//DataSets of it's own Data and responsible fore holding
//the Data for individula lakes

class Lake
{
  //all the required information
  private String id;
  private PVector location;
  private float altitude;
  private String name;
  private String siteType;
  private String grade0406;
  private String grade10;
  private float TLI0406;
  private float TLI10;

  private ArrayList<DataSet> lakeData;
  private ArrayList<PVector> maxPoint;
  private DataSet current;
  private float max;
  private boolean isSelected;
  //this method initizles the lake object
  public Lake(String _id, float x, float y, float alti, String _name, String sType,
  String g0406, String g10, float tli0406, float tli10)
  {
    isSelected = false;
    lakeData = new ArrayList<DataSet>();
    id = _id;
    location = new PVector(x,y);
    altitude = alti;
    name = _name;
    siteType = sType;
    grade0406 = g0406;
    grade10 = g10;
    TLI0406 = tli0406;
    TLI10 = tli10;
    //This creates a dataSet for Tropic level so it
    //can be switched between like the other datasets
    ArrayList<Float> dataPoint;
    ArrayList<Data> TLI = new ArrayList<Data>();
    TLI.add(new ChemData(id, "2004-2006", "TLI", TLI0406, "lvl"));
    TLI.add(new ChemData(id, "2010", "TLI", tli10, "lvl"));
    lakeData.add(new DataSet("TLI",TLI));
    //This holds the max point in terms of the
    //spider web graph for individual data
    maxPoint = new ArrayList<PVector>();
    maxPoint.add(new PVector(width/2 + 400 + 250, height/2 - 250));
    maxPoint.add(new PVector(width/2 + 400, (height/2 - 250) + 250));
    maxPoint.add(new PVector( width/2 + 400, (height/2 - 250) - 250));
    maxPoint.add(new PVector(width/2 + 400  - 250, height/2 - 250));
  }
  //This displays the data in text
  public void displayData()
  {
    int y = 300;
    int x = 15;
    text("====== DATA =======", x, y);
    y += 50;
    text("ID: "+id, x, y);
    y += 50;
    text("Location: "+"("+location.x+","+location.y+")", x, y);
    y += 50;
    text("Altitude: "+ altitude, x, y);
    y += 50;
    text("Site Type: "+siteType, x, y);
    y += 50;
    text("Grade 2004-2006: "+grade0406, x, y);
    y += 50;
    text("Grade 2010: "+ grade10,x, y);
    y += 50;
    text("===================", x, y);
  }
  //This sets the amx value of all it's data sets
  public void setMax(float value, String parameter)
  {
    for(DataSet ds : lakeData)
    {
      if(ds.getParameter().equals(parameter))
      {
        ds.setMax(value);
      }
    }
  }
  //get and set methods
  public float getTL0406()
  {
    return TLI0406;
  }
  public float getTL10()
  {
    return TLI10;
  }
  
  public String getLakeName()
  {
    return name;
  }
  
  public String getID()
  {
    return id;
  }  
  //This creates new DataSets from parameters and Data Lists
  public void SetupData(ArrayList<Data> transfer, String parameter)
  {
    //println(parameter + transfer.size());
    lakeData.add(new DataSet(parameter, transfer));
  }
  //This prints a dataSet (DEBUGING purposees)
  public void printDataSet(String parameter)
  {
    for(DataSet ds : lakeData)
    {
      if(ds.getParameter().equals(parameter))
      {
        ds.printData();
      }
    }
  }
  //This draws the bars for individual analysis
  public void drawSingBar()
  {
    int x = width/2 - 100;
    int y =  height/2 + 200;
    current.drawSingBar(x, y); 
  }
  //This draws its bar for the main page
  //(Difference it draws one bar not three of itself)
  public void drawBar(int x, int y)
  {
    current.drawBar(x, y);
    fill(0);
    textAlign(LEFT);
    text(name,  x, y + 25);
  }
  //this makes this lake selected for specific analysis
  public void selectDS(String parameter)
  {
    for(DataSet ds : lakeData)
    {
      if(ds.getParameter().equals(parameter))
      {
        current = ds;
      }
    }  
  }
  //This gets the max of each of it;s parameters
  public float getMax(String parameter)
  {
    for(DataSet ds : lakeData)
    {
      if(ds.getParameter().equals(parameter))
      {
        return ds.getMax();
      }
    }
    return 0;
  }
  
  
  //This changes the value of the webgraph
  public void changeWebGValue(String direction)
  {
    if(direction.equals("right"))
    {
      for(DataSet ds : lakeData)
      {
        ds.nextData();
      }
    }
    else
    {
      for(DataSet ds : lakeData)
      {
        ds.prevData();
      }
    }
  }
  //this ddraws the water data graph
  public void drawWaterData()
  {
    for(DataSet ds : lakeData)
    {
      if(ds.getParameter().equals("Water_Colour") ||
      ds.getParameter().equals("Water_Clarity"))
      {
        ds.drawWaterData();
      }
    }
  }
  //this draws thew web graph for the individual 
  //anylsis
  public void drawWebGraph()
  {
    
    //Draw the skeleton of the graph
    fill(0);
    textAlign(CENTER);
    textSize(20);
    text("==== Current Data Comparisions ====",  width/2 + 150, height/2 - 450);
    textSize(15);
    rectMode(CENTER);
    stroke(0);
    //rect(width/2 + 400, height/2 - 250, 500, 500);
    line(width/2 + 400, (height/2 - 250) + 250,width/2 + 400, (height/2 - 250) - 250);
    line((width/2 + 400) + 250, height/2 - 250,(width/2 + 400)  - 250, height/2 - 250);
    text("Chlorophyll", width/2 + 400 + 300, height/2 - 255);
    text("Total Nitrogen", width/2 + 400, (height/2 - 250) + 270);
    text("Turbidity", width/2 + 400, (height/2 - 250) - 270);
    text("Total Phosphorus", width/2 + 400  - 310, height/2 - 255);
    ArrayList<PVector> webPoints = new ArrayList<PVector>();
    
    //Indiviudally update the 4 corners of the graph
    for(DataSet ds : lakeData)
    {
      if(!ds.getParameter().equals("TLI") && !ds.getParameter().equals("Water_Clarity") &&
      !ds.getParameter().equals("Water_Colour"))
      {
        if(ds.getParameter().equals("Chlorophyll_a"))
        {
          float pos = ds.getIndiMax()/ 250;
          float x = (width/2 + 400) + ds.curSpecificVal() / pos;
          float y = maxPoint.get(0).y;
          rectMode(CENTER);
          println("I made it");
          fill(#EA0000);
          text(nfc(ds.curSpecificVal(), 4) + ds.getUnit() + " -- " + ds.getDate(),width/2 + 400 + 300, height/2 - 230);
          fill(0);
          rect(x,y, 5, 5);
          webPoints.add(new PVector(x,y));
        }
        if(ds.getParameter().equals("Total_Nitrogen"))
        {
          float pos = ds.getIndiMax()/ 250;
          float y = (height/2 - 250) + ds.curSpecificVal() / pos;
          float x = maxPoint.get(1).x;
          rectMode(CENTER);
          println("I made it");
          fill(#EA0000);
          text(ds.curSpecificVal() + ds.getUnit() + " -- " + ds.getDate(),  width/2 + 400, (height/2 - 250) + 290);
          fill(0);
          rect(x,y, 5, 5);
          webPoints.add(new PVector(x,y));
        }
        if(ds.getParameter().equals("Turbidity"))
        {
          float pos = ds.getIndiMax()/ 250;
          float y = (height/2 - 250) - ds.curSpecificVal() / pos;
          float x = maxPoint.get(2).x;
          rectMode(CENTER);
          println("I made it");
          fill(#EA0000);
          text(ds.curSpecificVal() + ds.getUnit() + " -- " + ds.getDate(),width/2 + 400, (height/2 - 250) - 250);
          fill(0);
          rect(x,y, 5, 5);
          webPoints.add(new PVector(x,y));
        }
        if(ds.getParameter().equals("Total_Phosphorus"))
        {
          float pos = ds.getIndiMax()/ 250;
          float x = (width/2 + 400) - ds.curSpecificVal() / pos;
          float y = maxPoint.get(3).y;
          rectMode(CENTER);
          println("I made it");
          fill(#EA0000);
          text(nfc(ds.curSpecificVal(), 4) + ds.getUnit() + " -- " + ds.getDate(),width/2 + 400 - 300, height/2 - 230);
          fill(0);
          rect(x,y, 5, 5);
          webPoints.add(new PVector(x,y));
        }
      }
    }
    //This draws the web using the webPoints
    stroke(#EA0000);
    println(webPoints.size());
    line(webPoints.get(0).x, webPoints.get(0).y, webPoints.get(1).x, webPoints.get(1).y);        
    line(webPoints.get(1).x, webPoints.get(1).y, webPoints.get(3).x, webPoints.get(3).y);        
    line(webPoints.get(3).x, webPoints.get(3).y, webPoints.get(2).x, webPoints.get(2).y);        
    line(webPoints.get(2).x, webPoints.get(2).y, webPoints.get(0).x, webPoints.get(0).y);
  }
  //get if selected
  public boolean getSelected()
  {
    return isSelected;
  }
  //Toggles selection depending on x and y
  public void isSelected(int x, int y)
  {
    if(current.isClicked(x, y))
    {
      isSelected = true;
    }
    else
    {
      isSelected = false;
    }
  }
  //deselects the lake
  public void deselect()
  {
    isSelected = false;
  }
  //This cahange data for current data
  public void changeData(String direction)
  {
    if(direction.equals("right"))
    {
      current.nextData();
    }
    else
    {
      current.prevData();
    }
  }
  //Debug meothds
  public void debug()
  {  
    //println(current.getParameter() + " - " + current.getMax());
    for(DataSet ds : lakeData)
    {
      println(ds.getParameter() + " Exists " + "Count: " + ds.Count());
    }
  }
}
