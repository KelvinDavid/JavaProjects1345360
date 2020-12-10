//This class holds the animations and properites 
//for LOVE pleeb

class PleebLove extends Pleeb
{
  private int resizeTick = 1;
  private int resizew;
  private int resizeh;
  private int check;
  
  public PleebLove(int locaX, int locaY, PImage img)
  {
    super(locaX, locaY, img);
    resizeh = mainImg.height;
    resizew = mainImg.width;
    check = mainImg.height;
    
  }
  
  public void reset(){}
  
  public void drawPleeb()
  {
    imageMode(CENTER);
    image(mainImg, _location.x, _location.y, resizew, resizeh);
    if(resizeh == check + 10) //check if max size
    {
      resizeTick = 2;
    }
    if(resizeh == check) //check if mine size
    {
      resizeTick = 1;
    }
    if(resizeTick == 1)//increase if min
    {
      resizew += 1;
      resizeh += 1;    
    }
    if(resizeTick == 2) //decrase if max
    {
      resizew -= 1;
      resizeh -= 1;
    }
  }
}
