package Sorting.CommonSort;

// 桶排序
public class BucketSort {

	// 插入排序
	static void insertSort(int[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			int p = a[i];
			insert(a, i, p);
		}
	}

	static void insert(int[] a, int index, int x) {
		// 元素插入数组a[0:index-1]
		int i;
		for (i = index - 1; i >= 0 && x < a[i]; i--) {
			a[i + 1] = a[i];
		}
		a[i + 1] = x;
	}

	private static void bucketSort(int[] a) {
		int M = 10; // 11个桶
		int n = a.length;
		int[] bucketA = new int[M]; // 用于存放每个桶中的元素个数
		// 构造一个二维数组b，用来存放A中的数据,这里的B相当于很多桶，B[i][]代表第i个桶
		int[][] b = new int[M][n];
		int i, j;
		for (i = 0; i < M; i++)
			for (j = 0; j < n; j++)
				b[i][j] = 0;

		int data, bucket;
		for (i = 0; i < n; i++) {
			data = a[i];
			bucket = data / 10;
			b[bucket][bucketA[bucket]] = a[i];// B[0][]中存放A中进行A[i]/10运算后高位为0的数据，同理B[1][]存放高位为1的数据
			bucketA[bucket]++;// 用来计数二维数组中列中数据的个数，也就是桶A[i]中存放数据的个数
		}
		System.out.println("每个桶内元素个数：");
		for (i = 0; i < M; i++) {
			System.out.print(bucketA[i] + " ");
		}
		System.out.println();

		System.out.println("数据插入桶后，桶内未进行排序前的结果为：");
		for (i = 0; i < M; i++) {
			for (j = 0; j < n; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}

		System.out.println("对每个桶进行插入排序，结果为：");
		// 下面使用直接插入排序对这个二维数组进行排序,也就是对每个桶进行排序
		for (i = 0; i < M; i++) {
			// 下面是对具有数据的一列进行直接插入排序，也就是对B[i][]这个桶中的数据进行排序
			if (bucketA[i] != 0) {
				// 插入排序
				for (j = 1; j < bucketA[i]; j++) {
					int p = b[i][j];
					int k;
					for (k = j - 1; k >= 0 && p < b[i][k]; k--)
					{
						assert k==-1;
						b[i][k + 1] = b[i][k];
					}
					b[i][k + 1] = p;
				}
			}
		}
		
		// 输出排序过后的顺序
		for (i = 0; i < 10; i++) {
			if (bucketA[i] != 0) {
				for (j = 0; j < bucketA[i]; j++) {
					System.out.print(b[i][j] + " ");
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {3,5,45,34,2,78,67,34,56,98};															
		bucketSort(arr);
	}

}
