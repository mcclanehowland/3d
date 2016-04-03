import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener, MouseListener, KeyListener {

    private BufferedImage bufferedImage;
    ArrayList<Point> points;

    public Screen() {
        //add key/mouse listeners and set jbutton layout
        setLayout(null);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        points = new ArrayList<Point>();

        //add the square ( > 8 because need to connect lines)
        points.add(new Point(0,0,0));
        points.add(new Point(0,0,100));
        points.add(new Point(100,0,100));
        points.add(new Point(100,0,0));
        points.add(new Point(0,0,0)); //connect the bottom square
        points.add(new Point(0,100,0));
        points.add(new Point(0,100,100));
        points.add(new Point(100,100,100));
        points.add(new Point(100,100,0));
        points.add(new Point(0,100,0)); //connect the top square
        points.add(new Point(100,100,0));
        points.add(new Point(100,0,0));
        points.add(new Point(100,0,100));
        points.add(new Point(100,100,100));
        points.add(new Point(0,100,100));
        points.add(new Point(0,0,100));
        
    }
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(bufferedImage==null) 
            bufferedImage = (BufferedImage)(createImage(getWidth(),getHeight()));
        
        Graphics gBuff = bufferedImage.createGraphics();
        gBuff.setColor(Color.white);
        gBuff.fillRect(0,0,800,600);

        gBuff.setColor(Color.black);
        gBuff.drawLine(0,300,800,300);
        gBuff.drawLine(400,0,400,600);

        //draw the points
        for(Point each : points) {
            each.drawPoint(gBuff);
        }
        //connect the points
        gBuff.setColor(Color.red);
        for(int i = 0; i < points.size()-1;i++) {
            gBuff.drawLine((int)points.get(i).x+400,(int)points.get(i).y+300,(int)points.get(i+1).x+400,(int)points.get(i+1).y+300);
        }

        g.drawImage(bufferedImage,0,0,null);
    }
    public void animate() {
        while(true) {
            sleep(100);
            for(Point each : points) {
                each.rotateY();
            }
            repaint();
        }
    }
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public void actionPerformed(ActionEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }
    public void keyPressed(KeyEvent e) {

    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent evt) {}
}
