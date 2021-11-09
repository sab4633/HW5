public class LinkedListColor {
    int val;
    LinkedListColor next;
    int color;

    public LinkedListColor(int myval, int mycolor){
        val = myval;
        next = null;
        color = mycolor;
    }

    public void add(int newval, int newcolor){
        LinkedListColor temp = this;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new LinkedListColor(newval, newcolor);
    }

    public int get(int index){
        LinkedListColor temp = this;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }
        return temp.val;
    }

    public String toString(){
        LinkedListColor temp = this;
        String str = "";
        while(temp.next != null){
            str+=temp.val+" -> ";
            temp = temp.next;
        }
        return str+temp.val;

    }



}
