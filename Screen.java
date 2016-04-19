import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Screen extends JPanel implements KeyListener,MouseListener,MouseMotionListener {
    private BufferedImage bufferedImage;

    Cube moving;

    private boolean rotateUp,rotateDown,rotateLeft,rotateRight;

    double thetaX = 0;
    double thetaY = 0;
    double thetaZ = 0;

    ArrayList<Point> points;
    ArrayList<Polygon> polygons;
    ArrayList<Cube> cubes;

    public Screen() {
        //add the keylistener
        addKeyListener(this);
        //set focusable to true to allow focus to shift to keyboard
        setFocusable(true);
        //add the mouse and mousemotionlist
        addMouseListener(this);
        addMouseMotionListener(this);
        //create cube arraylist
        cubes = new ArrayList<Cube>();
        /** add the cubes */
        for(int x = -260;x <= 260;x += 80) {
            for(int y = -260;y <= 260;y += 80) {
                cubes.add(new Cube(x,y,-20,40));
            }
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //set up the bufferedImage ///////////////////////
        if(bufferedImage == null) {
            bufferedImage = (BufferedImage)(createImage(getWidth(),getHeight()));
        }
        Graphics gBuff = bufferedImage.createGraphics();
        gBuff.setColor(Color.white);
        gBuff.fillRect(0,0,800,600);
        /////////////////////////////////////////////////
        /**
            do the rotating here:
        */
            //rotateX(thetaX); //happens every time repaint is called
            //rotateY(thetaY);
            //rotateZ(thetaZ);
        //draw the axes
        gBuff.setColor(Color.black);
        gBuff.drawLine(0,300,800,300); //x
        gBuff.drawLine(400,0,400,600); //y
        for(int i = 0;i < cubes.size()-1;i++) { //sort the cubes by z values to draw them in the correct order. Bubble sort, but who the fuck cares????
            for(int j = i+1;j < cubes.size();j++) { 
                if(cubes.get(i).largest > cubes.get(j).largest) { //getLargest returns the largest z value in the cube, the larger the z the farther back it will appear
                    Cube temp = cubes.get(i);
                    cubes.set(i,cubes.get(j));
                    cubes.set(j,temp);
                }
            }
        }
        for(Cube each : cubes) { //draw them cubes
            each.draw(gBuff);
        }
        g.drawImage(bufferedImage,0,0,null);
    }
    //rotation code from tutorial
    public void rotateX(double theta) { //along the x-axis
        double cos = Math.cos(theta); //calculate once, not everytime in the loop
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
        double cos = Math.cos(theta); //calculate once, not everytime in the loop 
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
        double cos = Math.cos(theta); //calculate once, not every time in the loop 
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
    public void move() {
        while(true) { //infinite loop
            sleep(10); //sleep with the sleep function
            if(rotateUp) {
                rotateX(Math.PI/200);
            }
            else if(rotateDown) {
                rotateX(-Math.PI/200);
            }
            if(rotateLeft) {
                rotateY(Math.PI/200);
            }
            else if(rotateRight) {
                rotateY(-Math.PI/200);
            }
            repaint();
        }
    }
    
    //key listener
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == 38) {
            rotateUp = true;
        }
        else if(key == 40) {
            rotateDown = true;
        }
        else if(key == 37) {
            rotateLeft = true;
        }
        else if(key == 39) {
            rotateRight = true;
        }   
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == 38) {
            rotateUp = false;
        }
        else if(key == 40) {
            rotateDown = false;
            
        }
        else if(key == 37) {
            rotateLeft = false;
        }
        else if(key == 39) {
            rotateRight = false;
        }
    }
    //mousepressed for initial location of click
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for(int i = 0;i < cubes.size();i++) {
            if(x > cubes.get(i).x+400 && x < cubes.get(i).x+400+cubes.get(i).size && y > cubes.get(i).y+300 && y < cubes.get(i).y+300 + cubes.get(i).size) {
                moving = cubes.get(i);
                cubes.remove(i);
                i--;
                break;
            }
        }
    }
    //mousedragged for updating the location of the block being dragged across the fucking screen
    public void mouseDragged(MouseEvent e) {
       if(moving != null) {

        
           repaint();
       }
    }
    //useless methods that need to be in because of listener interfaces
    public void mouseReleased(MouseEvent e) {
        moving = null;     
    }
    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void keyTyped(KeyEvent e) {}
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
}
