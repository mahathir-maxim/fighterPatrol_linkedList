/*
 *Copyrighted by @maxim
 */

/**
 *Singly linked Linked List class. Its a generic class.
 */

import java.util.ArrayList;


public class LinkedList<T extends Comparable<T>>
{
    public Node<T> head=null;   // for the head node
    public Node<T> tail=null;   // for the tail node
    public int size=0;          // will keep track of the size of the list

    public LinkedList()     //default constuctor
    {

    }

    public LinkedList(Node<T> e)    // arg constuctor
    {
        head=e;
        tail=e;
        size++;
    }

    public Node<T> getHead()    //getter
    {
        return this.head;
    }

    public Node<T> getTail()       //getter
    {
        return this.tail;
    }

    public void setHead(Node<T> e)      // setter
    {
        this.head=e;
    }

    public void setTail(Node <T> e)     //setter
    {
        this.tail=e;
    }

    public void addLast(T obj)      //this function adds node to the end of the linked list
    {
        //System.out.println("Before adding node size="+size);
        Node <T> node= new Node <>();
        node.data=obj;
        Node <T> current= this.head;
        if (this.head==null)
        {
            this.head=node;
            this.head.next=null;
            this.tail=node;
            this.tail.next=null;
            this.size++;
        }
        else
        {
            while (current.next!=null)
            {
                current=current.next;
            }
            current.next=node;
            node.next=null;
            this.tail=node;
            this.size++;
        }
        //System.out.println("After adding node size="+size);


    }

    @Override
    public String toString()    //this function turns the content of the linked list to a string
    {

        Node<T> node=head;
        String str=node.toString();
        node=node.next;
        while (node!=null)
        {
            str+=node.toString();
            node=node.next;
        }
       return str;
    }


    public ArrayList <String> showList()    //This function turns the content of the list into an String ArrayList
    {

        ArrayList <String> arr= new ArrayList<>();

        Node<T> node= head;
        while(node!=null)
        {

            String x= node.toString();
            //System.out.print(x);
            arr.add(x);
            node=node.next;
        }
        return arr;
    }

}
