//Kelvin David 1345360
//Data visulisation:
//The purpose of this program is to take a series
//of filed data and showcase them in a visual form
//To allow for easier understanding


//Main interface
UI ui;

//Buttons For the UI
Button next;
Button prev;
Button nitrogen;
Button chloro;
Button phosp;
Button turbidity;
Button debug;
Button TLI;
Button back;

int specificFlag;

void setup()
{
  size(1920, 1080);
  
  //This setups the sketch
  ui = new UI(sketchPath() + "/data/");
  ui.changeData("TLI");
  
  //Navigation controls
  prev = new Button(width/2 - 500, height - 100, "<< Prev", #7AA4D8, 200, 50);
  next = new Button(width/2 + 500, height - 100, "Next >>", #7AA4D8, 200, 50);
  back = new Button(width/2,  height - 100, ">> Back Home <<", #7AA4D8, 200, 50);
  
  //Changing between data controls
  nitrogen = new Button(width/2, height/2 + 100, "Total Nitrogen", #7AA4D8, 200, 50);
  chloro = new Button(width/2 + 250, height/2 + 100, "Chlorophyll", #7AA4D8, 200, 50);
  phosp = new Button(width/2 + 500, height/2 + 100, "Total Phosphorus", #7AA4D8, 200, 50);
  turbidity = new Button(width/2 + 600, height/2 + 150, "Turbidity", #7AA4D8, 200, 50);
  TLI = new Button(width/2 + 300, height/2 + 150, "Tropic Level", #7AA4D8, 200, 50);
  
  //debug = new Button(width/2,  height - 100, "Debug", #7AA4D8, 200, 50);
  
  //The flag to indicated the two different states
  specificFlag = 0;
  
  ui.printName();
}

void draw()
{
  background(255);
  
  //Everything under this if statement is drawn when not looking
  //at a specific lake
  if(specificFlag == 0)
  {
    next.drawButton();
    textSize(45);
    fill(0);
    text("=== LakeData Visulization ===", width/2, height - 100);
    prev.drawButton();
    //debug.drawButton();
    turbidity.ChangeVals(width/2 + 600, height/2 + 150, "Turbidity", #7AA4D8, 200, 50);
    turbidity.drawButton();
    nitrogen.ChangeVals(width/2 + 600, height/2 + 250, "Total Nitrogen", #7AA4D8, 200, 50);
    nitrogen.drawButton();
    chloro.ChangeVals(width/2 + 300, height/2 + 250, "Chlorophyll", #7AA4D8, 200, 50);
    chloro.drawButton();
    phosp.ChangeVals(width/2 + 300, height/2 + 50, "Total Phosphorus", #7AA4D8, 200, 50);
    phosp.drawButton();
    TLI.drawButton();
    ui.drawBar();
  }
  //These are the control drawn when looking at a specific lake
  else
  {
    next.drawButton();
    prev.drawButton();
    ui.drawSelected();
    nitrogen.ChangeVals(width/2, height/2 + 100, "Total Nitrogen", #7AA4D8, 200, 50);
    nitrogen.drawButton();
    chloro.ChangeVals(width/2 + 250, height/2 + 100, "Chlorophyll", #7AA4D8, 200, 50);
    chloro.drawButton();
    phosp.ChangeVals(width/2 + 500, height/2 + 100, "Total Phosphorus", #7AA4D8, 200, 50);
    phosp.drawButton();
    turbidity.ChangeVals(width/2 + 750, height/2 + 100, "Turbidity", #7AA4D8, 200, 50);
    turbidity.drawButton();
    back.drawButton();
  }
}

void mouseClicked()
{
  //These are all the button checks for the
  //controls in the first state
  if(specificFlag == 0)
  {
      if(ui.checkSelected(mouseX, mouseY))
    {
      specificFlag = 1;
      ui.changeData("Turbidity");
    }
    if(next.isClicked(mouseX, mouseY))
    {
      ui.changeBar("right");
    }
    if(prev.isClicked(mouseX, mouseY))
    {
      ui.changeBar("left");
    }
    if(turbidity.isClicked(mouseX, mouseY))
    {
      ui.changeData("Turbidity");
    }
    if(TLI.isClicked(mouseX, mouseY))
    {
      ui.changeData("TLI");
    }
    if(nitrogen.isClicked(mouseX, mouseY))
    {
      ui.changeData("Total_Nitrogen");
    }
    if(chloro.isClicked(mouseX, mouseY))
    {
      ui.changeData("Chlorophyll_a");
    }
    if(phosp.isClicked(mouseX, mouseY))
    {
      ui.changeData("Total_Phosphorus");
    }    
    //if(debug.isClicked(mouseX, mouseY))
    //{
    //  ui.debug();
    //}
  }
  
  //these are all the control checks for the
  //second state
  else
  {
    if(next.isClicked(mouseX, mouseY))
    {
      ui.changeWebGValue("right");
    }
    if(prev.isClicked(mouseX, mouseY))
    {
      ui.changeWebGValue("left");
    }
    if(back.isClicked(mouseX, mouseY))
    {
      specificFlag = 0;
      ui.changeData("TLI");
    }
    if(nitrogen.isClicked(mouseX, mouseY))
    {
      ui.changeData("Total_Nitrogen");
    }
    if(chloro.isClicked(mouseX, mouseY))
    {
      ui.changeData("Chlorophyll_a");
    }
    if(phosp.isClicked(mouseX, mouseY))
    {
      ui.changeData("Total_Phosphorus");
    }
    if(turbidity.isClicked(mouseX, mouseY))
    {
      ui.changeData("Turbidity");
    }  
  }
}
