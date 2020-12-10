//This class holds the animations and properites 
//for ANGRY pleeb
class PleebAnger extends Pleeb
{
  private int resizew;
  private int resizeh;
  private int check;
  
  public PleebAnger(int locaX, int locaY, PImage img)
  {
    super(locaX, locaY, img);
    resizeh = mainImg.height;
    resizew = mainImg.width;
    check = mainImg.height;
  }
  
  public void reset()
  {
      resizew = mainImg.width;
      resizeh = mainImg.height; 
  }
  public void drawPleeb()
  {
    if(resizeh != check + 30) //grow pleebian until max size
    {
      imageMode(CENTER);
      image(mainImg, _location.x, _location.y, resizew, resizeh);
      resizew += 1;
      resizeh += 1;    
    }
    else //stop growing and shake
    {
      imageMode(CENTER);
      image(mainImg, _location.x + random(-5, 5), _location.y  + random(-5,5), resizew, resizeh);
    }
  }
}
