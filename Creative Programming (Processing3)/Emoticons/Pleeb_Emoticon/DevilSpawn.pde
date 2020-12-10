//This item is what triggers the
//fear emotion
class DevilSpawn extends Item
{
  private ArrayList<PImage> reaction = new ArrayList<PImage>();
  private int tick = 0;
  private int curReact = 0;
  private int reacPos;
  
  public DevilSpawn(int x, int y, PImage img, PImage react1, PImage react2, PImage react3)
  {
    super(x,y, img);
    reaction.add(react1);
    reaction.add(react2);
    reaction.add(react3);
    reaction.add(react2);
  }
  
  public void drawItem()
  {
    if(_location.y < 615)
    {
      _location.y += GRAVITY;
    }
    image(item,_location.x, _location.y);
  }
  
  public void reaction(int x, int y)
  {
    if(curReact == 0)
    {
      reacPos = x - reaction.get(curReact).width;
    }
    if(curReact == 1 || curReact == 3)
    {
      reacPos = x;
    }
    if(curReact == 2)
    {
      reacPos = x + reaction.get(curReact).width;
    }
    imageMode(CENTER);
    image(reaction.get(curReact), reacPos , y - reaction.get(curReact).height/2);
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
