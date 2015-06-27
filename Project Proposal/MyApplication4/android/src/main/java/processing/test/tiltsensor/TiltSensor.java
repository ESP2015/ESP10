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

    float ax, ay, az;
    FallingBalls fallingballs[] = new FallingBalls[1];
    AccelerometerManager accel;
    PImage img;
    PImage img2;
    PImage img3;
    PImage img4;
    PImage img5;
    PImage img6;
    int p2x = 1027;
    int p2r = 100;
    int p2y = 385;
    int p3x = 875;
    int p3r = 100;
    int p3y = 1533;
    int p4x = 331;
    int p4r = 100;
    int p4y = 1650;
    int p5x = 147;
    int p5r = 100;
    int p5y = 425;
    int c1x = 147;
    int c1rx = 100;
    int c1ry = 50;
    int c1y = 710;
    int p1x = 600;
    int p1r = 100;
    int p1y = 100;
    int gameover = 1;


    public void setup() {

        for (int i = 0; i < fallingballs.length; i++) {
            int si = 100;
            float sp = si * 0.2f;
            int x = 0;
            int y = 0;
            PImage yo = loadImage("guy.png");
            fallingballs[i] = new FallingBalls(yo, x, y, si, sp);
        }
        accel = new AccelerometerManager(this);

        orientation(PORTRAIT);
    }

    public void draw() {
        background(0);
        for (int i = 0; i < fallingballs.length; i++) {
            fallingballs[i].update();
        }
        fill(255, 0, 0);
        rect(400, 300, p3r, p3r);
        rect(500, 400, p4r, p4r);
        rect(300, 500, 100, 100);
        rect(400, 700, 100, 100);
        rect(200, 600, 100, 100);
        rect(25, 300, 100, 50);
        //image(img2, p1x, p1y, p1r, p1r);
        //image(img3,p4x, p4y, p5r, p1r);
        //image(img4,p3x,p3y,p3r,p3r);
        //image(img4,p2x,p2y,p2r,p2r);



        fill(0, 255, 0);
        rect(600, 800, 100, 100);


    }

    public void resume() {
        if (accel != null) {
            accel.resume();
        }
    }


    public void pause() {
        if (accel != null) {
            accel.pause();
        }
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
            image(img, x, y, esize, esize);
            x -= ax * speed;
            y += ay * speed;

//-------COLLISION DETECTION------------
            if (x > displayWidth - esize) {
                x = displayWidth - esize;
            }
            if (x < 0 + esize) {
                x = 0 + esize;
            }
            if (y > displayHeight - esize) {
                y = displayHeight - esize;
            }
            if (y < 0 + esize) {
                y = 0 + esize;
            }
            if (x  < 500) {

                        if (y > 300) {
                            gameover =1;
                        }
                    }




            if (y > 800){
                if (x >600 ) {
                gameover = 2;
            }}
            if (y - esize == p3y + p3r) {

            }
            if (y + esize == p3y - p3r) {

            }
            if (y - esize == p4y + p4r) {

            }
            if (y + esize == p4y - p4r) {

            }
            if (y - esize == p5y + p5r) {

            }
            if (y + esize == p5y - p5r) {

            }
            if (y + esize == c1y - c1ry) {

            }
            if (y - esize == c1y + c1ry) {

            }


            //--------------COLLISION DETECTION ENDS--------------
            if (gameover == 1) {


                fill(255, 0, 0);
                rect(0, 0, 1000, 100);
                gameover=0;
            }

            if (gameover == 2) {

                fill(0, 255, 0);
                rect(0, 0, 1000, 100);
                gameover=0;

            }

        }
    }
}





