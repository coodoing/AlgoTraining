package Sorting.CommonSort;

// ���������ȶ�����
public class RadixSorting {

	// dΪ���ݳ���
	private static void radixSorting(int[] arr, int d) {		
		//arr = countingSort(arr, 0);
		for (int i = 0; i < d; i++) {
			arr = countingSort(arr, i); // ���ζԸ�λ��������ֱ���ü�������ı��壩
			print(arr,i+1,d);
		}
	}
	
	// ��ÿ�ΰ�λ����Ľ����ӡ����
	static void print(int[] arr,int k,int d)
	{
		if(k==d)
			System.out.println("����������Ϊ��");
		else
			System.out.println("����"+k+"λ����󣬽��Ϊ��");
		for (int t : arr) {
			System.out.print(t + " ");
		}
		System.out.println();
	}
	
	// ���ü��������Ԫ�ص�ÿһλ��������
	private static int[] countingSort(int[] arr, int index) {
		int k = 9;
		int[] b = new int[arr.length];
		int[] c = new int[k + 1]; //����Ƚ����⣺����ÿһλ�����Ϊ9		

		for (int i = 0; i < k; i++) {
			c[i] = 0;
		}
		for (int i = 0; i < arr.length; i++) {
			int d = getBitData(arr[i], index);
			c[d]++;
		}
		for (int i = 1; i <= k; i++) {
			c[i] += c[i - 1];
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			int d = getBitData(arr[i], index);
			b[c[d] - 1] = arr[i];//C[d]-1 �ʹ���С�ڵ���Ԫ��d��Ԫ�ظ���������d��B��λ��
			c[d]--;
		}
		return b;
	}

	// ��ȡdataָ��λ����
	private static int getBitData(int data, int index) {
		while (data != 0 && index > 0) {
			data /= 10;
			index--;
		}
		return data % 10;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {326,453,608,835,751,435,704,690,88,79,79};//{ 333, 956, 175, 345, 212, 542, 99, 87 };
		System.out.println("��������ǰΪ��");
		for (int t : arr) {
			System.out.print(t + " ");
		}
		System.out.println();
		radixSorting(arr, 4);		
	}

}
