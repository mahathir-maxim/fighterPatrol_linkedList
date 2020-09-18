
/**
 *Node class are the containers that contain the information in linked list.
 *
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>  // extends comparable and implements comparable
{

    public T data;
    /*********************/
    public Node<T> next;
    public Node<T> prev;


    @Override
    public int compareTo(Node<T> o) //overrides the compareTo class that compares between two obejcts
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*********************/
    public Node() //default constructor
    {

    }
    public Node (T object) // constuctor
    {
        data=object;
    }

    public void setData(T object) //setter
    {
        this.data=object;
    }

    public void setNext(Node<T> object) //setter
    {
        this.next=object;
    }


    public void setPrev(Node<T> object) //setter
    {
        this.prev=object;
    }

    public T getData () //getter
    {
        return data;
    }

    public Node<T> getNext () //getter
    {
        return next;
    }

    public Node<T> getPrev () //getter
    {
        return prev;
    }

    @Override
    public String toString()  //This overrides the toString method which transfers the contents of the node class into a string
    {
        String str= data.toString();
        return str;
    }


}
