//This class holds the animations and properites 
//for FEAR pleeb
class PleebFear extends Pleeb
{
  private PVector _shake;
  private PImage fearImg;
  
  public PleebFear(int locaX, int locaY, PImage img)
  {
    super(locaX, locaY, img);
    fearImg = img;
  }
  
  public void reset(){}
  
  public void drawPleeb()
  { 
    if(_location.y < 615 - size/2) //Checks if not on floor
    {
         _location.y += 20;
    }
    _shake = new PVector(_location.x, _location.y);
    _shake.x = _shake.x += random(0,10);
    _shake.y = _shake.y += random(0,10 );
    imageMode(CENTER);
    image(fearImg, _shake.x, _shake.y); //shake image
  }
}
