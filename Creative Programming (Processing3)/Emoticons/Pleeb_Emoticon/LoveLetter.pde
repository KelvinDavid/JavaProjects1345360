//This holds the love letter propeties
//The purpose of this item is to trigger the LOVE emotion of
//the pleebian
class LoveLetter extends Item
{
  private PImage reactImg;
  private PImage reactBack;
  private int resizeTick = 1;
  private int resizew;
  private int resizeh;
  private int check;
  
  public LoveLetter(int x, int y, PImage img, PImage rImg)
  {
    super(x,y, img);
    reactImg = rImg;
    reactBack = loadImage("PleebBedRoomLove.png");
    resizeh = reactBack.height;
    resizew = reactBack.width;
    check = reactBack.height;
  }
  
  public void drawItem()
  {
    //fall if not on ground
    fall();
    image(item,_location.x, _location.y);
  }
  
  public void reaction(int x, int y)
  {
    imageMode(CENTER);
    image(reactBack, width/2, height/2, resizew, resizeh);
    if(resizeh == check + 20)
    {
      resizeTick = 2;
    }
    if(resizeh == check)
    {
      resizeTick = 1;
    }
    if(resizeTick == 1)
    {
      resizew += 2;
      resizeh += 2;    
    }
    if(resizeTick == 2)
    {
      resizew -= 2;
      resizeh -= 2;
    }
    imageMode(CENTER);
    image(reactImg, x, y - reactImg.height/2 + 2); //draws thought bubble on top of pleebian
  }
}
