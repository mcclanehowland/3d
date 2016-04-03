import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Cube {
    int x,y,z,size;
    ArrayList<Point> points;
    ArrayList<Polygon> polygons;
    
    public Cube(int x,int y, int z, int size) {
        this.x = x; 
        this.y = y; 
        this.z = z; 
        this.size = size; 
        points = new ArrayList<Point>();

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

        polygons = new ArrayList<Polygon>();
    }
    public void draw(Graphics g) {
        for(int i = 0; i < points.size();i += 4) {

            Color color = new Color((i*100)%255,(i*100)%255,(i*100)%255);

            int x1 = (int)points.get(i).x+400;
            int x2 = (int)points.get(i+1).x+400;
            int x3 = (int)points.get(i+2).x+400;
            int x4 = (int)points.get(i+3).x+400;
            int[] xpoints = {x1,x2,x3,x4};

            int y1 = (int)points.get(i).y+300;
            int y2 = (int)points.get(i+1).y+300;
            int y3 = (int)points.get(i+2).y+300;
            int y4 = (int)points.get(i+3).y+300;
            int[] ypoints = {y1,y2,y3,y4};

            
            int z1 = (int)points.get(i).z;
            int z2 = (int)points.get(i+1).z;
            int z3 = (int)points.get(i+2).z;
            int z4 = (int)points.get(i+3).z;
            int[] zpoints = {z1,z2,z3,z4};
            
            polygons.add(new Polygon(xpoints,ypoints,zpoints,color));

        }
        //sort the polygons with largest z value first so that the closer ones are drawn over the farther
        for(int i = 0; i < polygons.size()-1;i++) {
            for(int j = i+1; j < polygons.size();j++) {
                if(polygons.get(i).getLargestZ() > polygons.get(j).getLargestZ()) { //if z value is larger it will swap
                    Polygon temp = polygons.get(i);
                    polygons.set(i,polygons.get(j));
                    polygons.set(j,temp);
                }
            }
        }
        for(Polygon each : polygons) {
            each.draw(g);
        }
        polygons.clear();
    }
}
