//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Linked List class is used for traversing a number of elements that are linked to each other.
 */
public class LinkedList {
    int val; //value
    LinkedList next; //next item

    /**
     * Constructor for the class
     * @param myval first item of LinkedList
     */
    public LinkedList(int myval){
        val = myval;
        next = null;
    }

    /**
     * Add an item to the end of the linkedList
     * @param newval new value to be added
     */
    public void add(int newval){
        LinkedList temp = this;
        while(temp.next != null){ //iterate till the end
            temp = temp.next;
        }
        temp.next = new LinkedList(newval); //add value
    }

    /**
     * String printer mainly for debugging
     * @return String version of the linkedList
     */
    public String toString(){
        LinkedList temp = this;
        String str = "";
        while(temp.next != null){ //iterate till the end
            str+=temp.val+" -> "; //add to the string for every item
            temp = temp.next;
        }
        return str+temp.val;

    }


}
