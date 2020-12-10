//This class holds the animations and properites 
//for SAD pleeb
class PleebSad extends Pleeb
{
  private PImage sadPleeb;
  private float cheight;
  
  public PleebSad(int locaX, int locaY, PImage img)
  {
    super(locaX, locaY, img);
    sadPleeb = img;
    cheight = sadPleeb.height;
  }
  
  public void reset()
  {
    cheight = sadPleeb.height;
  }
  
  public void drawPleeb()
  {
    if(!(cheight <= sadPleeb.height/2)) //flattens pleeb
    {
      cheight -= 0.5;
    }
    imageMode(CENTER);
    image(sadPleeb, _location.x, _location.y, sadPleeb.width, cheight);
  }
}
