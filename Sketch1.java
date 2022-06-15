import java.util.ArrayList;
import java.util.Iterator;
 
import processing.core.PApplet;
import processing.core.PImage;
 
public class Sketch1 extends PApplet {
  PImage background;
  PImage background1;
  PImage background2;
  PImage background3;
  PImage playerSprite;
  PImage bossSprite;
  PImage bossSword1;
  PImage bossDefault;
  PImage bossAttack;
  PImage bossReady;
  PImage bossDefault1;
  PImage bossSlash1;
  PImage bossSword2;
  PImage bossSlash2;
  PImage bossDefault2;
  PImage bossDefault3;
  PImage bossShield3;
  PImage bossSlash3;

  PImage menu;
 
  double bossX = 500;
  double bossY = 500;
  double bossXSpd;
  double bossYSpd;
  boolean bossAlive = true;
  int bossHealth = 1000;
  int phase = 1;
  int attack = 0;
  int prevattack = 0;
  int attackTimer = 0;
  boolean bossInvulnerable = false;
  int phaseTimer = 0;
  boolean startRotate;
  double tempAngle;
  double startX = 0; 
  double startY = 0;

  boolean startGame = false;
  boolean credits = false;
  boolean help = false;
  boolean win = false;
  boolean lose = false;
 
  double playerX;
  double playerY;
  boolean playerAlive = true;
  int playerSpd = 5;
  int playerMaxHealth = 100;
  int playerHealth = playerMaxHealth;
  int combatTimer = 0;
  int speedCooldown = 300; 
  int speedTimer = 90;

	boolean upPressed;
  boolean downPressed;
  boolean leftPressed;
  boolean rightPressed;
  int bulletRed = color(255, 50, 50);
 
 
 
  ArrayList <playerBullet> playerBullets = new ArrayList <playerBullet>();
  ArrayList <normalBullet> normalBullets = new ArrayList <normalBullet>();
  ArrayList <rectBullet> rectBullets = new ArrayList <rectBullet>();
  ArrayList <bomb> bombs = new ArrayList <bomb>();
  ArrayList <beam> beams = new ArrayList <beam>();
 
  PImage [] player = new PImage[8];
  PImage [] menuScreen = new PImage[11];
 
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 800);
  }
 
  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    frameRate(30);
    background1 = loadImage("dark_background.png");
    background2 = loadImage("default_background.png");
    background3 = loadImage("light_background.png");
    
    
    bossDefault1 = loadImage("black_default.png");
    bossDefault2 = loadImage("silver_default.png");
    bossDefault3 = loadImage("white_default_cropped.png");
    bossSword1 = loadImage("black_sword.png");
    bossSword2 = loadImage("silver_sword.png");
    bossShield3 = loadImage("white_shield_cropped.png");
    bossSlash1 = loadImage("black_slash.png");
    bossSlash2 = loadImage("silver_slash.png");
    bossSlash3 = loadImage("white_slash_cropped.png");

    menuScreen[0] = loadImage("start_menu.png");
    menuScreen[1] = loadImage("start_highlight.png");
    menuScreen[2] = loadImage("help_highlight.png");
    menuScreen[3] = loadImage("credits_highlight.png");
    menuScreen[4] = loadImage("credits.png");
    menuScreen[5] = loadImage("help.png");
    menuScreen[6] = loadImage("credits_coloured.png");
    menuScreen[7] = loadImage("help_coloured.png");
    menuScreen[8] = loadImage("win_screen.png");
    menuScreen[9] = loadImage("lose_screen.png");
    menuScreen[10] = loadImage("lose_highlighted.png");
 

    player[0] = loadImage("Gardevoir_Up.png");
    player[1] = loadImage("Gardevoir_Down.png");
    player[2] = loadImage("Gardevoir_Right.png");
    player[3] = loadImage("Gardevoir_Left.png");
    player[4] = loadImage("Gardevoir_Up_Left.png");
    player[5] = loadImage("Gardevoir_Up_Right.png");
    player[6] = loadImage("Gardevoir_Down_Left.png");
    player[7] = loadImage("Gardevoir_Down_Right.png");
    playerX = 800;
    playerY = 800;
    playerSprite = player[1];
    background = background1;

    bossAttack = bossSlash1;
    bossReady = bossSword1;
    bossDefault = bossDefault1;
    bossSprite = bossDefault1;
  }
 
  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
 
    // menu screen
    if (startGame == false) {

      if (win) {
        delay(2000);
        background(0);
        image(menuScreen[8], 0, 0);
        
      }
      if (lose) {
        image(menuScreen[9], 0, 0);
        if (mouseX >= 116 && mouseX <= 683 && mouseY >= 437 && mouseY <= 507) {
          image(menuScreen[10], 0, 0);
          if (mousePressed) {
            lose = false;
            startGame = true;
          }
        }
      }
      // credits screen
      if (credits) {
        image(menuScreen[4], 0 ,0 );
        if (mouseX >= 26 && mouseX <= 133 && mouseY >= 735 && mouseY <= 776) {
          image(menuScreen[6], 0, 0);
          if (mousePressed) {
            credits ^= true;
          }

        }
      }

      // help screen
      if (help) {
        image(menuScreen[5], 0, 0);
        if (mouseX >= 26 && mouseX <= 133 && mouseY >= 735 && mouseY <= 776) {
          image(menuScreen[7], 0, 0);
          if (mousePressed) {
            help ^= true;
          }
        }
      }
      // start screen
      if (credits == false && help == false && lose == false && win == false) {
        if (mouseX >= 305 && mouseX <= 493 && mouseY >= 207 && mouseY <= 276) {
          image(menuScreen[1], 0, 0);
          if(mousePressed) {
            startGame = true;
          }
        }
  
        else if(mouseX >= 205 && mouseX <= 593 && mouseY >= 322 & mouseY <= 389) {
          image(menuScreen[2], 0, 0);
          if (mousePressed) {
            help ^= true;
          }
        }
  
  
        else if(mouseX >= 286 && mouseX <= 514 && mouseY >= 434 && mouseY <= 502) {
          image(menuScreen[3], 0, 0);
          if(mousePressed) {
            credits ^= true;
          }
        }
  
        else {
          image(menuScreen[0], 0 ,0);
        }
      }



    



    }
    if (startGame) {

      // combat timer for health regeneration
      if(combatTimer > 0){
      combatTimer--;
      }
      if(combatTimer == 0 && playerHealth < playerMaxHealth && frameCount % 5 == 0){
        playerHealth++;
      }



      // speed ability
      playerSpd = 5;

      if (speedCooldown < 300) {
        speedCooldown++;
      }
      
      if (speedTimer < 90) {
        playerSpd = 10;
        speedTimer ++;
      }



      // player movement and change sprite based on direction
      if(upPressed && playerY > 400){
        playerY -= playerSpd;
        playerSprite = player[0];
      }
      if(downPressed && playerY < 1586){
        playerY += playerSpd;
        playerSprite = player[1];
      }
      if(leftPressed && playerX > 412){
        playerX -= playerSpd;
        playerSprite = player[3];
      }
      if(rightPressed && playerX < 1588){
        playerX += playerSpd;
        playerSprite = player[2];
      }
      if (upPressed && leftPressed) {
        playerSprite = player[4];
      }
      else if (upPressed && rightPressed) {
        playerSprite = player[5];
      }
      else if (downPressed && leftPressed) {
        playerSprite = player[6];
      }
      else if (downPressed && rightPressed) {
        playerSprite = player[7];
        }

      // translate screen to centre player
      translate(-(float)playerX+400, -(float)playerY+400);
    
      image(background, 0, 0);

      image(playerSprite, (float)playerX-12, (float)playerY-14);
      fill(50);
      rect((float)playerX-13, (float)playerY + 26, 24, 2);
      fill(0, 255, 0);
      if(playerHealth < playerMaxHealth/2){
      fill(255, 165, 50);
      }
      rect((float)playerX-13, (float)playerY + 26, (float)(playerHealth/(playerMaxHealth/24)), 2);
    
      /**
       * 
       * LOAD BULLETS
       * 
       */
      
      // beam update and collision detection
      Iterator <beam> beamItr = beams.iterator();
      while(beamItr.hasNext()) {
        beam i = beamItr.next();
        i.update();
        if(i.time > 60){
          if(circleRect(i.X, i.Y, i.size, playerX-12, playerY-14, 23, 28)&& i.hasHit == false){
            playerHealth -= 30;
            combatTimer = 90;
            i.hasHit = true;
          }
        }
        if(i.time > 75){
          beamItr.remove();
        }
      }

      // shoot player bullet every 5 frames in diretion of cursor
      if (frameCount%5==0 && mousePressed) {
        playerBullet b = new playerBullet(playerX, playerY, mouseX+playerX-400, mouseY+playerY-400);
        playerBullets.add(b);
      }

      // update player bullets and collision detection
      Iterator <playerBullet> playerItr = playerBullets.iterator();
      while(playerItr.hasNext()) {
        playerBullet i = playerItr.next();
        i.update();
        boolean collide = circleRect(i.X, i.Y, 8f, bossX-42, bossY-50, 80,  80);
        
        // check collision
        if (collide && !bossInvulnerable) {
          bossHealth -= 2;
          playerItr.remove();
        }
        // check if bullet is out of boundary or bullet expires
        else if (i.X > 1600 || i.X < 400 || i.Y > 1600 || i.Y < 400 || i.time == 20){
          playerItr.remove();
        }
      }
    
      // rectangle update and collision detection
      Iterator <rectBullet> rectItr = rectBullets.iterator();
      while(rectItr.hasNext()){
        rectBullet i = rectItr.next();
        i.update();
        if(rectRect(i.X, i.Y, (float) i.width, (float) i.height, playerX-12, playerY-14, 23, 28)){
          playerHealth -= i.damage ;
          rectItr.remove();
          combatTimer = 90;
        }
        else if (i.X > 1600 || i.X < 400 || i.Y > 1600 || i.Y < 400 || i.time == i.duration || i.width < 0 || i.height < 0){
        rectItr.remove();
        }
      }

      // normal bullet update and collision detection
      Iterator <normalBullet> normalItr = normalBullets.iterator();
      while(normalItr.hasNext()) {
        normalBullet i = normalItr.next();
        i.update();
        if (circleRect((int) i.X, (int) i.Y, i.size, playerX-12, playerY-14, 23,  28)) {
          playerHealth -= i.damage;
          normalItr.remove();
          combatTimer = 90;
        }
        else if (i.X > 1600 || i.X < 400 || i.Y > 1600 || i.Y < 400 || (i.time == i.duration && !i.isBounce)){
          normalItr.remove();
        }
      }
    
      // bomb bullet update and collision detection
      Iterator <bomb> bombItr = bombs.iterator();
      while(bombItr.hasNext()) {
        bomb i = bombItr.next();
        i.update();

        // when fuse runs out, create 8 normal bullets in 360 degrees
        if (i.fuse == 0){
          for(int j = 0; j < 8; j++){
            normalBullet b = new normalBullet((int) i.X, (int) i.Y, j * 45, 10, 20, 5, 10, false, false);
            normalBullets.add(b);
          }
          bombItr.remove();
        }
      }
    
    

      /**
       * 
       * BOSS BEHAVIOR
       * 
       */
 
      bossX += bossXSpd;
      bossY += bossYSpd;

      // Change boss phases
      if (bossHealth == 600 && phase == 1) {
        phase = 2;
        bossInvulnerable = true;
        phaseTimer = 60;
      }
      else if (bossHealth == 300 && phase == 2) {
        phase = 3;
        bossInvulnerable = true;
        phaseTimer = 60;
      }

      // Change boss sprites for the phase
      if (phase == 2 && phaseTimer == 0 && bossHealth == 600) {
        bossDefault = bossDefault2;
        bossAttack = bossSlash2;
        bossReady = bossSword2;
        background = background2;
        bossSprite = bossDefault;
      }
      if (phase == 3 && phaseTimer == 0 && bossHealth == 300) {
        background = background3;
        bossDefault = bossDefault3;
        bossAttack = bossSlash3;
        bossReady = bossShield3;
        bossSprite = bossAttack;
      }

      // Select an attack
      if (attack == 0 && phaseTimer == 0) {

        if (phase == 1) {
          attack = (int)random(1, 4);
          while(attack == prevattack) {
            attack = (int)random(1, 4);
          }
        }

        if(phase == 2){
          attack = (int)random(4, 7);
          while(attack == prevattack){
            attack = (int)random(4, 7);
          }
        }

        if(phase == 3){
          attack = (int)random(7, 10);
          while(attack == prevattack){
            attack = (int)random(7, 10);
          }
        }

        bossInvulnerable = false;
        attackTimer = 3000;
      }
    




      /**
      * 
      * ATTACK LIST
      *
      */

      // boss follows player and shoots bullets and drops bombs
      if (attack == 1){
        attackTimer-=20;
        if(attackTimer < 2600){
        bossMove(bossX, bossY, playerX, playerY, 3);
          if(frameCount % 10 == 0){
            normalBullet b = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY), 15, 10, 300, 10,  false, false);
            normalBullets.add(b);
          }
          if(frameCount % 30 == 0){
            bomb b = new bomb(bossX, bossY, bossX, bossY, 60);
            bombs.add(b);
            normalBullet c = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY)+20, 15, 10, 300, 10,  false, false);
            normalBullet d = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY)-20, 15, 10, 300, 10,  false, false);
            normalBullets.add(c);
            normalBullets.add(d);
          }
          if(frameCount % 30 >= 0 && frameCount % 30 < 5){
            bossSprite = bossAttack;
          }
          else{
            bossSprite = bossReady;
          }
        }
      }

      // centres boss, shoots bullets in rings (around the boss)
      if(attack == 2){
        attackTimer-=10;
        if(!(bossX < 1040 && bossX > 960 && bossY < 1040 && bossY > 960)){
          bossMove(bossX, bossY, 1000, 1000, 40);
        }
        else {
          bossX = 1000;
          bossY = 1000;
        }
        if(attackTimer < 2400) {
          bossSprite = bossAttack;
          if(frameCount % 30 >= 0 && frameCount % 30 < 5){
            bossSprite = bossReady;
          }
          if (frameCount % 30 == 0) {
            for (int i=0; i < 16; i++) {
              normalBullet b = new normalBullet(bossX, bossY, i*22.5 - 7.5, 20, 5, 300, 10, false, false);
              normalBullets.add(b);
            }
          }
          else if (frameCount%20==0){
            for(int i=0; i < 16; i++){
              normalBullet b = new normalBullet(bossX, bossY, i * 22.5, 20, 5, 300, 10, false, false);
              normalBullets.add(b);
            }
          }
          else if(frameCount%10==0){
            for(int i=0; i < 16; i++){
              normalBullet b = new normalBullet(bossX, bossY, i*22.5 + 7.5, 20, 5, 300, 10, false, false);
              normalBullets.add(b);
            }
          }
        }
        else {
          bossSprite = bossReady;
        }
      }

      // outer rotation attack (with tail)
      if(attack == 3){
        if(attackTimer == 3000) {
          startRotate = false;
          bossSprite = bossReady;
          for(int i=0; i<45; i++){
            beam b = new beam(1000 + cos((float)(i))*500, 1000 + sin((float)(i))*500, 100);
            beams.add(b);
          }
        }
        attackTimer-=10;

        // picks the closest edge of arena and moves towards the centre of that edge
        if(startRotate == false){
          if(bossX < 800){
            bossMove(bossX, bossY, 500, 1000, 20);
            if(bossXSpd == 0 && bossYSpd == 0){
              startRotate = true;
              tempAngle = getAngle(1000, 1000, bossX, bossY);
            }
          }
          else if(bossX > 1200){
            bossMove(bossX, bossY, 1500, 1000, 20);
            if(bossXSpd == 0 && bossYSpd == 0){
              startRotate = true;
              tempAngle = getAngle(1000, 1000, bossX, bossY);
            }
          }
          else if(bossY > 1000){
            bossMove(bossX, bossY, 1000, 1500, 20);
            if(bossXSpd == 0 && bossYSpd == 0){
              startRotate = true;
              tempAngle = getAngle(1000, 1000, bossX, bossY);
            }
          }
          else if(bossY < 1000){
            bossMove(bossX, bossY, 1000, 500, 20);
            if(bossXSpd == 0 && bossYSpd == 0){
              startRotate = true;
              tempAngle = getAngle(1000, 1000, bossX, bossY);
            }
          }
          else if(bossY == 1000 && bossX == 1000){
            bossMove(bossX, bossY, 1000, 1500, 20);
            if(bossXSpd == 0 && bossYSpd == 0){
              startRotate = true;
              tempAngle = getAngle(1000, 1000, bossX, bossY);
            }
          }
        }
        // once it reaches closest edge, begin shooting and spinnning
        else {
          bossX = 1000 + cos((float)(tempAngle*Math.PI/180)) * 500;
          bossY = 1000 + sin((float)(tempAngle*Math.PI/180)) * 500;
          tempAngle += 1;
          if(attackTimer < 2400){
            if(frameCount % 20 == 0){
              rectBullet a = new rectBullet(bossX-40, bossY, 0, 10, 80, 5, 10, -10, 0, 20);
              rectBullet b = new rectBullet(bossX-40, bossY, 0, -10, 80, 5, 10, -10, 0, 20);
              rectBullet c = new rectBullet(bossX, bossY-40, 10, 0, 5, 80, 10, 0, -10, 20);
              rectBullet d = new rectBullet(bossX, bossY-40, -10, 0, 5, 80, 10, 0, -10, 20);
              rectBullets.add(a);
              rectBullets.add(b);
              rectBullets.add(c);
              rectBullets.add(d);
            }
            if(frameCount % 10 == 0){
              normalBullet e = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY), 20, 10, 300, 10, false, false);
              normalBullets.add(e);
            }
            if(frameCount%30 == 0){
              normalBullet f = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY)-10, 20, 10, 300, 10, false, false);
              normalBullets.add(f);
              normalBullet g = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY)+10, 20, 10, 300, 10, false, false);
              normalBullets.add(g);
            }
            normalBullet h = new normalBullet(bossX, bossY, getAngle(bossX, bossY, 1000, 1000) + 180, 30, 10, 30, 50, false, false); 
            normalBullets.add(h);
            bossSprite = bossReady;
            if(frameCount%30 >=0 && frameCount%30 <5){
              bossSprite = bossAttack;
            }
          }
        }
      }



      // phase 2 ATTACKS

      // charge attack
      if(attack == 4) {
        attackTimer -= 5;
        if(bossHealth != 300) {
        bossInvulnerable = false;
        }
        if (attackTimer > 2800) {
          bossInvulnerable = true;
          bossSprite = bossDefault;
        }
        else{
        bossSprite = bossReady;
        }
        if(attackTimer % 500 == 210){
          normalBullet b = new normalBullet(bossX, bossY, getAngle(bossX, bossY, playerX, playerY), 30, 20, 300, 30, true, false);
          normalBullets.add(b);
        }
        if(bossX < 400 || bossX > 1600 || bossY < 400 || bossY > 1600){
          bossXSpd = 0;
          bossYSpd = 0;
        }
        if(bossX > 1600){
          bossX = 1600;
        }
        if(bossY < 400){
          bossY = 400;
        }
        if(bossY > 1600){
          bossY = 1600;
        }
        if(attackTimer % 500 == 200){
          bossMove(bossX, bossY, playerX, playerY, 15);
        }

        if(attackTimer < 2700){
          if(frameCount % 15 >= 0 && frameCount %15 < 5){
            bossSprite = bossAttack;
          }
          else{
            bossSprite = bossReady;
          }
          if(frameCount % 15 == 0){
            float offset = random(0, 20);
            for(int i = 0; i < 16; i++){
              normalBullet b = new normalBullet(bossX, bossY, i*22.5 + offset, 10, 10, 30, 10,  false, false);
              normalBullets.add(b);
            }
          }
        }
      }

      // rectangle gap dodging 
      if(attack == 5){
        if(attackTimer == 3000){
          for(int i = 0; i < 20; i++){
            beam b = new beam(i*60+400, 450, 100);
            beams.add(b);
          }
        }
        if(attackTimer > 2700){
          bossMove(bossX, bossY, 1000, 450, 30);
        }
        attackTimer -= 10;
        if(bossXSpd == 0 && bossYSpd == 0){
          bossX = 1000;
          bossY = 450;
        }
        if(attackTimer > 2700){
          bossSprite = bossReady;
        }
        if(attackTimer > 2600 && attackTimer < 2700){
          bossSprite = bossAttack;
          bossXSpd = 15;
          bossYSpd = 0;
        }
        if(bossX < 400 || bossX > 1600){
          bossXSpd *= -1;
        }
        if(attackTimer < 2600){
          bossSprite = bossDefault;
          if(frameCount % 30 == 0){
            int gap1 = (int)random(0, 8);
            int gap2 = (int)random(8, 15);
            for(int i = 0; i < 15; i++) {
              if(i != gap1 && i != gap2){
                rectBullet b = new rectBullet(i*80 + 400, 400, 0, 5, 80, 5, 300, 0, 0, 20);
                rectBullets.add(b);
              }
            }
          }
        }
      }

      // inner rotation + beams
      if(attack == 6){
        if(attackTimer == 3000){
          bossInvulnerable = true;
          beam b = new beam(1000, 1000, 150);
          beams.add(b);
          bossMove(bossX, bossY, 1000, 1000, 40);
        }
        if(attackTimer == 2700){
          bossSprite = bossReady;
          if(bossHealth != 300){
            bossInvulnerable = false;
          }
          for(int i = 0; i < 45; i++){
            beam b = new beam(1000 + cos((float)(i))*150, 1000 + sin((float)(i))*150, 80);
            beams.add(b);
          }
        }
        attackTimer -= 10;
        if(attackTimer > 2300){
          if(bossX < 1040 && bossX > 960 && bossY < 1040 && bossY > 960){
            bossXSpd = 0;
            bossYSpd = 0;
            bossX = 1000;
            bossY = 1000;
          }
        }
        if(attackTimer == 2300){
          startX = playerX;
          startY = playerY;
          tempAngle = getAngle(1000, 1000, startX, startY);
        }
        if(attackTimer < 2300){
          bossSprite = bossReady;
          if(frameCount % 20 >= 0 && frameCount % 20 <5){
            bossSprite = bossAttack;
          }
          if(attackTimer >= 2200){
            bossMove(bossX, bossY, startX, startY, 15);
          }
          else{
            bossX = 1000 + cos((float)(tempAngle*Math.PI/180)) * 150;
            bossY = 1000 + sin((float)(tempAngle*Math.PI/180)) * 150;
            tempAngle+=8;
            if(frameCount % 10 == 0){
              for(int i=0; i<16; i++){
                normalBullet b = new normalBullet(bossX, bossY, i*22.5 + 7.5, 20, 5, 300, 20, false, false);
                normalBullets.add(b);
              }

              for(int i=0; i<10; i++){
                beam b = new beam(random(400, 1600), random(400, 1600), 50);
                beams.add(b);
              }
            }
          }
        }
      }

      // teleporting attack
      if (attack == 7){
        attackTimer-=10;
        if(attackTimer > 2350){
          bossX = -500;
          bossY = -500;
        }
        else {
          if(attackTimer%600 > 450){
            bossSprite = bossReady;
          }
          if(attackTimer % 600 == 450){
            startX = playerX + random(-200, 200);
            startY = playerY + random(-200, 200);
            while(startX<=400 || startX >= 1600){
              startX = playerX + random(-200, 200);
            }
            while(startY <= 400 || startY >= 1600){
              startY = playerY + random(-200, 200);
            }
            beam b = new beam(startX, startY, 50);
            beam c = new beam(startX, startY, 100);
            beams.add(b);
            beams.add(c);
          }
          if(attackTimer % 600 > 150 && attackTimer % 600 < 450){
            bossX = -500;
            bossY = -500;
          }
          if(attackTimer % 600 < 150){
            bossX = startX;
            bossY = startY;
            if(attackTimer % 600 == 140){
              for(int i=0; i<16; i++){
                normalBullet b = new normalBullet(bossX, bossY, i*22.5, 15, 10, 30, 30, false, true);
                normalBullets.add(b);
              }
            }
            bossSprite = bossAttack;
            if(frameCount % 2 == 0){
              for(int i=0; i<3; i++){
                normalBullet b = new normalBullet(bossX, bossY, random(0, 360), 10, 8, 60, 20, true, false);
                normalBullets.add(b);
              }
            }
          }
        }
      }

      // cyclone attack
      if(attack == 8){
        if(attackTimer == 3000){
          bossInvulnerable = true;
          bossX = -500;
          bossY = -500;
          beam b = new beam(1000, 1000, 300);
          beams.add(b);
          beam c = new beam(1000, 1000, 50);
          beams.add(c);
        }
        if(attackTimer == 2700){
          bossSprite = bossAttack;
          bossX = 1000;
          bossY = 1000;
          tempAngle = 0;
          for(int i=0; i<8; i++){
            normalBullet b = new normalBullet(bossX, bossY, tempAngle + i * 45, 30, 10, 300, 20, true, false);
            normalBullets.add(b);
          }
        }
        attackTimer-=10;
        if(attackTimer < 2400){
          bossInvulnerable = true;
          if(frameCount % 60 >= 0 && frameCount % 60 < 30){
            bossInvulnerable = false;
          }
            tempAngle += 0.8;
            if(frameCount % 30 == 0){
              for(int i=0; i<5; i++){
                beam b = new beam(random(400, 1000), random(400, 1000), 50);
                beam c = new beam(random(1000, 1600), random(400, 1000), 50);
                beam d = new beam(random(400, 1000), random(1000, 1600), 50);
                beam e = new beam(random(1000, 1600), random(1000, 1600), 50);
                beams.add(b);
                beams.add(c);
                beams.add(d);
                beams.add(e);
              }
              bomb b = new bomb(bossX, bossY, playerX, playerY, 60);
              bombs.add(b);
            }
            for(int i=0; i<8; i++){
              normalBullet b = new normalBullet(bossX, bossY, tempAngle + i * 45, 20, 20, 300, 20, true, false);
              normalBullets.add(b);
            }
        }
      }

      if (attack == 9){
        if(attackTimer == 3000){
          bossSprite = bossDefault;
          for(int i = 0; i < 21; i++){
            beam b = new beam(i*60+400, 450, 100);
            beam c = new beam(i*60+400, 1550, 100);
            beams.add(b);
            beams.add(c);
          }
          if(random(0, 2) < 1){
            startY = 1;
          }
          else{
            startY = -1;
          }
        }
        if(attackTimer == 1500){
          startY *= -1;
        }
        attackTimer -= 5;
        if(attackTimer > 2850 || (attackTimer > 1350 && attackTimer <= 1500)){
          bossMove(bossX, bossY, 1000, 1000 + 550 * startY, 40);
        }
        if(bossXSpd == 0 && bossYSpd == 0){
          bossX = 1000;
          bossY = 1000 + 550 * startY;
        }
        if(frameCount % 90 == 0){
          for(int i = 0; i < 21; i++){
            beam b = new beam(i*60+400, 450, 100);
            beam c = new beam(i*60+400, 1550, 100);
            beams.add(b);
            beams.add(c);
          }
        }
        if(attackTimer < 2850){
          if((attackTimer > 300 && attackTimer < 1500) || attackTimer > 1800){
            if(frameCount % 10 == 0){
              for(int i = 0; i < 15; i++) {
                  rectBullet b = new rectBullet(i*80 + 400, 1000 - startY * 600, 0, 5 * startY, 80, 5, 130, 0, 0, 20);
                  rectBullets.add(b);
              }
            }
          }
          if(attackTimer > 1500 || attackTimer < 1350){
            if(frameCount % 2 == 0){
              normalBullet b = new normalBullet(bossX, bossY, tempAngle, 15, 5, 150, 15, true, false);
              normalBullets.add(b);
              tempAngle += startX;
              if(tempAngle == 20 || tempAngle == 160 || tempAngle == 340 || tempAngle == 200){
                startX *= -1;
              }
            }
            if(frameCount % 20 == 0){
              normalBullet b = new normalBullet(bossX, bossY, 180 + 90 * startY, 20, 10, 60, 30, false, true);
              normalBullets.add(b);
            }
          }
        }
        if(attackTimer == 2850 || attackTimer == 1350){
          startX = 10 * startY;
          tempAngle = 180 + 90 * startY; 
          bossXSpd = 15;
          bossYSpd = 0;
          bossSprite = bossDefault;
        }
        if((attackTimer > 1500 && attackTimer < 2850) || attackTimer < 1350){
          bossSprite = bossAttack;
        }
        if(bossX < 400 || bossX > 1600){
          bossXSpd *= -1;
        }
        
      }


    // Reset variables after attack ends
    if(attackTimer == 0){
      bossXSpd = 0;
      bossYSpd = 0;
      prevattack = attack;
      attack = 0;
      bossSprite = bossDefault; 
    }

    // Move boss to middle before phase change
      if(phaseTimer > 0){
        if(attack==0){
        phaseTimer--;
          bossMove(bossX, bossY, 1000, 1000, 20);
          if(bossXSpd == 0 && bossYSpd == 0){
            bossX = 1000;
            bossY = 1000;
          }
        }
      }
    


        /**
        * 
        * DRAW MAJOR SPRITES AND GRAPHICS 
        *
        */
      if (bossAlive) {
        image(bossSprite, (int)bossX-42, (int)bossY-50);
      }
    
      strokeWeight(1);
      translate((int)playerX-400, (int)playerY-400);

      // draw boss health bar
      if (bossHealth >= 1) {
        stroke(0);
        fill(50);
        rect(290, 50, 500, 10);
        fill(255, 0, 0);
        if(bossInvulnerable == true){
          fill(0, 0, 200);
        }
        rect(290, 50, bossHealth/2, 10);
        fill(255);
        text(bossHealth, 520, 60);
        } 
    
        // player health bar
        if (playerHealth >= 1) {
          fill (50);
          rect(20, 700, 100, 10);
          fill(0, 255, 0);
          stroke(0);
          rect(20, 700, playerHealth, 10);
          fill(100, 50, 200);
          text(playerHealth, 60, 710);

          // speed cooldown bar
          fill(50);
          rect(20, 720, 100, 5);
          fill(200);
          rect(20, 720, speedCooldown/3, 5);
        }
        
    
      if (bossHealth <= 0) {
        bossAlive = false;
        win = true;
        startGame = false;
      }

      if (playerHealth <= 0) {
        lose = true;
        startGame = false;
        credits = false;
        bossHealth = 1000;
        playerHealth = 1000;
        phase = 1;
        attackTimer = 0;
        attack = 0;
        bossDefault = bossDefault1;
        bossAttack = bossSlash1;
        bossReady = bossSword1;
        bossXSpd = 0;
        bossYSpd = 0;
        bossX = 500;
        bossY = 500;
        delay(1000);
        normalBullets.clear();
        rectBullets.clear();
        bombs.clear();
        beams.clear();
        playerBullets.clear();
      }
  }
  
  }
 








  // Set booleans when wasd keys are pressed
  public void keyPressed() {
    if (key == 'w') {
      upPressed = true;
    }
    else if (key == 's') {
      downPressed = true;
    }
    else if (key == 'a') {
      leftPressed = true;
    }
    else if (key == 'd') {
      rightPressed = true;
    }
    if (speedCooldown == 300 && key == ' ') {
      speedTimer = 0;
      speedCooldown = 0;
      }
  }
 
  // Set booleans when wasd keys are released
  public void keyReleased() {
    if (key == 'w') {
      upPressed = false;
    }
    else if (key == 's') {
      downPressed = false;
    }
    else if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
  }
 
  // calculates the angle between 2 points in degrees
  public double getAngle(double x1, double y1, double x2, double y2){
    double angle = atan2((int)y2 - (int)y1, (int)x2 - (int)x1) * 180 / PI;
    return angle;
  }
 
  // calculates collision between circle and rectangle
  public boolean circleRect(double circleX, double circleY, float size, double rectangleX, double rectangleY, float rectangleWidth, float rectangleHeight) {
    float radius = size/2;
    radius *= 0.6;
    double tempX = circleX;
    double tempY = circleY;
 
    if (circleX < rectangleX) {
      tempX = rectangleX;
    }            
    else if (circleX > rectangleX + rectangleWidth) {
      tempX = rectangleX + rectangleWidth;
    }  
    if (circleY < rectangleY) {
      tempY = rectangleY; 
    } 
    else if (circleY > rectangleY + rectangleHeight) {
      tempY = rectangleY + rectangleHeight;
    }
 
    double distX = circleX - tempX;
    double distY = circleY - tempY;
    double distance = sqrt((float)((distX*distX) + (distY*distY)));
 
    if (distance <= radius) {
      return true;
    }
    return false;
  }
 
  public boolean rectRect(double rect1X, double rect1Y, float rect1Width, float rect1Height, double rect2X, double rect2Y, float rect2Width, float rect2Height){
    if (rect1X < rect2X + rect2Width && rect1X+rect1Width > rect2X && rect1Y < rect2Y + rect2Height && rect1Y + rect1Height > rect2Y){
      return true;
    } 
    return false;
  }
 
  public void bossMove(double x, double y, double destx, double desty, int speed){
    if(x < destx + speed && x > destx - speed && y < desty + speed && y > desty - speed){
      bossXSpd = 0;
      bossYSpd = 0;
    }
    else{
      double dx = destx - x;
      double dy = desty - y;
  
      double length = Math.sqrt(dx*dx + dy*dy);
   
      dx /= length;
      dy /= length;
     
      bossXSpd = dx * speed;
      bossYSpd = dy * speed;
    }
  }









  class playerBullet {
  double X;
  double Y;
  double velX;
  double velY;
  double dx;
  double dy;
  double length;
  int time = 0;
 
  playerBullet(double x, double y, double destx, double desty){
  this.X = x;
  this.Y = y;
  dx = destx - X;
  dy = desty - Y;
 
  length = Math.sqrt(dx*dx + dy*dy);
 
  dx /= length;
  dy /= length;
 
  velX = dx * 20;
  velY = dy * 20;
  }
 
  void update(){
    time++;
    X += velX;
    Y += velY;
    fill(255, 160, 255);
    strokeWeight(1);
    stroke(75, 0, 130);
    ellipse((int)X, (int)Y, 8, 8);
   }
  }
 
  class normalBullet {
    double X;
    double Y;
    double velX;
    double velY;
    boolean isGold;
    boolean isBounce;
    int duration;
    int size; 
    int time;
    int damage;
 
    normalBullet(double x, double y, double angle, int size, int speed, int duration, int damage, boolean isGold, boolean isBounce) {
      this.X = x;
      this.Y = y;
      this.isGold = isGold;
      this.isBounce = isBounce;
      this.size = size;
      this.duration = duration;
      this.damage = damage;
 
      velX = Math.cos(angle*Math.PI/180) * speed;
      velY = Math.sin(angle*Math.PI/180) * speed;
 
      this.time = 0;
    }
 
    void update() {
      X += velX;
      Y += velY;
      time++;
      if (isGold) {
        fill(255, 210, 100);
      }
      else if (isBounce){
        if(time == duration){
          velX = -velX;
          velY = -velY;
          time = 0;
          isBounce = false;
        }
        fill(255, 165, 0);
      }
      else{
        fill(bulletRed);
      }
      strokeWeight(1);
      stroke(0);
      ellipse((int)X, (int)Y, size, size);
    }
 
  }
  class rectBullet {
    double X;
    double Y;
    double velX;
    double velY;
    double duration;
    double time;
    double width;
    double height;
    double shrinkX;
    double shrinkY;
    int damage;
 
    rectBullet(double x, double y, double velX, double velY, double width, double height, int duration, double shrinkX, double shrinkY, int damage){
    this.X = x;
    this.Y = y;
    this.velX = velX;
    this.velY = velY;
    this.width = width;
    this.height = height;
    this.duration = duration;
    this.shrinkX = shrinkX;
    this.shrinkY = shrinkY;
    this.damage = damage;
    time = 0;
    }
 
    void update(){
      time++;
      X += velX + shrinkX/2;
      Y += velY + shrinkY/2;
      width -= shrinkX;
      height -= shrinkY;
      fill(200, 100, 100);
      strokeWeight(1);
      stroke(0);
      rect ((int)X, (int)Y, (float) width, (float) height);
    }
  }
 
 
  class bomb {
    double X;
    double Y;
    double velX;
    double velY;
    double dx;
    double dy;
    double destx;
    double desty;
    double length;
    int fuse;
 
    bomb(double x, double y, double destx, double desty, int fuse) {
      this.X = x;
      this.Y = y;
      dx = destx - X;
      dy = desty - Y;
      this.destx = destx;
      this.desty = desty;
 
      this.fuse = fuse;
 
      length = Math.sqrt(dx*dx + dy*dy);
 
      dx /= length;
      dy /= length;
 
      velX = dx * 20;
      velY = dy * 20;
    }
 
    void update() {
      if(X < destx + 10 && X > destx - 10 && Y < desty + 10 && Y > desty - 10){
        velX = 0;
        velY = 0;
        X = destx;
        Y = desty;
      }
      X += velX;
      Y += velY;
      fuse--;
      fill(20);
      strokeWeight(3);
      if(fuse >= 60){
      stroke(255, 255, 0);
      }
      else if(fuse >= 30){
        stroke(255, 120, 10);
      }
      else{
        stroke(200, 0, 0);
      }
      ellipse((int)X, (int)Y, 20, 20);
    }
  }
 
    class beam {
    double X;
    double Y;
    int size;
    int time;
    boolean hasHit = false;
    beam(double x, double y, int size){
      this.X = x;
      this.Y = y;
      this.size = size;
      time = 0;
    }
 
    void update() {
      time++;
      if(time < 60){
        noFill();
        strokeWeight(1);
        stroke(255, 0, 0);
        ellipse((int)X, (int)Y, size, size);    
        strokeWeight(0);
        fill(255, 200, 200);
        ellipse((int)X, (int)Y, (float) (size*time/60), (float) (size*time/60));
      }
      if(time >= 60){
      strokeWeight(1);
      stroke(255, 0, 0);
      fill(255, 0, 0);
      ellipse((int)X, (int)Y, size, size);
      }
    }
  }
}         