/**
 * Christina Vanderwerf
 * CS318 Assignment 4
 * February 11, 2024
 */

public class LinkedListTest {
    public static void main(String args[]){
        LinkedList<String> list = new LinkedList<>();


        list.addAtEnd("M");
        list.addAtEnd("E");
        list.addAtEnd("R");
        list.addAtEnd("G");
        list.addAtEnd("E");
        list.addAtEnd("L");
        list.addAtEnd("I");
        list.addAtEnd("S");
        list.addAtEnd("T");

        list.printList();
        System.out.println();

        LinkedList<String> sortedList = new LinkedList<>();
        sortedList = list.mergeSort(list);
        System.out.print("List after sorting:\n");
        sortedList.printList();





    }
}
