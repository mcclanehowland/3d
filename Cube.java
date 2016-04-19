import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Cube {
    int x,y,z,size,largest;
    ArrayList<Point> points;
    ArrayList<Face> polygons;
    boolean wireframe = false;
    
    public Cube(int x,int y, int z, int size) {
        this.x = x; 
        this.y = y; 
        this.z = z; 
        this.size = size; 
        points = new ArrayList<Point>();
        polygons = new ArrayList<Face>();

        /** adding all the points. They are grouped into the 6 faces of the cube */

        points.add(new Point(x,y,z));  
        points.add(new Point(x+size,y,z));
        points.add(new Point(x+size,y+size,z));
        points.add(new Point(x,y+size,z));

        points.add(new Point(x,y,z+size));
        points.add(new Point(x+size,y,z+size));
        points.add(new Point(x+size,y+size,z+size));
        points.add(new Point(x,y+size,z+size));

        points.add(new Point(x,y,z));
        points.add(new Point(x,y,z+size));
        points.add(new Point(x,y+size,z+size));
        points.add(new Point(x,y+size,z));

        points.add(new Point(x+size,y,z));
        points.add(new Point(x+size,y,z+size));
        points.add(new Point(x+size,y+size,z+size));
        points.add(new Point(x+size,y+size,z));

        points.add(new Point(x,y+size,z));
        points.add(new Point(x+size,y+size,z));
        points.add(new Point(x+size,y+size,z+size));
        points.add(new Point(x,y+size,z+size));

        points.add(new Point(x,y,z));
        points.add(new Point(x+size,y,z));
        points.add(new Point(x+size,y,z+size));
        points.add(new Point(x,y,z+size));

        
    }
    public void draw(Graphics g) {
        for(int i = 0; i < points.size();i += 4) {

            Color color = new Color(120/*(i*100)%255*/,(i*100)%255,(i*100)%255);

            //create the polygons, cumbersome, but it works
            int x1 = (int)points.get(i).x+400;
            int x2 = (int)points.get(i+1).x+400;
            int x3 = (int)points.get(i+2).x+400;
            int x4 = (int)points.get(i+3).x+400;
            int x5 = x1;
            int[] xpoints = {x1,x2,x3,x4,x5};

            int y1 = (int)points.get(i).y+300;
            int y2 = (int)points.get(i+1).y+300;
            int y3 = (int)points.get(i+2).y+300;
            int y4 = (int)points.get(i+3).y+300;
            int y5 = y1;
            int[] ypoints = {y1,y2,y3,y4,y5};

            
            int z1 = (int)points.get(i).z;
            int z2 = (int)points.get(i+1).z;
            int z3 = (int)points.get(i+2).z;
            int z4 = (int)points.get(i+3).z;
            int z5 = z1;
            int[] zpoints = {z1,z2,z3,z4,z5};
            
            polygons.add(new Face(xpoints,ypoints,zpoints,color,wireframe));

        }
        //sort the polygons with largest z value first so that the closer ones are drawn over(last) the farther ones
        for(int i = 0; i < polygons.size()-1;i++) {
            for(int j = i+1; j < polygons.size();j++) {
                if(polygons.get(i).getLargestZ() > polygons.get(j).getLargestZ()) { //if z value is larger it will swap
                    Face temp = polygons.get(i);
                    polygons.set(i,polygons.get(j));
                    polygons.set(j,temp);
                }
            }
        }
        //largest = largest z valued polygon
        largest = polygons.get(polygons.size()-1).getLargestZ();
        //draw the polygons
        for(Face each : polygons) {
            each.draw(g);
        }
        //clear the polygon array
        polygons.clear();
    }
}
