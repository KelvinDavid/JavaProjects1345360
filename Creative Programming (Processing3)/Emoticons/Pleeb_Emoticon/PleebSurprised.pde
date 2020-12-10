//This class holds the animations and properites 
//for SURRPRISED pleeb
class PleebSurprised extends Pleeb
{
  private int fall = 0;;
  private int grounded;
  private int reset = 1;
  private PImage surprisedImg;
  
 public PleebSurprised(int locaX, int locaY, PImage img)
  {
    super(locaX, locaY, img);
    surprisedImg = img;
    grounded = 615 - size/2;
  }
  
  public void reset(){}
  
  public void drawPleeb()
  {
    if(_location.y > grounded) // if pleebian is grounded
    {
      _location.y = grounded;
    } 
    if(fall == 1)
    {
      if(_location.y == grounded) //if grounded reset jump
      {
        fall = 0;
        imageMode(CENTER);
        image(surprisedImg, _location.x, _location.y);
        return;
      }
      if(_location.y > 615 - size/2) //jumping
      {
        _location.y -= 20;
      }
      _location.y += 5;
      imageMode(CENTER);
      image(surprisedImg, _location.x, _location.y);
    }
    else
    {
      if(_location.y <= grounded - 100) //trigger fall if too high
      {
        fall = 1;
      }
      _location.y -= 8;
      imageMode(CENTER);
      image(surprisedImg, _location.x, _location.y);
    }
  }
}
