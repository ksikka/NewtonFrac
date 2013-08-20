

import java.util.ArrayList;

public class Calc
{
 


   private static Complex co5;
   private static Complex co4;
   private static Complex co3;
   private static Complex co2 ;
   private static Complex co1;
  private static Complex co0 ;

    private static Complex root1;
    private static Complex root2;
    private static Complex root3;
    private static Complex root4;
    private static Complex root5;
    private static boolean  one = false;
   private static boolean two= false;
     private static boolean three= false;
     private static boolean four= false;
    private static boolean five= false;
    private static int[] ret;
    private static int solutions;
    private static int index;
    private static ArrayList<Complex> sol;
    
    public static void initialize(int a,int b,int c, int d, int e,int f)
    {
     co5 = new Complex(a,0);
   co4 = new Complex(b,0);
   co3 = new Complex(c,0);
 co2 = new Complex(d,0);
    co1 = new Complex(e,0);
   co0 = new Complex(f,0);
          ret = new int[2];
         solutions = 5;
          index =0;
        if (a == 0) 
        {   
            solutions--;
            if (b == 0)
            {
            solutions--;
            if (c == 0)
                {
                solutions--;
                if (d == 0) 
                    {
                    solutions--;
                    if (e == 0)
                        {
                        solutions--;
                        }
                    }
                }
            }
        }
        sol = new ArrayList<Complex>(5);
        for (int j = 0; j<solutions;j++)
        {
            sol.add(j, new Complex(0,0));
            
            
        }
    }
    public static int roots()
    {
        return solutions;
    }
    public static int[] magic(Complex z)
    {
        
        
        
        for (int i = 0;i<50;i++)
        {   
            for (int k =0;k<solutions;k++)
            {
            Complex buffer = z.subtract(sol.get(k));
            if ((buffer.getReal()<.01)&&(buffer.getReal()>-.01)&&(buffer.getImaginary()<.01)&&(buffer.getImaginary()>-.01)) {ret[0]=i;ret[1]=k;return ret;}
            }
            Complex znext = iterate(z);
            Complex buffer2 = z.subtract(znext);
             //if (z.equals(znext)) {sol.remove(index);sol.add(index,z);index++;ret[0]=i;ret[1]=index-1;}
           if ((buffer2.getReal()<.001)&&(buffer2.getReal()>-.001)&&(buffer2.getImaginary()<.001)&&(buffer2.getImaginary()>-.001) ) {sol.remove(index);sol.add(index,z);index++;ret[0]=i;ret[1]=index-1;}
            z=znext;
            
            //((buffer.getReal()<1.0)&&(buffer.getReal()>-1.0)&&(buffer.getImaginary()<1.0)&&(buffer.getImaginary()>-1.0) )||
        }
        int[] dummy = new int[2];
        dummy[0] = 200;
        dummy[1] = 10;
        return dummy;
            
     
        
    
        
        
        
        
    }
    public static Complex iterate(Complex z)
    {
        return Complex.add(z,Complex.multiply(new Complex(-1,0),Complex.divide(function(z),derivative(z))));
              
        
    }
    public static Complex function(Complex z)
    {
        return Complex.add(Complex.add(Complex.add(z.multiply(z.multiply(z.multiply(z.multiply(z.multiply(co5))))),z.multiply(z.multiply(z.multiply(z.multiply(co4))))),Complex.add(z.multiply(z.multiply(z.multiply(co3))),z.multiply(z.multiply(co2)))),Complex.add(z.multiply(co1),co0));
    }
    public static Complex derivative(Complex z)
    {
        return Complex.add(Complex.add(     Complex.add(z.multiply(z.multiply(z.multiply(z.multiply(new Complex(5,0)).multiply(co5)))),z.multiply(z.multiply(z.multiply(new Complex(4,0)).multiply(co4)))),   Complex.add(z.multiply(z.multiply(new Complex(3,0)).multiply(co3)),z.multiply(new Complex(2,0)).multiply(co2)))   ,co1);
    }
    
    public static int converges(Complex c, double iterations)
    {
        Complex z = new Complex(0,0);
  
       
        
        for (int i =1;i<iterations;i++)
        {
            z=iterate(z);
            if (Math.pow(z.getReal(), 2) + Math.pow(z.getImaginary(), 2) > 4) return i;
            
        }
        
        return 0;
    }
}
