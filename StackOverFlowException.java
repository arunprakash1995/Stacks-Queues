package cs6301.g23;
/**
 * This programs implements StackOverFLowException
 *
 * @author Arun Prakash Themothy Prabhu Vincent
 * @author Akshaya Udayakumar
 * @author Radhika Kalaiselvan
 *
 * Version 1.0 - 9/4/17
 */

public class StackOverFlowException extends Exception {

	public StackOverFlowException() {
		super("Stack Overflow: The Array is full "); // The Error Message is set
	}
}
