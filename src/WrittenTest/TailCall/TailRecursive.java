package WrittenTest.TailCall;

public class TailRecursive {

	public static int FibonacciRecursively(int n)
	{
		if(n<2)
			return n;
		return FibonacciRecursively(n-1)+FibonacciRecursively(n-2);
	}
	
	// acc1：f(n-2) ; acc2：f(n-1)
	// f(n) = f(n-2)+f(n-1)
	public static int FibonacciTailRecursive(int n,int acc1,int acc2)
	{
		if(n==0)
			return acc1;
		return FibonacciTailRecursive(n-1,acc2,acc1+acc2);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("斐波拉契数列普通递归实现："+FibonacciRecursively(6));
		System.out.println("斐波拉契数列尾递归实现："+FibonacciTailRecursive(6,0,1));
		
	}

}
