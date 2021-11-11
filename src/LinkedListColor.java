//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * Linked List class is used for traversing a number of elements that are linked to each other with specific colors
 * associated with each value.
 */
public class LinkedListColor {
    int val; //value
    LinkedListColor next; //next item
    int color; //color of value

    /**
     * Constructor for a LinkedList with also a color attribute
     * @param myval first value
     * @param mycolor color of first value
     */
    public LinkedListColor(int myval, int mycolor){
        val = myval;
        next = null;
        color = mycolor;
    }

    /**
     * Add an item with an associated color to the end
     * @param newval first value
     * @param newcolor associated color
     */
    public void add(int newval, int newcolor){
        LinkedListColor temp = this;
        while(temp.next != null){ //iterate till the end
            temp = temp.next;
        }
        temp.next = new LinkedListColor(newval, newcolor); //add them to the end
    }

    /**
     * Creates a string format of the class
     * @return String format of class
     */
    public String toString(){
        LinkedListColor temp = this;
        String str = "";
        while(temp.next != null){ //iterate to the end
            str+=temp.val+" -> "; //add to the string
            temp = temp.next;
        }
        return str+temp.val;

    }
}
