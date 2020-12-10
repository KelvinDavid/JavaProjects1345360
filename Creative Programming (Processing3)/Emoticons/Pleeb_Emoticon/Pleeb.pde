//This is the parent class of the all the Pleebian emotions
//it holds all the functions common between the pleebian emoticons

abstract class Pleeb
{
  protected PVector _location; //current location
  protected boolean itemIs = false; //if pleeb sees item
  protected PVector itemLocation; //the pleeb's knowledge of the item's location
  protected int size = 50; //pleeb hit box
  protected PImage mainImg; //main sprite of the pleeb
  
  public Pleeb(int locaX, int locaY, PImage img)
  {
    _location = new PVector(locaX, locaY);
    mainImg = img;
  }
  
  public abstract void drawPleeb();
  
  //resets pleebs position to current position
  public void resetVector(float x, float y)
  { 
    _location = new PVector(x,y);
  }
  //resets loops and animations
  public abstract void reset();
  //pleebs current x position
  public float getX()
  {
    return _location.x;
  }
  //pleebs current y position
  public float getY()
  {
    return _location.y;
  }
  //Checking if pleeb knows where item is
  public void setItemIs(boolean value, float x, float y)
  {
    itemLocation = new PVector(x,y);
    itemIs = value;
  }
  //Checking if pleeb knows if item exists
  public boolean getItem()
  {
    return itemIs;
  }
}
