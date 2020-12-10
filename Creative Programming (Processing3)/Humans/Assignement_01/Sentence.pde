//This class holds full sentences from
//sourceText.txt and outputs it back to the window
//in different ways either letters, words, seteneces

class Sentence
{
  private String[] fontList = {
  "font1", "font2", "font3", "font4", "font5", "font6", "font7", "font8",
  "font9", "font10", "font11", "font12", "font13", "font14", "font15"}; //holds the fonts used
  private String[] _words; //holds individual words
  private char[] _letters; //holds individual letters
  private color _senColor; //holds the sentece color
  private int index = int(random(0,fontList.length)); //hold the current font
  private PFont _senFont  = loadFont(fontList[index] + ".vlw");; //hold the current font again
  
  //This initializes the setence
  public Sentence(String sentence)
  {
    _letters = new char[sentence.length()]; //sets the size to the amount of letters

     for(int i=0; i < sentence.length();i++) //divides letters and stores in list
     {
       _letters[i] = sentence.charAt(i);      
     }
    
    _senColor = color(random(255),random(255),random(255)); //chooses random color
    
    _words = splitTokens(sentence, " "); //Splits the words into list
  }
  
  //This draws the setence's words like a spray tool
  public void drawWord(int x, int y)
  {
    for(String word : _words)
    {
      fill(_senColor); //random properties
      textFont(_senFont);
      textSize(random(10, 30));
      rectMode(CENTER);
      rotate(HALF_PI/0.5);
      text(word,  x + random(200), y + random(200), 200, 200);
    }
  }
  //This draws the sentence wrapped structured
  public void drawSentence(int x, int y)
  {
    String _sentence = "";
    for(String word : _words) //forms sentence using words list
    {
      _sentence += word;
      _sentence += " ";
    }
    _sentence += ".";
    fill(_senColor); //random properties
    textFont(_senFont);
    textSize(5);
    rectMode(CENTER);
    text(_sentence, x,y, 25, 60);
  }
  //This draws the sentence's letters like a spray tool
  public void drawLetters(int x, int y)
  {
    for(char letter : _letters)
    {
      fill(_senColor); //random properties
      textFont(_senFont);
      textSize(random(10, 30));
      rotate(HALF_PI/0.5);
      rectMode(CENTER);
      text(str(letter), x + random(200), y + random(200), 200, 200);
    }
  }
}
