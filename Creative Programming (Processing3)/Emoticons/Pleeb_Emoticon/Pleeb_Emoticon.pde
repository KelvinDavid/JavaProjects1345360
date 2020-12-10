//Kelvin David 1345360
//- Pleebian Emoticons -
//The purpose of this program is to showcase the six main emotions
//JOY, FEAR, SADNESS, SURPRISED, ANGER, LOVE
//The way that this program does this is through interaction with the
//user and the pleebian character, the user spawns in objects that the
//pleebian may interact with and depending on the item the pleebian shows
//one of the six emotions.

import processing.sound.*;

ArrayList<SoundFile> music = new ArrayList<SoundFile>(); //Holds all background music
ArrayList<Pleeb> pleebStates = new ArrayList<Pleeb>(); //holds all the emoticons

Pleeb current = null; //looks at current emoticon
Pleeb previous = null; //looks at previous emoticon
Item currItem = null; //looks at current item

int wantedState = 0; //looks which state is wanted
int trigger = 0; //looks if item has been triggered
PImage curBack; //holds current background
PImage sizeRef; //holds for size reference of emoticon (to collision)

SoundFile currMusic; //holds current background music playing
SoundFile effect; //used for playing sound effects


//Under setup it initilizes all soundfiles and pleebian states ready for use
void setup()
{
  //size of window
  size(1280, 720);
  //adding states to the pleebStates list
  pleebStates.add(new PleebNetural(width/2, height/2, 2.5, 0, loadImage("neturalPleeb1.png"), loadImage("neturalPleeb2.png"), 
  loadImage("neturalPleeb3.png"), loadImage("neturalPleeb1Flip.png"), loadImage("neturalPleeb2Flip.png"), 
  loadImage("neturalPleeb3Flip.png")));
  pleebStates.add(new PleebJoy(0,0, 1.5, 2.1, loadImage("joyPleeb.png"), new SoundFile(this ,"Jump.mp3")));
  pleebStates.add(new PleebFear(0,0, loadImage("fearPleeb.png")));
  pleebStates.add(new PleebSad(0,0, loadImage("sadPleeb.png")));
  pleebStates.add(new PleebSurprised(0,0, loadImage("surprisedPleeb.png")));
  pleebStates.add(new PleebLove(0,0, loadImage("lovePleeb.png")));
  pleebStates.add(new PleebAnger(0,0, loadImage("angerPleeb.png")));
  //adding the SoundFiles to the music list
  music.add(new SoundFile(this, "Netural.mp3"));
  music.add(new SoundFile(this, "Joy.mp3"));
  music.add(new SoundFile(this, "Fear.mp3"));
  music.add(new SoundFile(this, "Sad.mp3"));
  music.add(new SoundFile(this, "Surprised.mp3"));
  music.add(new SoundFile(this, "Love.mp3"));
  music.add(new SoundFile(this, "Angry.mp3"));
  //sets the starting pleebState to netural
  current = pleebStates.get(0);
  //loads background
  curBack = loadImage("PleebBedRoom.png");
  //loads refrence
  sizeRef = loadImage("neturalPleeb1.png");
  //Current music played on loop
  currMusic = music.get(0);
  currMusic.loop();
}

//draws needed in ticks
void draw()
{
  background(curBack);
  
  //Checks if an item exists
  if(currItem != null)
  {
    //checks if pleeb has triggered item
    if(trigger == 1)
    {
      //triggers reaction
      currItem.reaction(int(current.getX()), int(current.getY()));
    }
    //draws item
    currItem.drawItem();
    //checks if item hasn't been triggered
    if(trigger == 0)
    {
      //moves pleeb towards item and checks for pleeb collision with item (for JOY)
      if(currItem.pleebColl(current.getX(), current.getY(),sizeRef.width, sizeRef.height) &&
      wantedState == 1)
      {
        delay(250);
        previous = current;
        current = pleebStates.get(1);
        trigger = 1;
        currMusic.stop();
        curBack = loadImage("PleebBedRoomJoy.png");
        currMusic = music.get(1);
        effect = new SoundFile(this, "Present.mp3");
        effect.play();
        currMusic.loop();
        current.resetVector(previous.getX(), previous.getY());
      }
      //moves pleeb towards item and checks for pleeb collision with item (for FEAR)
      if(currItem.pleebColl(current.getX(), current.getY(), sizeRef.width, sizeRef.height) &&
      wantedState == 2)
      {
        delay(250);
        previous = current;
        current = pleebStates.get(2);
        trigger = 1;
        currMusic.stop();
        currMusic = music.get(2);
        effect = new SoundFile(this, "Devil.mp3");
        effect.play();
        currMusic.loop();
        curBack = loadImage("PleebBedRoomFear.png");
        current.resetVector(previous.getX(), previous.getY());
      }
      //moves pleeb towards item and checks for pleeb collision with item (for SAD)
      if(currItem.pleebColl(current.getX(), current.getY(), sizeRef.width, sizeRef.height) &&
      wantedState == 3)
      {
        delay(250);
        previous = current;
        current = pleebStates.get(3);
        trigger = 1;
        currMusic.stop();
        currMusic = music.get(3);
        effect = new SoundFile(this, "Rain.mp3");
        effect.loop();
        currMusic.loop();
        curBack = loadImage("PleebBedRoomSad.png");
        current.resetVector(previous.getX(), previous.getY());
      }
      //moves pleeb towards item and checks for pleeb collision with item (for SURPRISED)
      if(currItem.pleebColl(current.getX(), current.getY(), sizeRef.width, sizeRef.height) &&
      wantedState == 4)
      {
        delay(500);
        previous = current;
        current = pleebStates.get(4);
        trigger = 1;
        currMusic.stop();
        currMusic = music.get(4);
        effect = new SoundFile(this, "Alert.mp3");
        effect.play();
        currMusic.loop();
        curBack = loadImage("PleebBedRoomSurprise.png");
        current.resetVector(previous.getX(), previous.getY());
      }
      //moves pleeb towards item and checks for pleeb collision with item (for LOVE)
      if(currItem.pleebColl(current.getX(), current.getY(), sizeRef.width, sizeRef.height) &&
      wantedState == 5)
      {
        delay(250);
        previous = current;
        current = pleebStates.get(5);
        trigger = 1;
        currMusic.stop();
        currMusic = music.get(5);
        effect = new SoundFile(this, "Heart.mp3");
        effect.loop();
        currMusic.loop();
        curBack = loadImage("PleebBedRoomLove.png");
        current.resetVector(previous.getX(), previous.getY());
      }
      //moves pleeb towards item and checks for pleeb collision with item (for ANGER)
      if(currItem.pleebColl(current.getX(), current.getY(), sizeRef.width, sizeRef.height) &&
      wantedState == 6)
      {
        delay(250);
        previous = current;
        current = pleebStates.get(6);
        trigger = 1;
        currMusic.stop();
        currMusic = music.get(6);
        effect = new SoundFile(this, "Trigger.mp3");
        effect.loop(1, 0.2);
        currMusic.loop();
        curBack = loadImage("PleebBedRoomAnger.png");
        current.resetVector(previous.getX(), previous.getY());
      }
    }
  }
  //draw pleeb
  current.drawPleeb();
}

void keyPressed()
{
  //checks if a item already exists (allows only 1 item in pleeb's room)
  if(currItem != null && trigger == 1)
  {
    //Reset to netural
    if(key == 'r')
    {
      currItem = null;
      //reset variables
      previous = current;
      current.reset();
      current = pleebStates.get(0);
      current.setItemIs(false, 0, 0); //item doesnt exist
      wantedState = 0;
      trigger = 0;
      currMusic.stop();
      effect.stop();
      currMusic = music.get(0);
      effect = new SoundFile(this, "Clear.mp3");
      currMusic.loop();
      effect.play();
      curBack = loadImage("PleebBedRoom.png");
      current.resetVector(previous.getX(), previous.getY());
    }
  }
  else
  {
    //Spawns a present
    if(key == '1')
    {
      println("PresentSpawned");
      wantedState = 1;
      currItem = new Present(mouseX, mouseY, loadImage("Present.png"), loadImage("confetii.png"), loadImage("openPresent.png"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    //Spawns devil spawn
    if(key == '2')
    {
      println("DevilSpawnSpawned");
      wantedState = 2;
      currItem = new DevilSpawn(mouseX, mouseY, loadImage("DevilSpawn.png"), 
      loadImage("fearR1.png"), loadImage("fearR2.png"), loadImage("fearR3.png"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    //Spawns tissue
    if(key == '3')
    {
      println("TissueSpawned");
      wantedState = 3;
      currItem = new Tissue(mouseX, mouseY, loadImage("Tissue.png"), loadImage("rain1.png"), loadImage("rain2.png"), loadImage("rain3.png"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    //Spawns jack in the box
    if(key == '4')
    {
      println("JackInTheBoxSpawned");
      wantedState = 4;
      currItem = new JackInTheBox(mouseX, mouseY, loadImage("jackInTheBox.png"), loadImage("surprisedR.png"), loadImage("alert.png"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    //spawns love letter
    if(key == '5')
    {
      println("LoveLetterSpawned");
      wantedState = 5;
      currItem = new LoveLetter(mouseX, mouseY, loadImage("loveLetter.png"), loadImage("loveR.png"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    //spawns picture frame
    if(key == '6')
    {
      println("PictureSpawned");
      wantedState = 6;
      currItem = new PictureFrame(mouseX, mouseY, loadImage("picWhole.png"), loadImage("picBroken.png"), loadImage("PleebBedRoomAnger.png"),
      new SoundFile(this, "Glass.mp3"));
      current.setItemIs(true, currItem.getX(), currItem.getY());
    }
    effect = new SoundFile(this, "Spawn.mp3");
    effect.play();
  }
}
