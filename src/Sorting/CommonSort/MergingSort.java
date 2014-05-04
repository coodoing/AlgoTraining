package Sorting.CommonSort;

// �鲢�����㷨  
public class MergingSort {

	public static void mergeSort(int[] arr) {
		int[] temp = new int[arr.length];//��ʱ����  
		mSort(arr, temp, 0, arr.length - 1);
	}

	private static void mSort(int[] arr, int[] temp, int low, int high) {
		if (low >= high)
			return;
		else {
			int middle = (low + high) / 2;
			// �����������еݹ�  
			mSort(arr, temp, low, middle);
			// ���ұ�������еݹ�
			mSort(arr, temp, middle + 1, high);
			// �ϲ� 
			merge(arr, temp, low, middle, high);
		}
	}

	// ����������ARR[low...middle]��ARR[middle+1...high]�鲢Ϊ�����TEMP[low...high] 
	private static void merge(int[] arr, int[] temp, int low, int middle,
			int high) {
		// �������һ��Ԫ�ص�����  
		int leftIndex = low;
		// �������һ��Ԫ������  
		int rightIndex = middle + 1;
		// tmpIndex ��¼��ʱ���������  
		int tmpIndex = low;

		while (low <= middle && rightIndex <= high) {
			// ������������ȡ����С�ķ�����ʱ����  
			if (arr[low] <= arr[rightIndex]) {
				temp[tmpIndex++] = arr[low++];
			} else {
				temp[tmpIndex++] = arr[rightIndex++];
			}
		}
		// ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����  
		while (rightIndex <= high) {
			temp[tmpIndex++] = arr[rightIndex++];
		}
		while (low <= middle) {
			temp[tmpIndex++] = arr[low++];
		}
		
		// ����ʱ�����е����ݿ�����ԭ������: ��ԭlow-right��Χ�����ݱ����ƻ�ԭ���飩 
		while (leftIndex <= high) {
			arr[leftIndex] = temp[leftIndex++];
		}
	}

	private static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] = { 2, 568, 34, 46, 9, 23, 89, 43, 572, 684, 783, 543 };
		System.out.println("�鲢����ǰ��");
		print(arr);
		mergeSort(arr);
		System.out.println("�鲢�����");
		print(arr);
	}
}
