import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class QueueArray<T>  {
    T[] queue; // queue array of type T
    int head;  // index value of front end of the queue
    int tail;  // index value of tail end of the queue
    int size;  // size of the queue
    int length; // no of elements in the queue
    
    /**
     * Constructor for QueueArray
     */
    public QueueArray(){
        this.size = 16;
        this.head = -1;
        this.tail = -1;
        this.length = 0;
        this.queue = (T[]) new Object[size];
    }
    
    /**
     * Method to add element to the tail end of the queue
     *
     * @param element
     *               : T - element to be pushed
     */
    public void offer(T element){
        if(isEmpty()){ // checking if the element being pushed is the first element of the queue
            head++;
            tail++;
            queue[tail] = element; // pushing the element to the end of the queue
        }
        else{
            reSize(); // if the queue is 90% full, doubles the queue size
            tail = (tail+1)%size; // wrapping-around if the front indices of the array are empty
            queue[tail] = element;
        }
        ++length;
    }
    
    // Method to remove element from the front end of the queue
    public T poll(){
        if(isEmpty()){
            return null; // returning null if the queue is empty
        }
        T element = queue[head];
        if(head==tail){ // checking if there is only one element in the queue
            head = -1;
            tail = -1;
        }
        else{
            head = (head+1)%size;
        }
        length--;
        reSize(); // Halves the queue size if it is less than 25% occupied
        return element;
    }
    
    // Method to peek the element from the front end of the queue
    public T peek(){
        if(isEmpty()){
            return null;
        }
        return queue[head];
    }
    
    // Method to check if the queue is empty
    public boolean isEmpty(){
        if(head == -1 && tail == -1){
            return true;
        }
        return false;
    }
    
    /**
     * Method to return string containing head position, tail position, size
     * and elements of the queue
     */
    public String toString() {
        return "Queue Head=" + head + ", Tail=" + tail + ", size=" + size
        + ", queue=" + Arrays.toString(queue) + "]";
    }
    
    //Method to print the queue
    public void printQueue(){
        if(head==-1){ // checking if the queue is empty
            return ;
        }
        System.out.print( this.size + ": ");
        if(head<=tail){ // checking if the queue is linear
            int cur=head;
            while(cur<=tail){
                System.out.print(queue[cur] + " ");
                cur++;
            }
        }
        else{
            int cur=head;
            while(cur<size){ // moving through elements from head index to the end of array
                System.out.print(queue[cur] + " ");
                cur++;
            }
            cur=0; // wrapping around
            while(cur<=tail){ // moving through elements from index 0 the tail index of the array
                System.out.print(queue[cur] + " ");
                cur++;
            }
        }
    }
    
    /**
     * Method to resize the queue size.
     * Doubles the queue size if the queue is 90% full.
     * Halves the queue size if the queue is less than 25% occupied.
     * minimum size: 16
     */
    public void reSize(){
        if(((float)length/size)>0.9){ // checking if the queue length is over 90%
            T[] tempQueue = (T[]) new Object[2*size]; // creating a new temp queue of double the size of main queue
            int pos =0;
            // Copying the contents of the main queue to new temp queue
            if(head<=tail){// checking if the queue is linear
                int cur=head;
                while(cur<=tail){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
            }
            else{ // if the queue is circular
                int cur=head;
                while(cur<size){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
                cur=0; // wrapping-around the queue
                while(cur<=tail){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
            }
            head=0;
            tail=pos-1;
            size = 2*size;
            queue = tempQueue; // assigning the tempQueue to the main queue
            return;
        }
        else if(((float)length/size)<0.25 && size>16){ // checking if the queue length is less than 25%
            T[] tempQueue = (T[]) new Object[size/2]; // creating a new temp queue of half the size main queue
            if(isEmpty()){ // checking if the queue is empty
                head=-1;
                tail=-1;
                size = size/2;
                queue = tempQueue;
                return ;
            }
            // Copying the contents of the main queue to new temp queue
            int pos =0;
            if(head<=tail){ // checking if the queue is linear
                int cur=head;
                while(cur<=tail){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
            }
            else{ // if the queue is circular
                int cur=head;
                while(cur<size){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
                cur=0; // wrapping around
                while(cur<=tail){
                    tempQueue[pos++] = queue[cur];
                    cur++;
                }
            }
            head=0;
            tail=pos-1;
            size = size/2;
            queue = tempQueue;
            return ;
        }
    }
    
    //Method to return the size of the queue
    public int getSize(){
        return size;
    }
    
    //Method to return number of elements in the queue
    public int getLength(){
        return length;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        int choice=0;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        }
        else {
            in = new Scanner(System.in);
        }
        int con=1;
        QueueArray<Integer> Q = new QueueArray<>();
        while(con==1){
            System.out.println("Choice : ");
            choice = in.nextInt();
            switch(choice){
                case 1:	int n = in.nextInt();
                    for(int i=1; i<=n; i++) {
                        int value = in.nextInt();
                        Q.offer(value);
                    }
                    System.out.println("Queue Size for " + Q.getLength() + " elements is " + Q.getSize() );
                    Q.printQueue();
                    break;
                case 2: int del=in.nextInt();
                    for(int i=1; i<=del; i++) {
                        Q.poll();
                    }
                    System.out.println("Queue Size for " + Q.getLength() + " elements is " + Q.getSize() );
                    Q.printQueue();
                    break;
                case 3: System.out.println(Q.peek());
                    break;
                default: System.out.println("invalid choice");
            }
            System.out.println("\nEnter 1 to continue : ");
            con = in.nextInt();
        }
    }
}

/* 
 choice-1: To add elements
    Enter number of elements to be added
    Enter elements
 choice-2: To remove elements
    Enter number of elements to be removed
 choice-3: To peek the front element
 
    Sample input:
 Choice :
 1 - (To add elements)
 5 - (number of elements to be added)
 1
 2
 3
 4
 5
 Queue Size for 5 elements is 16
 16: 1 2 3 4 5
 
 Enter 1 to continue :
 1
 Choice :
 1
 12
 6
 7
 8
 9
 10
 11
 12
 13
 14
 15
 16
 17
 Queue Size for 17 elements is 32
 32: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
 
 Enter 1 to continue :
 1
 Choice :
 2 - (To remove elements)
 10 - (To remove number of elements)
 Queue Size for 7 elements is 16
 16: 11 12 13 14 15 16 17
 
 Enter 1 to continue :
 1
 Choice :
 3 - (To peek top element)
 11
 
 Enter 1 to continue : 
 5
*/
