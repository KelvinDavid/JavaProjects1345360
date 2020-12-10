//this is the tissue propties
//It's purpose is to trigger the SAD emotion from
//the pleebian
class Tissue extends Item
{
  private ArrayList<PImage> rain = new ArrayList<PImage>();
  private int curReact = 0;
  private int tick = 0;
  
  public Tissue(int x, int y, PImage img, PImage imgR, PImage imgR2, PImage imgR3)
  {
    super(x,y, img);
    rain.add(imgR);
    rain.add(imgR2);
    rain.add(imgR3);
    rain.add(imgR2);
  }
  
  public void drawItem()
  {
    fall();
    image(item,_location.x, _location.y);
  }
  
  public void reaction(int x, int y)
  {
    imageMode(CENTER);
    image(rain.get(curReact), x , y - rain.get(curReact).height/2 + 5); //draw rain
    
    //change rain image every 20 ticks
    if(tick % 20 == 0)
    {
      curReact += 1;
      if(curReact == 4)
      {
        curReact = 0;
      }
    }
    tick += 1;  
  }
}
