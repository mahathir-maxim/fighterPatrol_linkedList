
/**
 *
 * The Payload class contains the name and area covered by the pilots
 *
 */

import java.lang.Math;
import java.text.DecimalFormat;

public class Payload  implements Comparable<Payload>
{
    private static final DecimalFormat  DF2 = new DecimalFormat("#0.00"); // formats output to two decimal places

    public String name;
    public double area;
    public static boolean compare=false;

    public Payload() // default constructor
    {

    }

    public Payload(String str, double area) // arg constructor eith double argument
    {
        this.name=str;
        this.area=area;
    }

    public Payload(String str) // arg constructor with single argument
    {
        this.name=str;
    }

    public String getName() //getter
    {
        return name;
    }

    public double getArea() //getter
    {
        return area;
    }

    public void setName(String name) //seeter
    {
        this.name = name;
    }

    public void setArea(double area)  //setter
    {
        this.area = area;
    }

    public void setCompare(boolean compare)  //setter
    {
        Payload.compare = compare;
    }

    public boolean getCompare() //getter
    {
        return compare;
    }

    @Override
    public String toString() //overrides the toString method. this method turns the content of the class into a string
    {

        String str= name+"\t"+DF2.format(area)+"\n";
        return str;
    }





    @Override
    public int compareTo(Payload o) //overrides the compareTo method which compares to obects
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
