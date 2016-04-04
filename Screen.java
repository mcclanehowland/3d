import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Screen extends JPanel implements MouseListener,MouseMotionListener {
    private BufferedImage bufferedImage;
    private int count;
    private boolean mouseDown = false;
    private int mx,my; //the coordinates of the mouse when it is clicked
    private int px,py; //previous x and y coordinates of the mouse

    double thetaX = 0;
    double thetaY = 0;
    double thetaZ = 0;

    ArrayList<Point> points;
    ArrayList<Polygon> polygons;
    ArrayList<Cube> cubes;

    public Screen() {
        addMouseListener(this);
        addMouseMotionListener(this);
        cubes = new ArrayList<Cube>();
        points = new ArrayList<Point>();
        polygons = new ArrayList<Polygon>();
        cubes.add(new Cube(0,0,0,50));
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
            //rotateX(thetaX); //happens every time repaint is called
            //rotateY(thetaY);
            //rotateZ(thetaZ);
        //axes
        gBuff.setColor(Color.black);
        gBuff.drawLine(0,300,800,300); //x
        gBuff.drawLine(400,0,400,600); //y
        for(int i = 0;i < cubes.size()-1;i++) {
            for(int j = i+1;j < cubes.size();j++) {
                if(cubes.get(i).largest > cubes.get(j).largest) {
                    Cube temp = cubes.get(i);
                    cubes.set(i,cubes.get(j));
                    cubes.set(j,temp);
                }
            }
        }
        for(Cube each : cubes) {
            each.draw(gBuff);
        }
        g.drawImage(bufferedImage,0,0,null);
    }
    
    //rotation code from tutorial
    public void rotateX(double theta) { //along the x-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Cube each : cubes) {
            for(Point p : each.points) {
                double y = p.y;
                double z = p.z;
                p.y = (y*cos - z*sin);
                p.z = (z*cos + y*sin);
            }
        }
    }
    public void rotateY(double theta) { //along the y-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Cube each : cubes) {
            for(Point p: each.points) {
                double x = p.x;
                double z = p.z;
                p.x = x*cos - z*sin;
                p.z = z*cos + x*sin;
            }
        }
    }
    public void rotateZ(double theta) { //along the z-axis
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        for(Cube each : cubes) {
            for(Point p : each.points) {
                double x = p.x;
                double y = p.y;
                p.x = x*cos - y*sin;
                p.y = y*cos + x*sin;
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
	}
    public void mouseDragged(MouseEvent e) {
        count++;
        if(e.getX() - px < 0) {
            thetaY -= 0.001;
        }
        else if(e.getX() - px > 0) {
            thetaY += 0.001;
        }
        rotateX(thetaX); 
        rotateY(thetaY);
        rotateZ(thetaZ);
        repaint();
        px = e.getX();
        py = e.getY();
    }
    public void sleep(int time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch(InterruptedException ex) 
		{
			Thread.currentThread().interrupt();
		}
	}
    public void mouseMoved(MouseEvent e) {
    }
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
}
