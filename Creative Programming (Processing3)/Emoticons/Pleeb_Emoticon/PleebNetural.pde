//This class holds the animations and properites 
//for NETURAL pleeb
class PleebNetural extends Pleeb
{
  private PVector _velocity;
  private int tick = 0;
  private int frameC = 1;
  private int flip = 1;
  ArrayList<PImage> frameRight = new ArrayList<PImage>();
  ArrayList<PImage> frameLeft = new ArrayList<PImage>();
  
  public PleebNetural(int locaX, int locaY, float velX, float velY, PImage img1, PImage img2, PImage img3
  , PImage img1F, PImage img2F, PImage img3F)
  {
    super(locaX, locaY, img1);
    _velocity = new PVector(velX, velY);
    frameRight.add(img1F); frameRight.add(img2F); frameRight.add(img3F); frameRight.add(img2F);
    frameLeft.add(img1); frameLeft.add(img2); frameLeft.add(img3); frameLeft.add(img2);
  }
  
  public void reset(){}
  
  public void drawPleeb()
  {
    //This block checks whether going left or right
    //------------------------------------------------------
    if(_velocity.x < 0)
    {
      flip = 2;
    }
    if(_velocity.x > 0)
    {
      flip = 1;
    }
    //This checks for a item to walk towards
    //------------------------------------------------------
    if(itemIs == true)
    {
      if (itemLocation.x > _location.x) //if going right
      {
        flip = 1;
        _location.x += 6;
      }
      if(itemLocation.x < _location.x) //if going left
      {
        flip = 2;
        _location.x -= 6;
      }
      imageMode(CENTER);
      if(flip == 1) //going left
      {
        image(frameRight.get(frameC), _location.x, _location.y);
      }
      if(flip == 2) //going right
      {
        image(frameLeft.get(frameC), _location.x, _location.y);
      }
      //This checks for ticks
      //-----------------------------------------------------
      if(tick % 10 == 0)
      {
        frameC += 1;
        if(frameC == 4)
        {
          frameC = 0;
        }            
      }
      tick += 1;
    }
    //-------------------------------------------------------
    else
    {
      _location.x += _velocity.x;
      if(_location.y > 615 - size/2)
      {
        _location.y -= 20;
      }
      if(_location.y < 615 - size/2)
      {
        _location.y += 20;
      }
      if((_location.x > width - size/2) || (_location.x < 0 + size/2))
      {
        _velocity.x = _velocity.x * -1;
      }
      if(0 + size/2 < _location.x)
      imageMode(CENTER);
      if(flip == 1)
      {
        image(frameRight.get(frameC), _location.x, _location.y);
      }
      if(flip == 2)
      {
        image(frameLeft.get(frameC), _location.x, _location.y);
      }
      if(tick % 20 == 0)
      {
        frameC += 1;
        if(frameC == 4)
        {
          frameC = 0;
        }      
      }
      tick += 1;
    }
  }
}
