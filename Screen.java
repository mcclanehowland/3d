import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Screen extends JPanel {
    private BufferedImage bufferedImage;
    private int count;

    ArrayList<Point> points;
    ArrayList<Polygon> polygons;

    public Screen() {
        points = new ArrayList<Point>();
        polygons = new ArrayList<Polygon>();

        //add four faces to the cube
        points.add(new Point(0,0,0)); 
        points.add(new Point(100,0,0));
        points.add(new Point(100,100,0));
        points.add(new Point(0,100,0));

        points.add(new Point(0,0,100));
        points.add(new Point(100,0,100));
        points.add(new Point(100,100,100));
        points.add(new Point(0,100,100));

        points.add(new Point(0,0,0));
        points.add(new Point(0,0,100));
        points.add(new Point(0,100,100));
        points.add(new Point(0,100,0));

        points.add(new Point(100,0,0));
        points.add(new Point(100,0,100));
        points.add(new Point(100,100,100));
        points.add(new Point(100,100,0));
    }
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bufferedImage == null) {
            bufferedImage = (BufferedImage)(createImage(getWidth(),getHeight()));
        }
        Graphics gBuff = bufferedImage.createGraphics();
        gBuff.setColor(Color.white);
        gBuff.fillRect(0,0,800,600);
        /**
            do the rotating here:
        */
            rotateX(Math.PI/200); //happens every time repaint is called
            rotateY(Math.PI/200);
            rotateZ(Math.PI/200);
        //axes
        gBuff.setColor(Color.black);
        gBuff.drawLine(0,300,800,300); //x
        gBuff.drawLine(400,0,400,600); //y
        //draw the points
        gBuff.setColor(Color.red);
        for(Point each : points) {
            gBuff.fillOval((int)each.x-2+400,(int)each.y-2+300,4,4);
        }
        //experiment with polygons
        // creates a new polygon for each set of four points, aka a face of the cube 
        for(int i = 0; i < points.size();i += 4) {

            Color color = new Color((i*100)%255,(i*100)%255,(i*1000)%255);

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
            each.draw(gBuff);
        }
        g.drawImage(bufferedImage,0,0,null);
        polygons.clear();

    }
    
    //rotation code from tutorial
    public void rotateX(double theta) { //along the x-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Point each : points) {
            double y = each.y;
            double z = each.z;
            each.y = y*cos - z*sin;
            each.z = z*cos + y*sin;
        }
    }
    public void rotateY(double theta) { //along the y-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Point each : points) {
            double x = each.x;
            double z = each.z;
            each.x = x*cos - z*sin;
            each.z = z*cos + x*sin;
        }
    }
    public void rotateZ(double theta) { //along the z-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Point each : points) {
            double x = each.x;
            double y = each.y;
            each.x = x*cos - y*sin;
            each.y = y*cos + x*sin;
        }
    }
    public void animate() {
        while(true) {
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            repaint(); //rotation is in the animate class
        }
    }
            

}
