import java.awt.Graphics;
import java.awt.Color;

public class Point {
    double x,y,z; //points that are updated
    double ox,oy,oz; //points that are used to update the points that are updated
    public Point(double x,double y,double z) {
        this.x = x; //add 400 to translate to center of screen
        this.y = y;
        this.z = z;
        ox = this.x;
        oy = this.y;
        oz = this.z;
    }
}

