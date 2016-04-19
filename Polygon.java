import java.awt.Graphics;
import java.awt.Color;

public class Polygon {
    int[] xpoints,ypoints,zpoints;
    boolean wireframe;
    Color color;
    public Polygon(int[] xpoints,int[] ypoints,int[] zpoints,Color color,boolean wireframe) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.zpoints = zpoints;
        this.color = color;
        this.wireframe = wireframe;
    }
    //draw the polygon
    public void draw(Graphics g) {
        g.setColor(color);
        if(!wireframe) {
            g.fillPolygon(xpoints,ypoints,xpoints.length);
        }
        else {
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
}
