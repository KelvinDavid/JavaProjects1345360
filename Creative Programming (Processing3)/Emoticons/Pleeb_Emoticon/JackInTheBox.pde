//This is the jack in the box properties
//it's purpose is to trigger the SURPRISED emotion from
//the pleebian
class JackInTheBox extends Item
{
  private PImage reactImg1;
  private PImage reactImg2;
  private int trigger = 0;
  
  public JackInTheBox(int x, int y, PImage img, PImage rImg, PImage reImg)
  {
    super(x,y, img);
    reactImg1 = rImg;
    reactImg2 = reImg;
  }
  
  public void drawItem()
  {
    //fall if not on ground
    fall();
    if(trigger == 0)
    {
      imageMode(CENTER);
      image(item,_location.x, _location.y); //closed jack in box
    }
    if(trigger == 1)
    {
      imageMode(CENTER);
      image(reactImg1, _location.x, _location.y); //open jack in box
    }
  }
  
  public void reaction(int x, int y)
  {
    trigger = 1;
    imageMode(CENTER);
    image(reactImg2, x, y - reactImg2.height); //draws '!' on top of pleebian
  }
}
