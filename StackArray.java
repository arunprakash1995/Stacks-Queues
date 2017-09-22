package cs6301.g23;
//SP2- Problem 6

/**
 * This programs implements array-based, bounded-sized stack.
 *Functionalities implemented: push, pop, peek, isEmpty.
 *
 * @author Arun Prakash Themothy Prabhu Vincent
 * @author Akshaya Udayakumar
 * @author Radhika Kalaiselvan
 *
 * Version 1.0 - 9/4/17
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import cs6301.g00.*;

public class StackArray<T> implements Iterable<T> {
    T[] stack; // stack array of type T
    int size;  // size of the stack
    int current; //tail end index of the stack array
    int top; //
    
    /**
     * Constructor for StackArray
     * @param size
     *              : int - size of the stack
     */
    public StackArray(int size){
        this.size = size;
        this.stack = (T[]) new Object[size];
        this.current= -1;
    }
    
    //Method to create iterator for the stack
    public Iterator<T> iterator(){
        return new ArrayIterator<T>(stack,0,current);
    }
    
    /**
     * Method to add element to the tail end of the stack
     * Throws an exception is the stack is full
     *
     * @param element
     *               : T - element to be pushed
     */
    public void push(T element) throws StackOverFlowException{
        if(current>=size-1){ //checking if the stack is full
            throw new StackOverFlowException();
        }
        stack[++current] = element;
    }
    
    // Method to remove element from the tail end of the stack
    public T pop(){
        if(current<0){// checking if the stack is empty
            return null;
        }
        return stack[current--];
    }
    
    // Method to peek the element from the tail end of the stack
    public T peek(){
        if(current<0){ // chcking if the stack is empty
            return null;
        }
        return stack[current];
    }
    
    /* Method to check if the stack is empty
     * returns boolean: true or false
     */
    public boolean isEmpty(){
        if(current<0){
            return true;
        }
        return false;
    }
    
    //Method to print the stack
    public void printstack(){
        System.out.print( this.size + ": ");
        int cur=0;
        for(T element: this){
            System.out.print(element + " ");
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        int num, del, Size;
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        }
        else {
            in = new Scanner(System.in);
        }
        int size = in.nextInt();
        StackArray<Integer> st = new StackArray<>(size);
        int con =1;
        while(con == 1){
            System.out.print("Enter Choice : ");
            int choice = in.nextInt();
            switch(choice){
                case 1: int n = in.nextInt();
                    for(int i=1; i<=n; i++) {
                        try{
                            int value = in.nextInt();
                            st.push(value);
                        }
                        catch(StackOverFlowException s){
                            System.out.println(s.getMessage()); // Displaying the generated exception message
                        }
                    }
                    st.printstack();
                    break;
                    
                case 2: System.out.println("\npeek : " + st.peek());
                    break;
                    
                case 3: int delete = in.nextInt();
                    for(int i=1; i<=delete; i++) {
                        st.pop();
                    }
                    st.printstack();
                    break;
                default : System.out.println("Invalid choice");
            }
            System.out.println("\nEnter 1 to continue : ");
            con = in.nextInt();
        }
    }
    
}

/**
 Enter Size
 Enter Choice
    choice-1: To add elements
        Enter number of elements to be added
        Enter elements
    choice-2: To peek the rear element
    choice-3: To remove elements
        Enter number of elements to be removed
     
 Sample output:
 8 - (Size of the stack)
 Enter Choice : 1
 8 - (number of elements to be added)
 1
 2
 3
 4
 5
 6
 7
 8
 8: 1 2 3 4 5 6 7 8
 
 Enter 1 to continue :
 1
 Enter Choice : 2
 
 peek : 8
 
 Enter 1 to continue :
 1
 Enter Choice : 3
 2 - (number of elements to delete)
 8: 1 2 3 4 5 6
 
 Enter 1 to continue :
 1
 Enter Choice : 2
 
 peek : 6
 
 Enter 1 to continue :
 1
 Enter Choice : 1
 2
 7
 8
 8: 1 2 3 4 5 6 7 8
 
 Enter 1 to continue :
 1
 Enter Choice : 1 (Trying to add element to a completely filled statck)
 1
 9
 Stack Overflow: The Array is full
 8: 1 2 3 4 5 6 7 8
 
 Enter 1 to continue :
 1

 Enter 1 to continue :
 5

*/
