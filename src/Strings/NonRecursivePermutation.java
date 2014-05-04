package Strings;

public class NonRecursivePermutation {

	static int[] arr = new int[] { 1, 2, 3, 4, 5, 6 };
	static int count = 1;

	// 反转区间
	private static void reverse(int[] arr, int pBegin, int pEnd) {
		while (pBegin < pEnd) {
			/*swap(arr, pBegin, pEnd);
			pBegin++;
			pEnd--;*/
			swap(arr, pBegin++, pEnd--);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static boolean hasNext() {
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] > arr[i - 1])
				return true;
		}
		return false;
	}

	private static void next() {
		int len = arr.length;
		int replace = 0; // 需替换数
		int firstR = 0; // 从后向前找比替换数大的第一个数
		// 找降序的相邻2数,前一个数即需替换数
		for (int i = len - 1; i > 0; i--) {
			if (arr[i] > arr[i - 1]) {
				replace = i - 1;
				break;
			}
		}

		//System.out.println("需替换数位置：" + replace);

		// 从后向前找比替换数大的第一个数
		// 比如926540的后继应该是946520,而不是956240
		for (int i = len - 1; i > replace; i--) {
			if (arr[i] > arr[replace]) {
				firstR = i;
				break; //找到之后直接退出
			}
		}

		//System.out.println("替换数位置：" + firstR);

		// replace与firstR交换
		swap(arr, replace, firstR);
		// 替换数后的数全部反转
		reverse(arr, replace + 1, len - 1);

		count++;
		print();
	}

	private static void print() {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		while (hasNext() == true) {
			next();
		}
		System.out.println("数组完全反转后的结果：");
		reverse(arr, 0, arr.length - 1);
		print();
		System.out.println("全排列的数目为：" + count);

		/*********************/
		System.out.println("数组是否存在后继：" + hasNext());
		System.out.println("数组一次替换后的结果：");
		next();
	}

}
