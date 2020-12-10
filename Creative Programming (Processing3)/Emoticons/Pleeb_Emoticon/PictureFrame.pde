//this is the picutre frame propties
//It's purpose is to trigger the ANGER emotion from
//the pleebian
class PictureFrame extends Item
{
  private PImage finalItem;
  private PVector shake;
  private PImage rectImg;
  private SoundFile sound;
  
  public PictureFrame(int x, int y, PImage img, PImage img2, PImage rImg, SoundFile snd)
  {
    super(x,y, img);
    finalItem = img2;
    rectImg = rImg;
    sound = snd;
    shake = new PVector(width/2, height/2);
  }
  
  public void drawItem()
  {
    //fall if not on ground
    fall();
    if(_location.y >= 614)
    {
      image(finalItem, _location.x, _location.y); //if hits ground break it
    }
    else
    {
      if(_location.y >= 610)
      {
        sound.play();
      }
      image(item,_location.x, _location.y); //if not it's still intact
    }
  }
  
  public void reaction(int x, int y)
  {
    imageMode(CENTER);
    image(rectImg, shake.x + random(-5, 5), shake.y + random(-5,5));  //shake background
  }
}
