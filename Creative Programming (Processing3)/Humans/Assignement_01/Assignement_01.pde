//Kelvin David 1345360
//- Assignement 01 - 'What it means to be human'
//This program extracts sentences from a file
//And uses the letters and words as paint for it's own
//spray tool.


PImage background1; //Holds the backgrounds
PImage background2;
PImage background3;
ArrayList<Sentence> sList = new ArrayList<Sentence>(); //Holds the sentence objects
int index = 0; //current sentence object index
int bk = 0; //current background

void setup()
{
  //This setups the window size
  size(1920, 1080);
  
  background(#676767);
  
  //This setups the human brain background
  background1 = loadImage("BrainP1.png");
  background2 = loadImage("BrainP2.png");
  background3 = loadImage("BrainP3.png");
  
  image(background1,0,0);
  
  //Declares the split arrays
  String[] lines = loadStrings("sourceText.txt");
  String[] sentences;
  
  //Assigns sentences to an object
  for(String line : lines)
  {
    sentences = splitTokens(line, ".?");
    for(String sentence : sentences)
    {
      sList.add(new Sentence(sentence));
    }
  }
}
void draw()
{
  //checks if end of sentence list
  if (index >= sList.size())
  {
    index = 0;
  }
  //current sentence object
  Sentence sCur = sList.get(index);
  //Checks if left mouse has been pressed
  if(mousePressed && (mouseButton == LEFT))
  {
    sCur.drawLetters(mouseX, mouseY); //uses letter spray tool
    index += 1; 
    delay(300);
  }
  //Checks if right mouse has been pressed
  else if(mousePressed && (mouseButton == RIGHT))
  {
    sCur.drawWord(mouseX, mouseY); //uses word spray tool
    index += 1;
    delay(300);
  }
  //Checks if middle mouse has been pressed
  else if(mousePressed && (mouseButton == CENTER))
  {
    sCur.drawSentence(mouseX, mouseY);//draws full sentence
    index += 1;
  }
  //Checks if up has been pressed
  if(keyPressed && (keyCode == UP))
  {
    saveFrame();//print screen
    bk += 1;
    if (bk == 1)//move to background 2
    {
      image(background2,0,0);
    }
    else if (bk == 2)//move to background 2
    {
      image(background3,0,0);
    }
  }
}
