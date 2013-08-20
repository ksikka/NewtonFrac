
public class Tester
{
 
    
    public static void Main(String[] args)
    {   
        Complex c = new Complex(0,1);
        Complex d = new Complex(0,-1);
        Calc numbers = new Calc();
        numbers.initialize();
        int[] output = numbers.magic(c);
        
        
        System.out.println(output[0]);
        System.out.println(output[1]);
        int[] output2 = numbers.magic(d);
        
        
        System.out.println(output2[0]);
        System.out.println(output2[1]);
    }

}
