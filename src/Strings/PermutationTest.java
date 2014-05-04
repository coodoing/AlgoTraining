package Strings;

import java.util.ArrayList;
import java.util.Stack;


// http://zhedahht.blog.163.com/blog/static/2541117420114172812217/
// http://yuchujin.iteye.com/blog/565818   n<m����
// http://blog.csdn.net/hackbuteer1/article/details/7462447 �ǵݹ鷨���,ȥ���ظ���ȫ���еĵݹ�ʵ��
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
			for (int i = start; i <= end; i++) {//����ָ��start�ֱ�ָ��ÿһ������   
				char temp = arr[start];//���������һ��Ԫ���������Ԫ��    
				arr[start] = arr[i];
				arr[i] = temp;

				doPermutation(arr, start + 1, end);//����Ԫ�صݹ�ȫ����    

				temp = arr[start];//������������黹ԭ    
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

	
	// ʵ�ʣ���m��Ԫ����ȡn��Ԫ�أ�Ȼ��n-m��Ԫ����һ�����滻����
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
		System.out.println("�ַ������2");
		for (int i = 1; i <= arr.length; i++) {
			combination2(arr, 0, i, list);		
		}
	}

	/*
	 * �ݹ��ʵ�ʾ���ջ��
	 * �����������ڳ���Ϊn���ַ�������m���ַ�����ϡ������ȴ�ͷɨ���ַ����ĵ�һ���ַ���
	 * ��Ե�һ���ַ�������������ѡ��һ�ǰ�����ַ��ŵ������ȥ��������������Ҫ��ʣ��
	 * ��n-1���ַ���ѡȡm-1���ַ������ǲ�������ַ��ŵ������ȥ��������������Ҫ��ʣ��
	 * ��n-1���ַ���ѡ��m���ַ���������ѡ�񶼺������õݹ�ʵ�֡�
	 * */
	
	/*�ǵݹ�ʵ�֣�
	����n���ַ���int num �� 1 ������ 2^n -1, ��num����iλ����1����λ&������
	�����жϵ�i���ַ�ȡ���ǲ�ȡ��
	
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
		System.out.println("�ַ���ȫ����1");
		perm.doPermutation(arr, 0, arr.length - 1);
		System.out.println("�ַ���ȫ����2");
		perm.doPermutation2(arr, 0, len);		
		System.out.println("m��Ԫ����ѡȡn��������");
		perm.selectMN(arr, list, len - 1);
		for (String str : list) {
			char[] temp = str.toCharArray();
			perm.doPermutation(temp, 0, temp.length - 1);
		}
		
		
		Combination combine = new Combination(arr);
		System.out.println("�ַ������1");
		combine.doCombination(arr);
		
		System.out.println("�ַ���ȥ��ȫ����");
		arr = "122".toCharArray();
		perm.removeDulPermutation(arr, 0, len);
	}
}
