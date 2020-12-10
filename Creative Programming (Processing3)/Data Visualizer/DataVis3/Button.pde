//This is is the class repsonsible for holding
//the properties of the button control

class Button
{
  private PVector _center;
  private color _buttonColor;
  private int _bHeight;
  private int _bWidth;
  private PFont _font;
  private String _caption;
  
  //intilizes the button
  public Button(int x, int y, String caption, color buttonColor,
  int bWidth, int bHeight)
  {
    _buttonColor = buttonColor;
    _center = new PVector(x, y);
    _font = createFont("New Times Roman", 14);
    _bHeight = bHeight;
    _bWidth = bWidth;
    _caption = caption;
  }
  
  //this allows for total overall of the button's propeties
  public void ChangeVals(int x, int y, String caption, color buttonColor,
  int bWidth, int bHeight)
  {
    _buttonColor = buttonColor;
    _center = new PVector(x, y);
    _font = createFont("New Times Roman", 14);
    _bHeight = bHeight;
    _bWidth = bWidth;
    _caption = caption;  
  }
  //this draws the button
  public void drawButton()
  {
    rectMode(CENTER);
    if(isClicked(mouseX, mouseY))
    {
      fill(#FF8400);
    }
    else
    {
      fill(_buttonColor);
    }
    stroke(0);
    rect(_center.x, _center.y, _bWidth, _bHeight);
    textFont(_font);
    fill(#FFFFFF);
    textAlign(CENTER,CENTER);
    text(_caption, _center.x, _center.y - 2);  
  }
  //this checks if user has clicked the button
  public boolean isClicked(int x, int y)
  {
    if((x > _center.x - _bWidth/2) && (x < _center.x + _bWidth/2) && 
    (y > _center.y - _bHeight/2) && (y < _center.y + _bHeight/2)){
      return true;
    }
    return false;  
  }
  
}
