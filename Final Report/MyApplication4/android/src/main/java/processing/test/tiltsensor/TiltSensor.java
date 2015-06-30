package processing.test.tiltsensor;

import android.content.Intent;

import processing.core.*;
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException;

public class TiltSensor extends PApplet {
    public class Pirate {
        public Pirate( int X, int Y, int Image )
        {
            this.x = X;
            this.y = Y;
            this.image = Image;
        }
        public int image;
        public int x;
        public int y;
    }
    float ax, ay, az;
    FallingBalls fallingballs[] = new FallingBalls[1];
    AccelerometerManager accel;
    PImage Gold;
    int gameover = 1;
    Pirate[] pirates;
    PImage[] pImages;
    PImage Win;

    public void setup() {
        pImages = new PImage[3];
        pirates = new Pirate[15];
        pImages[0] = loadImage("Captain.png");
        pImages[1] = loadImage("Pirate1v3Animated.png");
        pImages[2] = loadImage("Pirate2v2.png");
        Win = loadImage("win.png");
        Gold = loadImage("GoldPile.png");
        for (int i = 0; i < fallingballs.length; i++) {
            int size = displayWidth/10;
            PImage yo = loadImage("guy.png");
            fallingballs[i] = new FallingBalls(yo, 0, 0, size, size * 0.2f);
        }
        for( int i  = 0; i < pirates.length; i++ ) {
            int im = (int)random(0,pImages.length);
            int randx = 0;
            int randy = 0;
            while( (randx <= 3*displayWidth/10+1 && randy <= 3*displayWidth/10+1) || (randx >= displayWidth-displayWidth/3 && randy >= displayHeight-displayWidth/3) )
            {
                randx = (int) random(0, displayWidth - pImages[im].width);
                randy = (int) random(0, displayHeight - pImages[im].height);
            }
            pirates[i] = new Pirate(randx, randy, im);
        }
        accel = new AccelerometerManager(this);

        orientation(PORTRAIT);
    }

    public void draw() {
        background(0);
        fill(255, 0, 0);
        image(Gold,displayWidth-2*displayWidth/9,displayHeight-2*displayWidth/9,displayWidth/9,displayWidth/9);
        for( Pirate pirate : pirates )
            image(pImages[pirate.image], pirate.x, pirate.y, pImages[pirate.image].width*displayWidth/(15*pImages[0].width), pImages[pirate.image].height*displayHeight/(15*pImages[0].height));
        for (int i = 0; i < fallingballs.length; i++)
            fallingballs[i].update();
    }

    public void resume() {
        if (accel != null)
            accel.resume();
    }


    public void pause() {
        if (accel != null)
            accel.pause();
    }


    public void shakeEvent(float force) {
        println("shake : " + force);
    }

    public void accelerationEvent(float x, float y, float z) {
//  println("acceleration: " + x + ", " + y + ", " + z);
        ax = x;
        ay = y;
        az = z;
        redraw();
    }

    class FallingBalls {
        long timer = 0;
        int x, y, esize;
        float speed;
        PImage img;

        FallingBalls(PImage yo, int xa, int ya, int si, float sp) {
            x = xa;
            y = ya;
            esize = si;
            speed = sp;
            img = yo;
        }


        public void update() {
            x -= ax * speed;
            y += ay * speed;
//-------COLLISION DETECTION------------
            if (x > displayWidth - esize) {
                x = displayWidth - esize;
            }
            if (x < 0) {
                x = 0;
            }
            if (y > displayHeight - esize) {
                y = displayHeight - esize;
            }
            if (y < 0) {
                y = 0;
            }
            if( gameover != 3 ) {
                for (Pirate pirate : pirates) {
                    if (x + esize > pirate.x && x < pirate.x + pImages[pirate.image].width * displayWidth / (15 * pImages[0].width) &&
                            y + esize > pirate.y && y < pirate.y + pImages[pirate.image].height * displayHeight / (15 * pImages[0].height))
                        gameover = 1;
                }
            } else if( System.currentTimeMillis() - timer > 2500 ) {
                for( int i  = 0; i < pirates.length; i++ ) {
                    int im = (int)random(0,pImages.length);
                    int randx = 0;
                    int randy = 0;
                    while( (randx <= 3*displayWidth/10+1 && randy <= 3*displayWidth/10+1) || (randx >= displayWidth-displayWidth/3 && randy >= displayHeight-displayWidth/3) )
                    {
                        randx = (int) random(0, displayWidth - pImages[im].width);
                        randy = (int) random(0, displayHeight - pImages[im].height);
                    }
                    pirates[i] = new Pirate(randx, randy, im);
                }
                x = esize;
                y = esize;
                gameover = 0;
            }
            if( x+esize > displayWidth-2*displayWidth/9 && x < displayWidth-displayWidth/9 &&
                    y+esize > displayHeight-2*displayWidth/9 && y < displayHeight-displayWidth/9 )
                    gameover = 2;


            //--------------COLLISION DETECTION ENDS--------------


            if (gameover == 2) {
                fill(0, 255, 0);
                rect(0, 0, 1000, 100);
                gameover = 3;
                timer = System.currentTimeMillis();
            } else if (gameover == 1) {
                fill(255, 0, 0);
                rect(0, 0, 1000, 100);
                x = esize;
                y = esize;
                gameover = 0;
            }

            if( gameover == 3 )
                image(Win,0,0,displayWidth,displayHeight);

            image(img, x, y, esize, esize);
        }
    }
}





