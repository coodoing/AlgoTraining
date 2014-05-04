package StackHeap.Heap;

//最大堆排序
public class MaxHeapProblem {

	public static void buildHeap(int a[]) {
		int heapSize = a.length;
		int filter = (int) Math.floor(heapSize / 2);
		// i从第一个非叶子结点开始
		for (int i = filter - 1; i >= 0; i--) {
			heapAdjust(a, i, heapSize);
		}
	}

	// 已知H.r[i...heapSize]中记录的关键字除H.r[i]外，均满足最大堆结构
	public static void heapAdjust(int arr[], int i, int heapSize) {
		// 当前待调整的元素
		int tmp = arr[i];
		// 该元素的左孩子
		int index = 2 * i + 1;
		while (index < heapSize) {
			// 如果右孩子大于左孩子,则index+1，即交换右孩子和双亲节点
			if (index + 1 < heapSize && arr[index] < arr[index + 1]) {
				index = index + 1;
			}
			if (arr[i] < arr[index]) {
				// 交换孩子和双亲节点
				arr[i] = arr[index];
				
				// 重新赋初值
				i = index;
				index = 2 * i + 1;
			} 
			// 已经是最大堆
			else {
				break;
			}
			// 把双亲值赋给孩子节点
			arr[i] = tmp;
		}
	}
	
	private void heapAdjust2(int[] arr, int i, int heapSize) {
		int maxIndex = i;
		if (2 * i + 1 <= heapSize - 1 && arr[2 * i + 1] > arr[i])
			maxIndex = 2 * i + 1;
		if (2 * i + 2 <= heapSize - 1 && arr[i * 2 + 2] > arr[maxIndex])
			maxIndex = 2 * i + 2;
		if (maxIndex != i) {
			int temp = arr[maxIndex];
			arr[maxIndex] = arr[i];
			arr[i] = temp;
			heapAdjust2(arr, maxIndex, heapSize);
		}
		
	}

	public static void heapSort(int a[]) {
		int heapSize = a.length;
		for (int i = heapSize - 1; i > 0; i--) {
			// 交换堆顶和最后一个元素
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			// 在heapSize范围内根结点的左右子树都已经是最大堆,所以只需看新交换的堆顶元素是否满足最大堆结构即可。
			// 将H.r[0...i]重新调整为最大堆
			heapAdjust(a, 0, i);
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 6, 5, 3, 1, 8, 7, 2, 4 };
		buildHeap(arr);
		System.out.println("初始建立的最大堆是:");
		for (int data : arr)
			System.out.print(data + " ");
		System.out.println();
		System.out.println("堆经过筛选调整后，排序结果为:");
		heapSort(arr);
		for (int data : arr)
			System.out.print(data + " ");
	}

}