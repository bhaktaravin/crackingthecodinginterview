package chapter2;

import java.util.HashSet;

public class Chapter2Problems {
    
    // Problem 2.1: Remove Dups

    // Remove duplicates from an unsorted linked list
   public void deleteDups(LinkedList n){

    HashSet<Integer> set = new HashSet<>();
    LinkedList previous = null;
    while(n != null){
        if(set.contains(n.data)){
            previous.next = n.next; //remove node
        } else {
            set.add(n.data);
            previous = n;
        }
        n = n.next;
    }
   }

   // No Buffer Solution
   public void deleteDupsNoBuffer(LinkedList head){
        LinkedList current = head;
        while(current != null){
            LinkedList runner = current;
            while(runner.next != null){
                if(runner.next.data == current.data){
                    runner.next = runner.next.next; //remove node
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // Problem 2.2: Return Kth to Last
    // iF LinkedList size is known
    public LinkedList kthToLast(LinkedList head, int k){
        LinkedList p1 = head;
        LinkedList p2 = head;

        // Move p1 k nodes into the list
        for(int i = 0; i < k; i++){
            if(p1 == null) return null; // Out of bounds
            p1 = p1.next;
        }

        // Move both pointers at the same pace
        while(p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2; // p2 is now the kth to last element
    }

    // Recursive approach
    public int kthToLastRecursive(LinkedList head, int k){
      if(head == null) return 0;

      int index = kthToLastRecursive(head.next, k) + 1;
      if(index == k){
          System.out.println(k + "th to last node is " + head.data);
      }
      return index;
    }

    // 2.2 - Create a Wrapper class
    class Index {
        public int value = 0;
    }
    public LinkedList kthToLastWithWrapper(LinkedList head, int k, Index idx){
        if(head == null) return null;

        LinkedList node = kthToLastWithWrapper(head.next, k, idx);
        idx.value = idx.value + 1;
        if(idx.value == k){
            return head;
        }
        return node;
    }

    // 2.2 - C++ Approach
    /*
        node* nthToLast(node* head, int k, int& i) {
            if (head == NULL) {
                return NULL;
            }
            node* n = nthToLast(head->next, k, i);
            i = i + 1;
            if (i == k) {
                return head;
            }
            return n;
        }

        node* nthToLast(node* head, int k) {
            int i = 0;
            return nthToLast(head, k, i);
        }
    */

        // 2.2 - Iterative with Length Calculation
    public LinkedList kthToLastWithLength(LinkedList head, int k){
        int length = 0;
        LinkedList n = head;
        while(n != null){
            length++;
            n = n.next;
        }
        if(k > length) return null; // Out of bounds
        n = head;
        for(int i = 0; i < length - k; i++){
            n = n.next;
        }
        return n;
    }
    
}
