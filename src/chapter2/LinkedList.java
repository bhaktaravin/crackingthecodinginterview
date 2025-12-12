package chapter2;

public class LinkedList {
    int data;
    LinkedList next;
    
    public LinkedList(int data) {
        this.data = data;
        this.next = null;
    }
    
    // Helper method to append to end of list
    public void appendToTail(int d) {
        LinkedList end = new LinkedList(d);
        LinkedList n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
    
    // Helper method to create list from array
    public static LinkedList createList(int[] values) {
        if (values == null || values.length == 0) return null;
        
        LinkedList head = new LinkedList(values[0]);
        LinkedList current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new LinkedList(values[i]);
            current = current.next;
        }
        return head;
    }
    
    // Helper method to convert list to string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedList n = this;
        while (n != null) {
            sb.append(n.data);
            if (n.next != null) sb.append(" -> ");
            n = n.next;
        }
        return sb.toString();
    }
}
