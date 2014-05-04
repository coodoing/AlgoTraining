package Sorting.CommonSort;

/*
 *  �㷨�Ĳ������£�
	1���ҳ��������������������С��Ԫ��
	2��ͳ��������ÿ��ֵΪt��Ԫ�س��ֵĴ�������������C�ĵ�t��
	3�������еļ����ۼӣ���C�еĵ�һ��Ԫ�ؿ�ʼ��ÿһ���ǰһ����ӣ�
	4���������Ŀ�����飺��ÿ��Ԫ��t����������ĵ�C(t)�ÿ��һ��Ԫ�ؾͽ�C(t)��ȥ1
 * */
public class CountingSort {
	// ����bitmap���򣬿�����bitmap������������
	public static void countSort(int[] a, int[] b, final int k) {
		// k>=n
		int[] c = new int[k + 1];
		for (int i = 0; i < k; i++) {
			c[i] = 0;
		}		
		for (int i = 0; i < a.length; i++) {
			c[a[i]]++;
		}		
		System.out.println("\n****************");
		System.out.println("���������2����,��ʱ����C��Ϊ��");
		for (int m:c) {
			System.out.print(m + " ");
		}
		
		for (int i = 1; i <= k; i++) {
			c[i] += c[i - 1];
		}		
		System.out.println("\n���������3����,��ʱ����C��Ϊ��");
		for (int m:c) {
			System.out.print(m + " ");
		}
		
		for (int i = a.length - 1; i >= 0; i--) {
			b[c[a[i]] - 1] = a[i];//C[A[i]]-1 �ʹ���С�ڵ���Ԫ��A[i]��Ԫ�ظ���������A[i]��B��λ��
			c[a[i]]--;
		}
		System.out.println("\n���������4����,��ʱ����C��Ϊ��");
		for (int n:c) {
			System.out.print(n + " ");
		}
		System.out.println("\n���������4����,����B��Ϊ��");
		for (int t:b) {
			System.out.print(t + " ");
		}
		System.out.println();
		System.out.println("****************\n");
	}

	public static int getMaxNumber(int[] a) {
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 2, 5, 3, 0, 2, 3, 0, 3 };
		int[] b = new int[a.length];
		System.out.println("��������ǰΪ��");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		countSort(a, b, getMaxNumber(a));
		System.out.println("���������Ϊ��");
		for (int i = 0; i < a.length; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}

}
