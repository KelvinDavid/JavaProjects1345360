//This class holds the animations and properites 
//for JOY pleeb
class PleebJoy extends Pleeb
{
  private PVector _velocity;
  private PVector _gravity;
  private PImage joyImg;
  private SoundFile sound;
  
 public PleebJoy(int locaX, int locaY, float velX, float velY, PImage img, SoundFile snd)
  {
    super(locaX, locaY, img);
    _velocity = new PVector(velX, velY);
    _gravity = new PVector(0, 2);
    joyImg = img;
    sound = snd;
  }
  
  public void reset(){}
  
  public void drawPleeb()
  {
    _location.add(_velocity);
    _velocity.add(_gravity);
    
    //Moves pleeb in arc motion
    if((_location.x > width) || (_location.x < 0))
    {
      _velocity.x = _velocity.x * -1;
    }
    //Bounces pleeb
    if(_location.y > 650)
    {
      sound.stop();
      sound.play();
      _velocity.y = _velocity.y * -0.95;
      _location.y = 650;
    }
    imageMode(CENTER);
    image(joyImg,_location.x, _location.y);
  }
}
