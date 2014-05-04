package Strings;

import java.util.ArrayList;
import java.util.Stack;


// http://zhedahht.blog.163.com/blog/static/2541117420114172812217/
// http://yuchujin.iteye.com/blog/565818   n<m排列
// http://blog.csdn.net/hackbuteer1/article/details/7462447 非递归法求解,去掉重复的全排列的递归实现
class Permutation {
	int[] intArr = new int[] { 1, 2, 3 };
	char[] charArr;
	int length;

	public Permutation(char[] arr) {
		charArr = arr;
		length = arr.length;
	}

	public void doPermutation(char[] arr, int start, int end) {
		if (start == end) {
			for (int i = 0; i <= end; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		} else {
			for (int i = start; i <= end; i++) {//（让指针start分别指向每一个数）   
				char temp = arr[start];//交换数组第一个元素与后续的元素    
				arr[start] = arr[i];
				arr[i] = temp;

				doPermutation(arr, start + 1, end);//后续元素递归全排列    

				temp = arr[start];//将交换后的数组还原    
				arr[start] = arr[i];
				arr[i] = temp;
			}
		}
	}

	public void doPermutation2(char[] arr, int start, int len) {
		if (start == len - 1) {
			for (int i = 0; i < len; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		} else {
			for (int i = start; i < len; i++) {
				swap(arr, start, i);
				doPermutation2(arr, start + 1, len);
				swap(arr, start, i);
			}
		}
	}

	public void swap(char[] arr, int i, int j) {
		char temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	boolean isSwap(int begin, int end)  
	{  
	    int i ;  
	    for(i = begin ; i <end ; i++)  
	    {  
	        if(i == end)  
	            return false;  
	    }  
	    return true;  
	}  
	
	// removeDulPermutation(arr,0,len)
	public void removeDulPermutation(char[] arr, int start, int len) {
		if (start == len - 1) {
			for (int i = 0; i < len; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		} else {
			for (int i = start; i < len; i++) {
				if(isSwap(start,i))
				{
					swap(arr, start, i);
					removeDulPermutation(arr, start + 1, len);
					swap(arr, start, i);
				}
			}
		}
	}

	
	// 实质：从m个元素中取n个元素，然后将n-m个元素逐一进行替换即可
	public void selectMN(char[] source, ArrayList<String> arrayList, int num) {
		int len = source.length;
		char[] temp = new char[num];

		System.arraycopy(source, 0, temp, 0, num);

		arrayList.add(new String(temp));

		for (int i = num; i < len; i++) {
			for (int j = 0; j < num; j++) {
				char tempChar = temp[j];
				temp[j] = source[i];

				arrayList.add(new String(temp));

				temp[j] = tempChar;
			}
		}
	}
}

class Combination {
	char[] charArr;
	int length;

	public Combination(char[] arr) {
		charArr = arr;
		length = arr.length;
	}

	public void doCombination(char[] arr) {
		if (arr.length == 0)
			return;

		Stack<Character> stack = new Stack<Character>();
		ArrayList<Character> list=new ArrayList<Character>();  
		
		for (int i = 1; i <= arr.length; i++) {
			combination(arr, 0, i, stack);			
		}
		System.out.println("字符串组合2");
		for (int i = 1; i <= arr.length; i++) {
			combination2(arr, 0, i, list);		
		}
	}

	/*
	 * 递归的实质就是栈。
	 * 假设我们想在长度为n的字符串中求m个字符的组合。我们先从头扫描字符串的第一个字符。
	 * 针对第一个字符，我们有两种选择：一是把这个字符放到组合中去，接下来我们需要在剩下
	 * 的n-1个字符中选取m-1个字符；二是不把这个字符放到组合中去，接下来我们需要在剩下
	 * 的n-1个字符中选择m个字符。这两种选择都很容易用递归实现。
	 * */
	
	/*非递归实现：
	设有n个字符。int num 从 1 自增到 2^n -1, 将num右移i位，跟1做按位&操作，
	即可判断第i个字符取还是不取。
	
	*/
	public void combination(char[] arr, int begin, int number,
			Stack<Character> stack) {
		if (number == 0) {
			System.out.println(stack.toString());
			return;
		}
		if (begin == arr.length) {
			return;
		}
		stack.push(arr[begin]);
		combination(arr, begin + 1, number - 1, stack);
		stack.pop();
		combination(arr, begin + 1, number, stack);
	}
	
	public void combination2(char[] arr, int begin, int number,
			ArrayList<Character> list) {
		 if(number==0){  
	            System.out.println(list.toString());  
	            return ;  
	        }  
	        if(begin==arr.length){  
	            return;  
	        }  
	        list.add(arr[begin]);  
	        combination2(arr,begin+1,number-1,list);  
	        list.remove((Character)arr[begin]);  
	        combination2(arr,begin+1,number,list);  
	}

}

public class PermutationTest {

	public static void main(String[] args) {
		String text = "abc";
		char[] arr = text.toCharArray();
		int len = arr.length;

		ArrayList<String> list = new ArrayList<String>();

		Permutation perm = new Permutation(arr);
		System.out.println("字符串全排列1");
		perm.doPermutation(arr, 0, arr.length - 1);
		System.out.println("字符串全排列2");
		perm.doPermutation2(arr, 0, len);		
		System.out.println("m个元素中选取n个的排列");
		perm.selectMN(arr, list, len - 1);
		for (String str : list) {
			char[] temp = str.toCharArray();
			perm.doPermutation(temp, 0, temp.length - 1);
		}
		
		
		Combination combine = new Combination(arr);
		System.out.println("字符串组合1");
		combine.doCombination(arr);
		
		System.out.println("字符串去重全排列");
		arr = "122".toCharArray();
		perm.removeDulPermutation(arr, 0, len);
	}
}
