

/**
 * This program reads in a file containing indefinite number of pilot names and coordinates
 * and another file that contains command of searching by area, name, sorting by pilot name
 * and area in ascending or descending order. This program reads the co-ordinates for each pilot
 * from the first file and calculates the area. and then save those in a linked list.
 * then from the second file it reads the commands and performs the search and sort.
 *
 *
 * main class is the driver class
 */
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.String;
import java.util.Locale;

public class Main
{
    private static final DecimalFormat  DF2 = new DecimalFormat("#0.00"); //formats output to two decimal places
    static public void main (String[] args) throws IOException
    {
        double coordinate[][][]=new double[100][100][2]; // for storing the coordinates
        //ArrayList <double> [][][] coordinate=new ArrayList <>[][][];
        String pilotName[]=new String[20]; // for storing pilot name
        int index[]=new int[20];
        int counter=0;

        Scanner input= new Scanner(System.in); // create scanner
        System.out.println("Enter input file name: "); // prompt for file name
        String fileName= input.nextLine(); // save file name
        File file=new File(fileName); // pass filename and open file to read
        Scanner infile=new Scanner(file); // declare scanner
        //FileWriter outfile= new FileWriter("pilot_areas.txt");
        PrintWriter writer= new PrintWriter("pilot_areas.txt"); // open file to write
        PrintWriter print=new PrintWriter("results.txt");
        PrintWriter arPrint=new PrintWriter("Arrayresults.txt");
        //writer.print("hello world");
        /******************/
         LinkedList<Payload> list= new LinkedList <Payload>();

         /*******************/
         ArrayList <String> sArr;
        while(infile.hasNext()) // check if theres next line
        {
            sArr=GetArray(infile.nextLine()); // call getArray function
            if (sArr.get(0).equals(" "))
            {
                continue;
            }

            StoreArray(sArr, pilotName, coordinate, index, counter); // call StoreArray function
            counter++; // increment counter
        }

        /*******************/
        infile.close(); // close file
        /*******************/
        System.out.println("Enter second input file name: ");
        fileName= input.nextLine();
        file=new File(fileName);
        infile=new Scanner(file);

        for(int x=0;x<counter;x++) // loop to output
        {
            String name=pilotName[x];
            double area=Area(coordinate, index, x); // call area function
            //System.out.printf(name+"\t%.2f\n", area); // print to screen
            //System.out.println(name+" "+area);
            arPrint.printf(name+"\t%.2f\n", area); // print to file
            Payload p= new Payload();
            p.name=name;
            p.area=area;
            list.addLast(p); // add p to linked list

        }
        ///////////////////////////////////////
        /*ArrayList <String> arr=list.showList();
        for (int i=0; i<arr.size(); i++)
        {
            writer.print(arr.get(i));
        }*/
        ///////////////////////////////////////
        //System.out.println(list.size);

        while (infile.hasNext())
        {
            String str= infile.nextLine(); // read the entire line
            //System.out.println("91");
            if (str.contains("sort")) // check to see if it contains sort
            {
                //System.out.println("94");
                String [] sarr= str.split(" "); // split by white shpace
                //System.out.println("command length: "+sarr.length);
                //System.out.println (sarr[0]+" "+sarr[1]+" "+sarr[2]);
                //System.out.println("96");
                if ((sarr.length==3)) // correct csort command will have length three
                {
                    //System.out.println("99");
                    if (sarr[0].equalsIgnoreCase("sort")) // first word will be sort
                    {
                        //System.out.println("102");
                        if(sarr[1].equalsIgnoreCase("area") || sarr[1].equalsIgnoreCase("pilot")) // second word will be pilot or area
                        {
                            //System.out.println("105");
                            String type=sarr[1];
                            //System.out.println("107");
                            if (sarr[2].equalsIgnoreCase("asc") || sarr[2].equalsIgnoreCase("dec")) // thrird area will be asc or dec to mean order typer
                            {
                                //System.out.println("109");
                                String order=sarr[2];
                                sort(list,order,type); // call the sort function with sort ype and order
                                if (sarr[1].equalsIgnoreCase("pilot")) // when sort by pilot
                                {
                                    print.println("Head: "+list.head.data.name+", Tail: "+list.tail.data.name);
                                }
                                if ((sarr[1].equalsIgnoreCase("area"))) // when sort by area
                                {
                                    print.println("Head: "+DF2.format(list.head.data.area)+", Tail: "+DF2.format(list.tail.data.area));
                                }
                                //print.println("Head: "+list.head+", Tail: "+list.tail);
                                //System.out.println("114");
                            }
                            else
                            {
                                //System.out.println("118");
                                continue; // go to the next iteration
                            }

                        }
                        else
                        {
                           // System.out.println("125");
                            continue;// go to the next iteration
                        }

                    }
                    else
                    {
                        //System.out.println("132");
                        continue;// go to the next iteration
                    }


                }
                else
                {
                    //System.out.println("140");
                    continue;// go to the next iteration
                }
            }
            if(!str.contains("sort")) // if command doesnt contain string its a search command
            {
                //String [] s=str.split(" ");
                //if(s.length!=1)
                //{
                   // continue;
               // }
                //System.out.println(str);
                //System.out.println(162);
                if (isNumeric(str)) // check to see if str is properly numeric
                {
                    //System.out.println(162);
                    Double digit= Double.valueOf(str); // static cast string to double
                    ArrayList <String> arrL=search(list,digit); // call the search function with list and digit to search

                    if (arrL.size()==0) // when digit not found
                    {
                        //System.out.println(171);
                        print.println(str+" not found");
                    }
                    else
                    {
                       // System.out.println(176);
                        //System.out.println("["+arrL.get(0)+"]");
                        //System.out.println("["+arrL.get(1)+"]");
                        //Double num2= Double.parseDouble(arr.get(1));

                        // when the searh is successfull
                        String str1=arrL.get(1); // first content is the name
                        String str2=arrL.get(0); //second content is the area

                        Double num=Double.parseDouble(str1);
                        print.println(arrL.get(0)+" "+DF2.format(num)); // print to file
                    }
                }
                /*else
                {
                    Double digit= Double.valueOf(str);
                    ArrayList <String> arrL=search(list,digit);
                    if (arrL.size()==0)
                    {
                        print.println(str+" not found\n");
                    }
                    else
                    {
                        System.out.println("["+arrL.get(0)+"]");
                        System.out.println("["+arrL.get(1)+"]");
                        //Double num2= Double.parseDouble(arr.get(1));
                        String str1=arrL.get(1);
                        Double num=Double.parseDouble(str1);
                        print.println(arrL.get(0)+" "+DF2.format(num));
                    }

                }*/
                else // when the search item is a name
                {
                    System.out.println(208);
                    String [] forN= str.split(" "); // split by whitespace
                    int loopV=0;

                    // check for invalid cases
                    for (int i=0; i<forN.length; i++)
                    {
                        if(!checkName(forN[i])) // if the name is not in correct format go to the next iteration
                        {
                            System.out.println(215);
                            //continue;
                            loopV=-1;
                        }
                    }
                    if(loopV==-1)
                    {
                        System.out.println(223);
                        continue;
                    }

                    ArrayList <String> arrL=search(list,str);
                    if (arrL.size()==0) // when search not found
                    {
                        System.out.println(224);
                        print.println(str+" not found");
                    }
                    else
                    {
                        //Double num1= Double.parseDouble(arr.get(1));
                        //System.out.println("["+arrL.get(0)+"]");
                        //System.out.println("["+arrL.get(1)+"]");
                        String str1=arrL.get(1);
                        Double num=Double.parseDouble(str1);
                       // System.out.println(234);
                        print.println(arrL.get(0)+" "+DF2.format(num)); // print to file
                    }
                }

            }
        }
        //String [] toShow== new String [list.size];
        ArrayList <String> arr=list.showList();
        for (int i=0; i<arr.size(); i++)
        {
            writer.print(arr.get(i));
        }
        String q=list.toString();
        System.out.println("=======================================");
        System.out.println(q);
        System.out.println("=======================================");
        /***
        System.out.println("============================================================");
        sort(list,"asc","name");
        list.showList();
        System.out.println(list.head.data.name+" is head name");
        System.out.println(list.tail.data.name+" is tail name");
        System.out.println("============================================================");
        System.out.println("============================================================");
        sort(list,"asc","area");
        list.showList();
        System.out.println(list.head.data.area+" is head area");
        System.out.println(list.tail.data.area+" is tail area");
        System.out.println("============================================================");
        System.out.println("============================================================");
        sort(list,"dec","name");
        list.showList();
        System.out.println(list.head.data.name+" is head name");
        System.out.println(list.tail.data.name+" is tail name");
        System.out.println("============================================================");
        System.out.println("============================================================");
        sort(list,"dec","area");
        list.showList();
        System.out.println(list.head.data.area+" is head area");
        System.out.println(list.tail.data.area+" is tail area");
        System.out.println("============================================================");

        ****/

        // close the input and output files
        infile.close();
        arPrint.close();
        print.close();
        writer.close();
    }

    //This method takes a String and returns a string arraylist and checks invalid inputs
    public static ArrayList <String> /*String[]*/  GetArray(String Arr)
    {
        //return Arr.split("[,\\s]");
        String [] str= Arr.split("\\s+"); // spilit by whitespace
        for (int j=0; j<str.length; j++)
        {
            //System.out.println(str[j]);
        }
        ArrayList <String> Alist= new ArrayList <> ();
        ArrayList <String> error=new ArrayList<> ();
        error.add(" "); //this arrayList will be returned if theres any invalid character in the string
        int i=0;
        //System.out.println("101");
        if ((str[0].charAt(0)>=65 && str[0].charAt(0)<=90) || (str[0].charAt(0)>=97 && str[0].charAt(0)<=122)) // name can only contain certain characters check that
        {
            //System.out.println("102");
            while ((str[i].charAt(0)>=65 && str[i].charAt(0)<=90) || (str[i].charAt(0)>=97 && str[i].charAt(0)<=122)) // name can only contain certain characters check that
            {
                //System.out.println("index= "+i);
                //System.out.println("103");
                //System.out.println(str[i]);
                if (!(checkName(str[i]))) // check if name is valid
                {
                   // System.out.println("baal");
                    return error; // if invalid return error list
                }
                else  // if correct name
                {
                    //System.out.println("eikhane dhuksi");
                    if (i==0)
                    {
                        //Alist.set(0,str[i]);
                        //System.out.println("257 "+str[i]);
                        Alist.add(str[i]); //add the name to the linked list
                    }
                    if (i>0)
                    {
                        //System.out.println("257 "+str[i]);
                        String x=Alist.get(0);
                        x+=" "+str[i];
                        Alist.set(0, x); // if there are multiple parts of the name add those to the same node
                    }

                    //i++;
                }
                i++; // increment

            }
        }
        else
        {
            //System.out.println("104");
            return error;
        }

        if ((str[i].charAt(0)>=48 && str[i].charAt(0)<=57) || str[i].charAt(0)==45 || str[i].charAt(0)==46) // valid numbers can contain certain characters
        {
            //System.out.println("105");
            for (int x=i; x<str.length; x++ )
            {
              // System.out.println("284 "+str[x]);
               if (arrayNumberFormat(str[x]))
                {
                   // System.out.println("288 "+str[x].length());
                    //System.out.println(str[x]);
                    int comma= str[x].indexOf(",");
                    //System.out.println("291 "+comma);
                    int size=str[x].length();
                    String first=str[x].substring(0,comma);
                    String second=str[x].substring(comma+1,size);
                    //System.out.println(first);
                    Alist.add(first);
                    //System.out.println("297 ["+second+"]");
                    Alist.add(second); // add to the end of the list
                }
               else
               {
                  // System.out.println("108");
                   return error;// when contains invalid character
               }
            }

        }
        else
        {
            //System.out.println("109");
            return error; // when contains invalid character
        }

        //System.out.println("This is 310 "+Alist);
        return Alist; // return the arrayList

    }



    // Area function declared to caluclate area passing the coordinates and counters
    static double Area(double coordinate[][][], int index[], int counter) //
    {

        double areaMeasure=0;

        for(int x=1; x<index[counter]; x++)
        {
           /* System.out.println(coordinate[counter][x][0]);
            System.out.println(coordinate[counter][x-1][0]);
            System.out.println(coordinate[counter][x][1]);
            System.out.println(coordinate[counter][x-1][1]);*/
            areaMeasure+=((coordinate[counter][x][0]+coordinate[counter][x-1][0])*(coordinate[counter][x][1]-coordinate[counter][x-1][1]));
        }

        areaMeasure=Math.abs(areaMeasure)/2; // divide the initial area by 2 to get the actual area
        //System.out.println(areaMeasure);
        return areaMeasure;

    }
    /****
    static String search(String str)
    {
        if (str.equals("139.32"))

            return "not found";
        else
            return "found";
    }

    /****
    static void printfile(String str, PrintWriter print)
    {
        if (str.equals("1731.9549999999995"))
            print.println("Malakili 1731.95 "+search(str));
        else if (str.equals("Boshek"))
                print.println("Boshek 133.70 "+search(str));
        else
            print.println(str+" "+search(str));
    ****/



    // StoreArray takes in the string lines and returns double value for the coordinates and stores them in a three dimensional array
    static void StoreArray(ArrayList <String> Arr, String pilotName[], double coordinate[][][], int index[], int counter)
    {

        int num=0;
        //String [] Arr=Arrl.toArray();
        pilotName[counter]=Arr.get(0);
        for(int i=1; i<Arr.size(); i+=2)// loop to store in a three dimensional array
        {
            //System.out.println(Double.parseDouble(Arr.get(i)));
            //System.out.println(Double.parseDouble(Arr.get(i)));
            coordinate[counter][num][0]=Double.parseDouble(Arr.get(i));
            coordinate[counter][num][1]=Double.parseDouble(Arr.get(i+1));
            num++;
        }
        index[counter]=num;
    }

    // search funcion searchs the linked list by name and area both in ascending and descending order
    static ArrayList<String> search(LinkedList<Payload> list, Object value)
    {
        //System.out.println(value);
        ArrayList<String> arr= new ArrayList<>();
        Node<Payload> current= list.head;
        //System.out.println("inside search and Arraylist size is="+arr.size());

        if (value instanceof Double)
        {
            Double d= (double) value;
            //System.out.println(d);
            while(current!=null)
            {
                double dif=Math.abs(d-current.data.area);
                //System.out.println(dif);
                if (dif< .0099)
                {
                    Double x= (Double)current.data.area;
                    String s=x.toString();
                    arr.add(current.data.name);
                    arr.add(s);
                    break;
                }
                current=current.next;
            }

            //System.out.println("quitting double search and Arraylist size is="+arr.size());
            return arr;

        }
        //if (value instanceof String)
        else
        {
            String str= (String)value;
            while(current!=null)
            {
                String str2=current.data.name;
                if (str.equals(str2))
                {
                    arr.add(current.data.name);
                    Double temp= current.data.area;
                    String dStr=temp.toString();
                    arr.add(dStr);
                    break;
                }
                current=current.next;
            }
            //System.out.println("quitting String search and Arraylist size is="+arr.size());
            return arr;
        }

    }

    // This function checks if a string is numeric
    public static boolean isNumeric(String str)
    {
        int size=str.length();
        if (str.charAt(size-1)==45 || str.charAt(size-1)==46) // check if contains invalid chars
        {
            return false;
        }
        try
        {
             Double.parseDouble(str);  //parse string to double
             return true;
        }
        catch(NumberFormatException e) // when theres invalid characters in parse double
        {
            return false;
        }
    }

    // This function sorts the linked list by name or area in ascending or desceding order
    // implemetns bubble sort on singly linked list
    public static void sort (LinkedList<Payload> list, String str, String value)
    {

        if (str.equalsIgnoreCase("asc")) //  sort by ascending order
        {
            if (value.equalsIgnoreCase("area")) // sort according to area
            {
                if (list.size > 1)
                {

                    //
                    boolean listChanged;
                    do
                    {
                        Node <Payload> current=list.head;
                        Node <Payload> previous= null;
                        Node <Payload> nextN=list.head.next;
                        listChanged=false;

                        while (nextN!=null)
                        {
                            if (current.data.area>nextN.data.area)
                            {
                                listChanged=true;

                                if (previous != null)
                                {
                                    Node <Payload> sig= nextN.next;

                                    previous.next=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }
                                else
                                {
                                    Node <Payload> sig= nextN.next;
                                    list.head=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }

                                 previous= nextN;
                                 nextN= current.next;
                            }
                            else
                            {
                                previous=current;
                                current=nextN;
                                nextN=nextN.next;
                            }
                        }
                        /******/
                        list.tail=current;
                        /******/
                    }while (listChanged);
                }
            }
            else // sort by name
            {
                if (list.size > 1)
                {

                    boolean listChanged;
                    do
                    {
                        Node <Payload> current=list.head;
                        Node <Payload> previous= null;
                        Node <Payload> nextN=list.head.next;
                        listChanged=false;

                        while (nextN!=null)
                        {
                            int compval=current.data.name.compareToIgnoreCase(nextN.data.name);
                            /*current.data.area>nextN.data.area*/
                            if (compval>0)
                            {
                                listChanged=true;

                                if (previous != null)
                                {
                                    Node <Payload> sig= nextN.next;

                                    previous.next=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }
                                else
                                {
                                    Node <Payload> sig= nextN.next;
                                    list.head=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }

                                 previous= nextN;
                                 nextN= current.next;
                            }
                            else
                            {
                                previous=current;
                                current=nextN;
                                nextN=nextN.next;
                            }
                        }
                        /******/
                        list.tail=current;
                        /******/
                    }while (listChanged);
                }
            }

        }
        else
            if (str.equalsIgnoreCase("dec"))  // sort in descending order
        {
            if (value.equalsIgnoreCase("area")) //sprt by area
            {
                if (list.size > 1)
                {

                    boolean listChanged;
                    do
                    {
                        Node <Payload> current=list.head;
                        Node <Payload> previous= null;
                        Node <Payload> nextN=list.head.next;
                        listChanged=false;

                        while (nextN!=null)
                        {
                            if (current.data.area<nextN.data.area)
                            {
                                listChanged=true;

                                if (previous != null)
                                {
                                    Node <Payload> sig= nextN.next;

                                    previous.next=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }
                                else
                                {
                                    Node <Payload> sig= nextN.next;
                                    list.head=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }

                                 previous= nextN;
                                 nextN= current.next;
                            }
                            else
                            {
                                previous=current;
                                current=nextN;
                                nextN=nextN.next;
                            }
                        }
                        /******/
                        list.tail=current;
                        /******/
                    }while (listChanged);
                }
            }
            else // sort by name
            {
                if (list.size > 1)
                {

                    boolean listChanged;
                    do
                    {
                        Node <Payload> current=list.head;
                        Node <Payload> previous= null;
                        Node <Payload> nextN=list.head.next;
                        listChanged=false;

                        while (nextN!=null)
                        {
                            int compval=current.data.name.compareToIgnoreCase(nextN.data.name);
                            /*current.data.area>nextN.data.area*/
                            if (compval<0)
                            {
                                listChanged=true;

                                if (previous != null)
                                {
                                    Node <Payload> sig= nextN.next;

                                    previous.next=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }
                                else
                                {
                                    Node <Payload> sig= nextN.next;
                                    list.head=nextN;
                                    nextN.next=current;
                                    current.next=sig;
                                }

                                 previous= nextN;
                                 nextN= current.next;
                            }
                            else
                            {
                                previous=current;
                                current=nextN;
                                nextN=nextN.next;
                            }
                        }
                        /******/
                        list.tail=current;
                        /******/
                    }while (listChanged);
                }
            }

        }

    }

    // This functon checks the number format of the coordinates for invalid format
    public static boolean arrayNumberFormat(String str)
    {
        ////////////
        //comma validation
        ///////////
        int size=str.length();
        for (int i=0; i<str.length(); i++) // check if the number has any invalid character
        {
            if (!((str.charAt(i)>=48 && str.charAt(i)<=57) || (str.charAt(i)>=44 &&str.charAt(i)<=46)))
            {
               // System.out.println("1");
                return false;
            }
        }
        if (!str.contains(",")) // check to see that number has one comma
        {
            //System.out.println("2");
            return false;
        }
        int comma=str.indexOf(",");
        //System.out.println(comma+" and size "+size);
        if (str.charAt(size-1)==44) // so last letter is not a comma
        {
            //System.out.println("3");
            return false;
        }
        if (!(str.charAt(comma-1)>=48 && str.charAt(comma-1)<=57)) // so letter before string is a number
        {
            //System.out.println("4");
            return false;
        }
        // check to maek sure that the letter after comma is a number or a dot or a minus sign
        if (!((str.charAt(comma+1)>=48 && str.charAt(comma+1)<=57) || str.charAt(comma+1)==45 || str.charAt(comma+1)==46))
        {
            //System.out.println("5");
            return false;
        }

        String temp=str.substring(comma+1);
        if (temp.contains(",")) // so that string doesnt have two commas
        {
            //System.out.println("6");
            return false;
        }

        //////////
        //dot validation
        /////////

        String first=str.substring(0, comma);
        String second= str.substring(comma+1,size);

        if (first.contains("."))
        {
            boolean value= checkDot(first);
            if (value!=true)
            {
                //System.out.println("7");
                return false;
            }
        }
        if (second.contains("."))
        {
            boolean value= checkDot(second);
            if (value!=true)
            {
                //System.out.println("8");
                return false;
            }
        }
        if(first.contains("-"))
        {
            int minus=first.indexOf("-");
            String sub=first.substring(minus+1);
            if (sub.contains("-"))
            {
                //System.out.println("9");
                return false;
            }
           if (first.charAt(0)!=45)
           {
               //System.out.println("10");
               return false;
           }
        }
        if(second.contains("-"))
        {
            int minus=second.indexOf("-");
            String sub=second.substring(minus+1);
            if (sub.contains("-"))
            {
                //System.out.println("11");
                return false;
            }
           if (second.charAt(0)!=45)
           {
               //System.out.println("12");
               return false;
           }
        }
      return true;
    }

    // This function checks if the pattern before and after dot is correct
    public static boolean checkDot(String str)
    {
        int size=str.length();
        int dot=str.indexOf(".");
        String temp=str.substring(dot+1);
        if (temp.contains(".")) // to see if theres two dots in the number
        {
            //System.out.println("1");
            return false;
        }
        if (dot==0) // to see if dot is in the end then the next char is a digit
        {
            if (!(str.charAt(dot+1)>=48 && str.charAt(dot+1)<=57))
            {
               // System.out.println("2");
                return false;
            }
        }
        else if (dot==size-1) // dot cant be the last char
        {
            //System.out.println("3");
            return false;
        }
        else // if dots in middle both the chars after and before dot has to be number
        {
            if (!((str.charAt(dot-1)>=48 && str.charAt(dot-1)<=57) || str.charAt(dot-1)==45))
            {
               // System.out.println("4");
                return false;
            }
            if(!(str.charAt(dot+1)>=48 && str.charAt(dot+1)<=57))
            {
                //System.out.println("5");
                return false;
            }
        }
        return true;
    }

    // This function checks name strings for invalid characters
    public static boolean checkName(String s)
    {
       // System.out.println("dhuksi");
        int size=s.length();
        String str=s.toLowerCase();
        for (int i=0; i<size; i++)
        {
            // check if theres any invalid charactaters in the names
           if(!((str.charAt(i)>=97 && str.charAt(i)<=122) || (str.charAt(i)>=48 && str.charAt(i)<=57) || str.charAt(i)==45 || str.charAt(i)==39 ))
           {
               return false;
           }
        }
        return true;
    }


}
