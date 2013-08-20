
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
import javax.swing.JOptionPane;



/**
 * Class Mandelplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class Generator2 extends JApplet implements MouseListener
{
    private double startX= -10.0;
    private static double FACTOR = 4.0;
    private Rectangle2D.Double blank = new Rectangle2D.Double(0,0,1000 ,1000);
    public static int RES = 40;
    private double endX = 10.0;
    private double startY = -10.0;
    private double endY = 10.0;
    private double x = startX;
    private double y = startY;
    private double currentX=-0.5;
    private double currentY=0;
    private double xRange = Math.abs(startX-endX);
    private double yRange = Math.abs(startY-endY);
    private double mag = 30;
    private double interval = 1.0/mag;
    private static Point2D.Double CENTER = new Point2D.Double(500,340);
    private Graphics2D g2;
    private double xCoord = 0;
    private double yCoord=0;
    private double zoom = 1;
    private boolean loading = true;
    
    private static int fifth = 0;
    private static int fourth = 0;
    private static int third = 1;
    private static int second = 0;
    private static int first = 0;
    private static int constant = -1;
    private Calc numbers = new Calc();
    
    
    public void init()
    {
        fifth= Integer.parseInt(JOptionPane.showInputDialog("x^5 Coefficient?"));
        fourth= Integer.parseInt(JOptionPane.showInputDialog("x^4 Coefficient?"));
        third= Integer.parseInt(JOptionPane.showInputDialog("x^3 Coefficient?"));
        second= Integer.parseInt(JOptionPane.showInputDialog("x^2 Coefficient?"));
        first= Integer.parseInt(JOptionPane.showInputDialog("x Coefficient?"));
        constant= Integer.parseInt(JOptionPane.showInputDialog("Constant?"));
         numbers.initialize(fifth,fourth,third,second,first,constant);
         addMouseListener(this); 
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
        
        g2.setColor(Color.white);
        g2.fill(blank);
         g2.setColor(Color.black);
        
    }
    public void paint(Graphics g)
    {

        g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        
        if (loading==false) 
        {
            clearscreen(g);
            g2.drawString("X"+zoom,200,35);
           
            String function = new String("f(x) = ");
            if (fifth>0) function = function + fifth+"x^5";
            if (fourth>0)function = function +" + " + fourth+"x^4";
            if (third>0)function = function +" + " + third+"x^3";
            if (second>0)function = function +" + " + second+"x^2";
            if (first>0)function = function +" + " + first+"x^1";
            if (constant>0)function = function +" + " + constant;
            g2.drawString(function,200,20);
            g2.drawString("BachSikkaHayes Productions, Copyright 2011",200,655);
            g2.drawString("(You know you're jealous, Ben Most)",200,695);
            g2.drawString("WE <3 FRACTALS (and matt hayess)",200,675);
        }
           
        
        loading = true;
        
       
        
        y+=interval;
        if (y>endY)
        {
            y=startY;
            x+=interval;
        }
        
        Complex input = new Complex(x,y);
        g2.setColor(Color.black);
         int[] stuff = numbers.magic(input);
         g2.setColor(new Color(Color.HSBtoRGB((float)1/numbers.roots()*(stuff[1]),1.0f,(float)(1.0-(.02*stuff[0])))));
         if (stuff[0]==0) g2.setColor(Color.black);   
           
        
        g2.fill(new Rectangle2D.Double(CENTER.getX()+(x-currentX)*mag,CENTER.getY()+(y-currentY)*mag,interval*mag,interval*mag));
       
        
        if (x<endX) repaint();
        
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
    public void mousePressed(MouseEvent e) 
   {
      // if (loading == false)
       //{
           if (e.getButton()==MouseEvent.BUTTON1)
           {
           currentX = ((double)e.getX()-(double)CENTER.getX())*interval+currentX;
           currentY = ((double)e.getY()-(double)CENTER.getY())*interval+currentY;
           xRange=xRange/FACTOR;
           yRange=yRange/FACTOR;
           
           startX = currentX-(xRange/2.0);
           startY = currentY-(yRange/2.0);
           endX = currentX+(xRange/2.0);
           endY = currentY+(yRange/2.0);
           x=startX;
           y=startY;
           mag=mag * FACTOR;
           interval = 1.0/mag;
           zoom*=FACTOR;
           //ITER = 100 + Math.log10(zoom)*RES;
           repaint();
           }
           
           if (e.getButton()==MouseEvent.BUTTON3)
           {
           currentX = ((double)e.getX()-(double)CENTER.getX())*interval+currentX;
           currentY = ((double)e.getY()-(double)CENTER.getY())*interval+currentY;
           xRange=xRange*FACTOR;
           yRange=yRange*FACTOR;
           
           startX = currentX-(xRange/2.0);
           startY = currentY-(yRange/2.0);
           endX = currentX+(xRange/2.0);
           endY = currentY+(yRange/2.0);
           x=startX;
           y=startY;
           mag=mag / FACTOR;
           interval = 1.0/mag;
           zoom =zoom/FACTOR;
           //ITER = 100 + Math.log10(zoom)*RES;
           repaint();
          
           
        }
         loading=false;      
               
       // }
          

   }
   
   public void mouseReleased(MouseEvent e) {}
   public void mouseClicked(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
}
