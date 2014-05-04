package Sorting.CommonSort;

// 归并排序算法  
public class MergingSort {

	public static void mergeSort(int[] arr) {
		int[] temp = new int[arr.length];//临时数组  
		mSort(arr, temp, 0, arr.length - 1);
	}

	private static void mSort(int[] arr, int[] temp, int low, int high) {
		if (low >= high)
			return;
		else {
			int middle = (low + high) / 2;
			// 对左边数组进行递归  
			mSort(arr, temp, low, middle);
			// 对右边数组进行递归
			mSort(arr, temp, middle + 1, high);
			// 合并 
			merge(arr, temp, low, middle, high);
		}
	}

	// 将有序数组ARR[low...middle]和ARR[middle+1...high]归并为有序的TEMP[low...high] 
	private static void merge(int[] arr, int[] temp, int low, int middle,
			int high) {
		// 左数组第一个元素的索引  
		int leftIndex = low;
		// 右数组第一个元素索引  
		int rightIndex = middle + 1;
		// tmpIndex 记录临时数组的索引  
		int tmpIndex = low;

		while (low <= middle && rightIndex <= high) {
			// 从两个数组中取出最小的放入临时数组  
			if (arr[low] <= arr[rightIndex]) {
				temp[tmpIndex++] = arr[low++];
			} else {
				temp[tmpIndex++] = arr[rightIndex++];
			}
		}
		// 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）  
		while (rightIndex <= high) {
			temp[tmpIndex++] = arr[rightIndex++];
		}
		while (low <= middle) {
			temp[tmpIndex++] = arr[low++];
		}
		
		// 将临时数组中的内容拷贝回原数组中: （原low-right范围的内容被复制回原数组） 
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
		System.out.println("归并排序前：");
		print(arr);
		mergeSort(arr);
		System.out.println("归并排序后：");
		print(arr);
	}
}
