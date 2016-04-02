import java.awt.Graphics;
import java.awt.Color;

public class Polygon {
    int[] xpoints,ypoints,zpoints;
    Color color;
    public Polygon(int[] xpoints,int[] ypoints,int[] zpoints,Color color) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.zpoints = zpoints;
        this.color = color;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(xpoints,ypoints,xpoints.length);
    }
    public int getLargest() {
       int largest = zpoints[1];
       for(int i = 0; i < zpoints.length;i++) {
           if(zpoints[i] > largest) {
               largest = zpoints[i];
           }
       }
       return largest;
    }
}
