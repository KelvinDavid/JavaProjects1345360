//This is the parent class to all the items
//spawned
abstract class Item
{
  protected final int GRAVITY = 10; //Constant gravity
  protected PVector _location; //location of item
  protected PImage item; //item image
  
  //constructor
  public Item(int x, int y, PImage img)
  {
    _location = new PVector(x, y);
    item = img;
    
  }
  //draws item into sketch
  public abstract void drawItem();
  //triggers reaction of the item
  public abstract void reaction(int x, int y);
  //returns X
  public float getX()
  {
    return _location.x;
  }
  //returns Y
  public float getY()
  {
    return _location.y;
  }
  //returns item's width
  public int getWidth()
  {
    return item.width;
  }
  //returns item's height
  public int getHeight()
  {
    return item.height;
  }
  
  //fall if not on ground
  public void fall()
  {
    if(_location.y < 615)
    {
      _location.y += GRAVITY;
    }  
  }
  
  //checks for collision with pleebian
  public boolean pleebColl(float x, float y, int sizew, int sizeh)
  {
    if((x + sizew/2 > _location.x - getWidth()/2) && (x - sizew/2 < _location.x + getWidth()/2) && 
    (y + sizeh/2 > _location.y - getHeight()/2) && (y - sizeh/2 < _location.y + getHeight()/2))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
