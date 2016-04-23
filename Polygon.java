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
    public boolean contains(int x, int y) {
        /* 
            line out from the point. if the # of 
            times it intersects polygon is even,
            it is inside. If it is odd, it is 
            outside. Java implementation copied
            from stackexchange
        */
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = xpoints.length - 1; i < xpoints.length; j = i++) { //no clue wtf this loop struct is
            if ((ypoints[i] > y) != (ypoints[j] > y) && (x < (xpoints[j] - xpoints[i]) * (y - ypoints[i]) / (ypoints[j]-ypoints[i]) + xpoints[i])) {
                    result = !result;
            }
        }
        return result;
    }
}


