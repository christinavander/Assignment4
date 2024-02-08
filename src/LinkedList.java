import java.util.Comparator;
import java.util.Iterator;

/**
 * Christina Vanderwerf
 * CS318 Assignment 4
 * February 11, 2024
 */

public class LinkedList<Item> {
    private class DoubleNode implements Comparator<Item> {
        Item item;
        DoubleNode next;
        DoubleNode previous;

        /**
         * Default Constructor
         */
        DoubleNode() {
        }

        /**
         * Constructor creates DoubleNode with just item value
         */
        DoubleNode(Item item) {
            this.item = item;
            next = null;
            previous = null;
        }

        /**
         * Constructor creates DoubleNode and loads item, next, and previous
         */
        DoubleNode(Item item, DoubleNode next, DoubleNode previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }


        @Override
        public int compare(Item item1, Item item2) {
            String firstItem = (String)item1;
            String secondItem = (String)item2;

            return (firstItem.compareTo(secondItem));
            //negative if item 1 is less than item2
            //0 if item 1 is the same as item2
            //positive if item 1 is more than item2

        }


        private DoubleNode getTail(){
            return tail;
        }

    }//end DoubleNode

    private int count = 0;
    private DoubleNode head;
    private DoubleNode tail;

    /**
     * Constructor creates new empty LinkedList
     */
    public LinkedList() {
        count = 0;
        head = null;
        tail = null;
    }

    /**
     * Finds the end of the first sorted subarray
     *
     */
    private DoubleNode findSortedEnd(DoubleNode head){
        DoubleNode sortedEnd = new DoubleNode();

        for(DoubleNode current = head; current != null; current = current.next){
            if(current == current.getTail()){
                sortedEnd = current;
                break;
            }
           else if (current.compare(current.item, current.next.item) > 0){ //If the first item is greater than the second, this is the end of the sorted subarray
               sortedEnd = current;
               break;
           }
        }
        return sortedEnd;
    }


    /**
     * MergeSort sorts a linked list based on naturally occurring sorted sub arrays
     */
    public LinkedList mergeSort(LinkedList list){

        DoubleNode ptr1 = new DoubleNode();
        DoubleNode start = list.getHead();
        ptr1 = findSortedEnd(start);

        DoubleNode ptr2 = new DoubleNode();
        ptr2 = ptr1.next; //List without first sorted sub array

        DoubleNode cont = new DoubleNode();

        cont = findSortedEnd(ptr2); //Second sorted subarray end

        LinkedList finalHead = new LinkedList<>();
        finalHead = merge(start, ptr1, ptr2, cont);


        if(cont.next == null){
            return finalHead;
        }
        else{
            DoubleNode remainder = cont.next;
            while(remainder != null){
                finalHead.addAtEnd(remainder.item);
                remainder = remainder.next;
            }
            return mergeSort(finalHead);
       }
    }

    /**
     * Merge two heads of Nodes
     */
    private LinkedList merge(DoubleNode head1Start, DoubleNode head1End, DoubleNode head2Start, DoubleNode head2End){
        LinkedList<Item> mergedList = new LinkedList<>();

        while(head1Start != head1End.next && head2Start != null && head2Start != head2End.next){
            if (head1Start.compare(head1Start.item, head2Start.item) < 0) { //If first item is smaller
                    mergedList.addAtEnd(head1Start.item);
                    head1Start = head1Start.next;
            }
            else if (head1Start.compare(head1Start.item, head2Start.item) == 0){ //If the items are equal, add both to the list
                mergedList.addAtEnd(head1Start.item);
                mergedList.addAtEnd(head2Start.item);
                head1Start = head1Start.next;
                head2Start = head2Start.next;
            }
            else if (head1Start.compare(head1Start.item, head2Start.item) > 0){ //The second item is smaller
                    mergedList.addAtEnd(head2Start.item);
                    head2Start = head2Start.next;
            }
        }


        if (head1Start == head1End.next) { //If first sorted sub array is empty
            if(head2Start == head2End) {
                mergedList.addAtEnd(head2Start.item);
            }
            else{
                while(head2Start != head2End.next){
                    mergedList.addAtEnd(head2Start.item);
                    head2Start = head2Start.next;
                }
            }
        }

        if(head2Start == null || head2Start == head2End.next ){ //Add the rest of head1 into list
            if(head1Start == head1End){
                mergedList.addAtEnd(head1Start.item);
            }
            else if(head2Start != head1End){
                while(head1Start != head1End.next){
                    mergedList.addAtEnd(head1Start.item);
                    head1Start = head1Start.next;
                }
            }
        }

            return mergedList;

    }



    private DoubleNode getHead(){
        return head;
    }

    private int getCount(){
        return count;
    }


    /**
     * Returns index of first occurrence of item in list
     */
    private int indexOf(Item item){
        count = 0;
        for(DoubleNode current = head; current != null; current = current.next){

            if (current.item == item){
                break;
            }

            count++;
        }
        return count;
    }

    /**
     * Adds a node to an empty list
     */
    private void addToEmptyList(Item item) {
        DoubleNode newNode = new DoubleNode(item);
        tail = newNode;
        head = newNode;
        head.next = tail;
        tail.previous = head;
        count++;

    }

    /**
     * Print contents of list
     */
    public void printList() {
        for (DoubleNode x = head; x != null; x = x.next) {
            System.out.println(x.item);
        }
    }

    /**
     * Insert a DoubleNode at the beginning of the list
     */
    public void addAtBeginning(Item item) {
        DoubleNode newNode = new DoubleNode(item);

        //if list is empty
        if (head == null) {
            addToEmptyList(item);
        }
        else if(count == 1){
            head = newNode;
            newNode.next = tail;
            tail.previous = newNode;
            tail.next = null;
            head.previous = null;
        }
        //if list is not empty
        else {
            DoubleNode temp = head;
            head = newNode;
            head.next = temp;
            temp.previous = head;
            count++;

        }
    }

    /**
     * Insert a DoubleNode at the end of the list
     */
    public void addAtEnd(Item item) {
        DoubleNode newNode = new DoubleNode(item);

        //if list is empty
        if (count == 0) {
            addToEmptyList(item);
        }
        //if list is not empty
        else {
            DoubleNode temp = new DoubleNode();
            temp = tail;
            tail = newNode;
            temp.next = tail;
            tail.previous = temp;
            count++;
        }

    }

    /**
     * Insert before a give node (Insert before the first occurrence of the
     * node, if the node exists; else insert at the end)
     */
    public void addBefore(Item itemToFind, Item itemToAdd) {
        DoubleNode newNode = new DoubleNode(itemToAdd);
        boolean found = false;

        //if list is empty
        if (count == 0) {
            addToEmptyList(itemToAdd);
        }

        //find occurrence of Node
        for (DoubleNode x = head; x != null; x = x.next) {
            if (x.item == itemToFind) {
                //if itemToFind is at the beginning of the list
                if (x == head) {
                    found = true;
                    addAtBeginning(itemToAdd);
                    break;
                }

                //if itemToFind is at the end of the list
                if (x == tail) {
                    count++;
                    found = true;
                    newNode.next = tail;
                    newNode.previous = tail.previous;
                    tail.previous.next = newNode;
                    tail.previous = newNode;

                }
                //itemToFind is somewhere in the middle
                else {
                    count++;
                    found = true;
                    DoubleNode temp = newNode;
                    temp.next = x;
                    temp.previous = x.previous;
                    x.previous.next = temp;
                    x.previous = temp;
                    break;
                }
            }
        }

        //if item is not found in the list, add to the end of the list
        if (!found) {
            addAtEnd(itemToAdd);
        }
    }

    /**
     * Insert after a given node (Insert after the first occurrence of the
     * node, if the node exists; else insert at the end)
     */
    public void addAfter(Item itemToFind, Item itemToAdd) {

        DoubleNode newNode = new DoubleNode(itemToAdd);
        boolean found = false;
        //Search for the itemToFind
        for (DoubleNode x = head; x != null; x = x.next) {
            if (x.item == itemToFind) {
                //if itemToFind is the tail
                if (x == tail) {
                    found = true;
                    addAtEnd(itemToAdd);
                    break;
                }
                //if itemToFind is the head
                else if (x == head) {
                    found = true;
                    count++;
                    head.next.previous = newNode;
                    newNode.next = head.next;
                    newNode.previous = head;
                    head.next = newNode;
                    newNode = head;
                    break;
                } else { //itemToFind is somewhere in the middle
                    count++;
                    found = true;
                    x.next.previous = newNode;
                    newNode.next = x.next;
                    x.next = newNode;
                    newNode.previous = x;
                    break;
                }

            }
        }

        if (!found){
            addAtEnd(itemToFind);
        }
    }

    /**
     * Remove beginning node in the list
     */
    public void removeFirst(){

        try {
            //If list is empty
            if (count == 0) {
                throw new Exception("Can't remove node from empty list.");
            }

            //If there is only one item in the list
            else if (count == 1) {
                head = null;
                tail = null;
                count--;
            } else {
                DoubleNode temp = new DoubleNode();
                temp = head.next;
                head = temp;
                count--;
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Remove last node in the list
     */
    public void removeLast(){
        try {
            //If list is empty
            if (count == 0) {
                throw new Exception("Can't remove node from empty list.");
            }

            //If only one item in list
            else if (count == 1) {
                head = null;
                tail = null;
                count--;
            } else {
                DoubleNode temp = new DoubleNode();
                temp = tail;
                tail = tail.previous;
                tail.next = null;
                temp = null;
                count--;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Remove a given node (Remove the first occurrence of the node,
     * remove nothing if node not found)
     */
    public void removeItem(Item itemToRemove){
        boolean found = false;
        try{
            if (count == 0){
                throw new Exception("Can't remove an item from an empty list.");
            }
            else {
                //Search for item to remove
                for (DoubleNode x = head; x != null; x = x.next) {
                    if (x.item == itemToRemove) {

                        //if itemToRemove is the head
                        if(x == head){
                            found = true;
                            removeFirst();
                            break;
                        }
                        //If itemToRemove is the tail
                        else if(x == tail){
                            found = true;
                            removeLast();
                            break;
                        }
                        //If itemToRemove is somewhere in the middle
                        else{
                            found = true;
                            x.previous.next = x.next;
                            x.next.previous = x.previous;
                            count--;
                            break;
                        }
                    }
                }
            }
            if (!found){
                return;
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Move to front (move the first occurrence of the node to the front),
     * if not found, do nothing
     */
    public void moveToFront(Item itemToMove){
        try{
            if (count == 0){
                throw new Exception("Can't move anything in an empty list.");
            }
            else if(count == 1) { //if head and tail are the same
                //don't move anything
                return;
            }
            else{
                for (DoubleNode x = head; x != null; x = x.next) {
                    if (x.item == itemToMove) {
                        removeItem(itemToMove);
                        addAtBeginning(itemToMove);
                        break;
                    }
                }

            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Move to end (moved and first occurrence of the node to the end),
     * if not found, do nothing
     */
    public void moveToEnd(Item itemToMove){
        try{
            if (count == 0){
                throw new Exception("Can't move anything in an empty list.");
            }
            else if(count == 1) { //if head and tail are the same
                //don't move anything
                return;
            }
            else{
                for (DoubleNode x = head; x != null; x = x.next) {
                    if (x.item == itemToMove) {
                        removeItem(itemToMove);
                        addAtEnd(itemToMove);
                        break;
                    }
                }

            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
