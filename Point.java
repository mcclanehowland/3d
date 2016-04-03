import java.awt.Graphics;
import java.awt.Color;

public class Point {
    double x,y,z; //x,y, and z locations
    double ox,oy,oz;
    int x2d,y2d;
    int size = 4; //size of the point drawn on the screen
    double theta = 0;
    public Point(int x,int y,int z) {
        //using three seperate int values
        this.x = x;
        this.y = y;
        ox = x;
        oy = y;
        oz = z;
        if(z == 0) {
            this.z = 1;
        }
        else {
            this.z = z;
        }
    }
    public void rotateZ() {
        theta += 0.05;
        //ox is the original x and oy is the original y
        x = ox * Math.cos(theta) - oy*Math.sin(theta);
        y = oy * Math.cos(theta) + ox*Math.sin(theta);
    }
    public void rotateX() {
        theta += 0.05;
        //ox is the original x and oy is the original y
        y = oy * Math.cos(theta) - oz*Math.sin(theta);
        z = oz * Math.cos(theta) + oy*Math.sin(theta);
    }
    public void rotateY() {
        theta += 0.05;
        //ox is the original x and oy is the original y
        x = ox * Math.cos(theta) - oz*Math.sin(theta);
        z = oz * Math.cos(theta) + ox*Math.sin(theta);
    }



    public void drawPoint(Graphics g) { //more later
        g.setColor(Color.red);
        g.fillRect((int)x+400,(int)y+300,size,size);
    }
}

