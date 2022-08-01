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
	   public static void push(int j) {//ѹ��ջ��
	      stackArray[++top] = j;
	   }
	   public static int pop() {//����ջ��
	      return stackArray[top--];
	   }
	   public  static int peek() {//����ջ��
	      return stackArray[top];
	   }
	   public static boolean isEmpty() {
	      return (top == -1);
	   }
	   public static boolean isFull() {
	      return (top == maxSize - 1);
	   }
}
