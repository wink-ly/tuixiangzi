package Box;

public class MoveStack {
	 public static int maxSize;
	  public static int[] stackArray;
	   public static int top;
	   public MoveStack() {
	      maxSize = 200000;
	      stackArray = new int[maxSize];
	      top = -1;
	     
	   }
	   public static void push(int j) {//—π»Î’ª∂•
	      stackArray[++top] = j;
	   }
	   public static int pop() {//µØ≥ˆ’ª∂•
	      return stackArray[top--];
	   }
	   public  static int peek() {//∑µªÿ’ª∂•
	      return stackArray[top];
	   }
	   public static boolean isEmpty() {
	      return (top == -1);
	   }
	   public static boolean isFull() {
	      return (top == maxSize - 1);
	   }
}
