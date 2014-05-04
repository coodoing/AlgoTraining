package Sorting.CommonSort;

import java.util.Random;

// http://rdc.taobao.com/team/jm/archives/252
public class QuickSort {

	static Random rand = new Random();

	// 参考算法导论
	// 只使用了一个索引i，i从左向右扫描，遇到比pivot小的，就跟从p+1开始的位置（由j索引进行递增标 志）进行交换，最终的划分点落在了j，然后将pivot调换到j上，再递归排序左右两边子序列。
	static int partition(int[] arr, int low, int high) {
		// 选择左端点为pivot
		int pivot = arr[low];
		int i = low + 1;
		int j = low + 1;
		for (; j <= high; j++) {
			// 小于pivot的放到左边
			if (arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		// 经过上述过程后，得到的结果为：ARR[pivot,x1,x2,x3,y1,y2,..]
		// 其中pivot为轴，x1,x2,x3为小于pivot的值，y1,y2...为大于pivot的值
		// 最后将i-1项即x3与pivot交换，得到ARR[x1,x2,x3,pivot,y1,y2,..]，完成一次快排
		// 交换左端点和pivot位置
		swap(arr, low, i - 1);
		return i - 1;
	}
	
	static void quickSort(int[] arr, int low, int high) {
		int index;
		// 长度大于1
		if (low < high) {
			index = partitionImprove3(arr, low, high);
			//System.out.println("pivot位置是："+index);
			quickSort(arr, low, index - 1);
			quickSort(arr, index + 1, high);
		}
	}

	static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] = { 2, 568, 34, 46, 9, 23, 89, 43, 572, 684, 783, 543 };
		System.out.println("快速排序前：");
		print(arr);
		quickSort(arr, 0, arr.length - 1);
		System.out.println("快速排序后：");
		print(arr);

	}
	
	// 用于取三个数的中数
	static int med3(int arr[], int a, int b, int c) {
	    return arr[a] < arr[b] ? (arr[b] < arr[c] ? b : arr[a] < arr[c] ? c : a)
	            : arr[b] > arr[c] ? b : arr[a] > arr[c] ? c : a;
	}

	// 随机选择pivot的位置
	static int partitionImprove1(int[] arr, int low, int high) {
		// 随机选择pivot
		int rdm = low + rand.nextInt(high - low + 1);
		swap(arr, low, rdm);

		return partition(arr, low, high);
	}
	
	// 使用两个索引i和j，分别从左右两 端进行扫描，i扫描到大于等于pivot的元素就停止，j扫描到小于等于pivot的元素也停止，交换两个元素，持续这个过程直到两个索引相遇，此时的 pivot的位置就落在了j，然后交换pivot和j的位置
	static int partitionImprove2(int[] arr, int low, int high) {
		int pivot = arr[low];
		//从表两端交替向中间扫描,直到low==high结束，此时位置才是轴的最后位置，避免了多次交换操作
		while (low < high) {
			while (low < high && arr[high] >= pivot)
				--high;
			arr[low] = arr[high];
			while (low < high && arr[low] <= pivot)
				++low;
			arr[high] = arr[low];
		}

		arr[low] = pivot;
		return low;
	}

	// 在数组大小小于7的情况下使用插入排序
	static int partitionImprove3(int[] arr, int low, int high) {
		// 在数组大小小于7的情况下使用插入排序
		/*if (high - low + 1 < 7) {
			for (int i = low; i <= high; i++) {
				for (int j = i; j > low && arr[j - 1] > arr[j]; j--) {
					swap(arr, j, j - 1);
				}
			}
		}*/
		
		// 在数组大小小于7的情况下使用插入排序
		if (high - low + 1 < 7) {
			for (int i = low; i < high - low + 1; i++) {
				int x = arr[i];
				int j;
				for (j = i - 1; j >= 0 && x < arr[j]; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = x;
			}
		}
		
		// 随机选择pivot
		int rdm = low + rand.nextInt(high - low + 1);
		swap(arr, low, rdm);
		
		return partitionImprove2(arr, low, high);
	}

	// 大小大于40的数组使用median-of-nine选择pivot，大小在7到40之间的数组使用three-of-median选择pivot,
	// 大小等于7的数组直接选择中数作为pivot，大小小于7的数组则直接使用插入排序
	static int partitionImprove4(int[] arr, int low, int high) {

		// 在数组大小小于7的情况下使用插入排序
		if (high - low + 1 < 7) {
			for (int i = low; i <= high; i++) {
				for (int j = i; j > low && arr[j - 1] > arr[j]; j--) {
					swap(arr, j, j - 1);
				}
			}
		}
		
		// 计算数组长度
        int len = high - low + 1;
        // 求出中点，大小等于7的数组选择pivot
        int m = low + (len >> 1);
        // 大小大于7
        if (len > 7) {
            int l = low;
            int n = low + len - 1;
            if (len > 40) { // 大数组，采用median-of-nine选择
                int s = len / 8;
                l = med3(arr, l, l + s, l + 2 * s); // 取样左端点3个数并得出中数
                m = med3(arr, m - s, m, m + s); // 取样中点3个数并得出中数
                n = med3(arr, n - 2 * s, n - s, n); // 取样右端点3个数并得出中数
            }
            m = med3(arr, l, m, n); // 取中数中的中数
        }
        // 交换pivot到左端点，后面的操作与qsort4相同
        swap(arr, low, m);

		return partitionImprove2(arr, low, high);
	}
	
	// split-end解决重复元素过多的情况
	static int partitionImprove5(int[] arr, int low, int high) {
		

		return partitionImprove2(arr, low, high);
	}
}
