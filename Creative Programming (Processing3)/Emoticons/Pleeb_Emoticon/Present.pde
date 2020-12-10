//this is the Present propties
//It's purpose is to trigger the JOY emotion from
//the pleebian
class Present extends Item
{
  private int conY;
  private PImage reactImg;
  private PImage reactionImg;
  private int trigger = 1;
  
  public Present(int x, int y, PImage img, PImage rImg, PImage rImg2)
  {
    super(x,y, img);
    reactImg = rImg;
    reactionImg = rImg2;
  }
  
  public void drawItem()
  {
    fall();
    if(trigger == 1)
    {
      image(item,_location.x, _location.y); //if trigger open present
    }
    else
    {
      image(reactionImg,_location.x, _location.y); //if not closed present
    }
  }
  
  public void reaction(int x, int y)
  {
     if(trigger == 1)
     {
       conY = 0 - reactImg.height; //reset confetti
       trigger = 0;
     }
      conY += 12;
     image(reactImg, width/2, conY); //draw confetti
     //confettii reaches bottom
     if(conY >= width + reactImg.height)
     {
       trigger = 1; //reloop
     }
     else
     {
       trigger = 0; //continue
     }
  }
}
