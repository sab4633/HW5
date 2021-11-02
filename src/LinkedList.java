public class LinkedList {
    int val;
    LinkedList next;

    public LinkedList(int myval){
        val = myval;
        next = null;
    }

    public void add(int newval){
        LinkedList temp = this;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new LinkedList(newval);
    }

    public int get(int index){
        LinkedList temp = this;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }
        return temp.val;
    }

    public String toString(){
        LinkedList temp = this;
        String str = "";
        while(temp.next != null){
            str+=temp.val+" -> ";
            temp = temp.next;
        }
        return str+temp.val;

    }


}
