import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

public class Face {
    int[] xpoints,ypoints,zpoints; //public so I can access easily from screen for collision detection w/o methods
    boolean wireframe; //controls color (default is black) and whether to shade ore not
    Polygon boundary;
    Color color;
    public Face(int[] xpoints,int[] ypoints,int[] zpoints,Color color,boolean wireframe) {
        //each array is 4 values, because it is one face of the cube. Z values included for drawing order
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.zpoints = zpoints;
        this.color = color;
        this.wireframe = wireframe;
        boundary = new Polygon(xpoints,ypoints,4);
    }
    //draw the polygon
    public void draw(Graphics g) {
        g.setColor(color);
        if(!wireframe) { //if wireframe is false, fill the polygon (with color)
            g.fillPolygon(xpoints,ypoints,xpoints.length);
        }
        else { //draw a wireframe of the polygon
            g.drawPolygon(xpoints,ypoints,xpoints.length);
        }
    }
    //returns the largest z value in the array of points
    public int getLargestZ() {  
       int largest = zpoints[1];
       for(int i = 0; i < zpoints.length;i++) {
           if(zpoints[i] > largest) {
               largest = zpoints[i];
           }
       }
       return largest;
    }
    public boolean contains(int x,int y) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = 5; i < 8; j = i++) {
            if ((ypoints[i] > y) != (ypoints[j] > y) && (x < (xpoints[j] - xpoints[i]) * (y - ypoints[i]) / (ypoints[j]-ypoints[i]) + xpoints[i])) {
                result = !result;
            }
        }
        return result;
    }
}
