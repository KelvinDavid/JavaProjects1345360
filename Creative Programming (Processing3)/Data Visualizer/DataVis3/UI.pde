//This is the main class that holds all the 
//lakes, DataSets and Data so it can presnet it on the
//acutal window. most of the methods are calls instead of
//accutal function

class UI
{
  ArrayList<Lake> lakeList;  //The assoication fo lakes
  String parameters[] = {"Chlorophyll_a", "Total_Nitrogen", "Turbidity", "Total_Phosphorus", "Water_Clarity", "Water_Colour"};
  
  private Lake selectedLake; //currently selected lake
  private String curPara;
  
  //THis initilzies the UI by reading a folder path
  public UI(String folderPath)
  {
    
    //extract all the lakes
    lakeList = new ArrayList<Lake>();
    String fileNames[] = getFileNames(folderPath);
      for(String file : fileNames)
      {
        if(file.equals("overall_lake_data.csv"))
        {
          String dataLine[] = loadStrings(file);
          for(int i = 1; i < dataLine.length; i++)
          {
            String dataex[] = split(dataLine[i], ',');
            if(dataex.length == 10)
            {
              lakeList.add(new Lake(dataex[0], Float.parseFloat(dataex[1]), 
              Float.parseFloat(dataex[2]), Float.parseFloat(dataex[3]), dataex[4],
              dataex[5], dataex[6], dataex[7], Float.parseFloat(dataex[8]),
              Float.parseFloat(dataex[9])));            
            }
            else
            {
              println("error on line: " + dataLine[i]);
            }
          }
        }
      }
      
      
      //Extract indivdual data
      ArrayList<Data> transfer = new ArrayList<Data>();
      for(String file : fileNames)
      {
        if(!file.equals("overall_lake_data.csv"))
        {
          String dataLine[] = loadStrings(file);
          for(int i = 1; i < dataLine.length; i++)
          {
            String dataex[] = split(dataLine[i], ',');
            if(dataex.length == 5)
            {
              try
              {
                transfer.add(new ChemData(dataex[0], dataex[1], dataex[2], 
                Float.parseFloat(dataex[3]), dataex[4]));
                //println(dataex[2] + " added");
              }
              catch(Exception ex)
              {
                transfer.add(new WaterData(dataex[0], dataex[1], dataex[2], 
                dataex[3], dataex[4]));                     
              }           
            }
            else
            {
              println("error on line: " + dataLine[i]);
            }
          }
        }
      }
      //for(Data da : transfer)
      //{
      //  if(da.getParameter().equals("Water_Clarity"))
      //  {
      //    println(da.toString() + " is ChemData: " + (da instanceof ChemData));
      //  }
      //}
      
      
      //Input all data points into lake objects
      for(Lake la : lakeList)
      {
        for(String para : parameters)
        {
          ArrayList<Data> curSet = new ArrayList<Data>();
          for(Data da : transfer)
          {
            if(la.getID().equals(da.getID()) && da.getParameter().equals(para))
            {
             curSet.add(da);
            }
          }
          la.SetupData(curSet, para);
        }
      }
      calcMax();
  }
  
  //Read all the file names instead a directory
  private String[] getFileNames(String dir)
  {
    File file = new File(dir);
    if(file.isDirectory())
    {
      String[] names = file.list();
      for(String n : names)
      {
        println(n);      
      }
      return names;
    }
    else
    {
      return null;
    }
  }
  //This changes the bar values
  public void changeBar(String di)
  {
    for(Lake la : lakeList)
    {
      la.changeData(di);
    }
  }
  
  //This draws the main bar screen
  public void drawBar()
  {
    textSize(20);
    fill(0); 
    text("Currently Showing: " + curPara, width/2 + 600, height/2 + 50);
    textSize(15);
    int count = 0;
    int x = 10;
    int y = 25;
    for(Lake la : lakeList)
    {
      la.drawBar(x, y);
      y += 45;
      count++;
      if(count == 20)
      {
        count = 0;
        x += width/2;
        y = 25;
      }      
    }
  }
  //This draws the selected screen
  public void drawSelected()
  {
    fill(0);
    textSize(60);
    textAlign(LEFT);
    text("=== "+selectedLake.getLakeName()+" ===", 10, 80);
    textSize(20);
    selectedLake.displayData();
    textSize(15);
    textAlign(CENTER);
    selectedLake.drawWebGraph();
    selectedLake.drawWaterData();
    selectedLake.drawSingBar();
  }
  //This prints the lake name
  public void printName()
  {
    for(Lake la : lakeList)
    {
      println(la.getLakeName());
    }    
  }
  //This changes the web value
  public void changeWebGValue(String di)
  {    
    //lakeList.get(1).printDataSet(parameter);
    for(Lake la : lakeList)
    {
      la.changeWebGValue(di);
    }
  }  
  //This changes the Data of the current looked parameter
  public void changeData(String parameter)
  {    
    curPara = parameter;
    //lakeList.get(1).printDataSet(parameter);
    for(Lake la : lakeList)
    {
      la.selectDS(parameter);
    }
  }
  //DEBUGING
  public void debug()
  {
    calcMax();
    for(Lake la: lakeList)
    {
      la.debug();
    }
    println("================================");
  }
  //This checks if a lake has been selected for
  //more info
  public boolean checkSelected(int x, int y)
  {
    for(Lake la : lakeList)
    {
      la.isSelected(x,y);
      if(la.getSelected() == true)
      {
        selectedLake = la;
        return true;
      }
    }
    return false;
  }
  
  private void calcMax()
  {
    float max = lakeList.get(0).getTL0406();
    for(Lake la : lakeList)
    {
      if(la.getTL0406() > max)
      {
        max = la.getTL0406();
      }
      else if(la.getTL10() > max)
      {
        max = la.getTL10();
      }
    }
    for(Lake la : lakeList)
    {
      la.setMax(max, "TLI");
    }
    for(String para : parameters)
    {
      if(!para.equals("Water_Clarity") && !para.equals("Water_Colour"))
      {
        ArrayList<Float> maxNums = new ArrayList<Float>();
        for(Lake la : lakeList)
        {
          maxNums.add(la.getMax(para));
        }
        max = maxNums.get(0);
        for(Float f : maxNums)
        {
          if(f > max)
          {
            max = f;
          }
        }
        println("I get Here with " + para);
        for(Lake la : lakeList)
        {
          la.setMax(max, para);
        }      
      }
    }
  }
}
