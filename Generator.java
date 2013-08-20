
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.applet.*; 


import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;



/**
 * Class Mandelplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class Generator extends JApplet 
{
    private double startX= -10.0;
    
    private double endX = 10.0;
    private double startY = -10.0;
    private double endY = 10.0;
    private double x = startX;
    private double y = startY;
    private double currentX=-0.5;
    private double currentY=0;
    private double xRange = Math.abs(startX-endX);
    private double yRange = Math.abs(startY-endY);
    private double mag = 25;
    private double interval = 1.0/mag;
    private static Point2D.Double CENTER = new Point2D.Double(500,500);
    private Graphics2D g2;
    private double xCoord = 0;
    private double yCoord=0;
    private double zoom = 1;
    private boolean loading = true;
    private Calc numbers = new Calc();
    public void init()
    {
        
        numbers.initialize();
         
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void clearscreen(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle2D.Double blank = new Rectangle2D.Double(0,0,1200 ,1200);
        g2.setColor(Color.white);
        g2.fill(blank);
         g2.setColor(Color.black);
        
    }
    public void paint(Graphics g)
    {

        g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        
       // if (loading==false) 
        //{
        //    clearscreen(g);
            //g2.drawString("X"+zoom,0,50);
          //  g2.drawString("Res: "+ITER,0,80);
        //}
           
        
      //  loading = true;
        
        //String Coord= "" + xCoord+", "+yCoord;
        //g2.drawString(Coord,(float)xCoord,(float)yCoord);
        
        y+=interval;
        if (y>endY)
        {
            y=startY;
            x+=interval;
        }
        
        Complex input = new Complex(x,y);
        g2.setColor(Color.black);
        int[] stuff = numbers.magic(input);
        //int col=(stuff[1]+1)*50;
        //g2.setColor(new Color(col,120, 120));
        g2.setColor(new Color(Color.HSBtoRGB((float)1/numbers.roots()*(stuff[1]),1.0f,(float)(1.0-(.02*stuff[0])))));
        //if (temperature==0) g2.setColor(Color.black);
       // else g2.setColor(new Color(255-(int)(255/ITER*temperature),255,255));
       // else g2.setColor(new Color(Color.HSBtoRGB((float)(1.0/ITER*temperature),1.0f,0.8f)));
        
             
           
        
        g2.fill(new Rectangle2D.Double(CENTER.getX()+(x-currentX)*mag,CENTER.getY()+(y-currentY)*mag,interval*mag,interval*mag));
        //g2.fill(new Rectangle2D.Double(center.getX()+x*mag,center.getY()+y*(-mag),interval*mag,interval*mag));
        
        if (x<endX) repaint();
        else
        {
            //loading = false;
            //Rectangle2D.Double block = new Rectangle2D.Double(0,0,500,500);
            //g2.fill(block);
        }
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
   
}
