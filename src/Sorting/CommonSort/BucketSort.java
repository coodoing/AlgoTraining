package Sorting.CommonSort;

// Ͱ����
public class BucketSort {

	// ��������
	static void insertSort(int[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			int p = a[i];
			insert(a, i, p);
		}
	}

	static void insert(int[] a, int index, int x) {
		// Ԫ�ز�������a[0:index-1]
		int i;
		for (i = index - 1; i >= 0 && x < a[i]; i--) {
			a[i + 1] = a[i];
		}
		a[i + 1] = x;
	}

	private static void bucketSort(int[] a) {
		int M = 10; // 11��Ͱ
		int n = a.length;
		int[] bucketA = new int[M]; // ���ڴ��ÿ��Ͱ�е�Ԫ�ظ���
		// ����һ����ά����b���������A�е�����,�����B�൱�ںܶ�Ͱ��B[i][]�����i��Ͱ
		int[][] b = new int[M][n];
		int i, j;
		for (i = 0; i < M; i++)
			for (j = 0; j < n; j++)
				b[i][j] = 0;

		int data, bucket;
		for (i = 0; i < n; i++) {
			data = a[i];
			bucket = data / 10;
			b[bucket][bucketA[bucket]] = a[i];// B[0][]�д��A�н���A[i]/10������λΪ0�����ݣ�ͬ��B[1][]��Ÿ�λΪ1������
			bucketA[bucket]++;// ����������ά�������������ݵĸ�����Ҳ����ͰA[i]�д�����ݵĸ���
		}
		System.out.println("ÿ��Ͱ��Ԫ�ظ�����");
		for (i = 0; i < M; i++) {
			System.out.print(bucketA[i] + " ");
		}
		System.out.println();

		System.out.println("���ݲ���Ͱ��Ͱ��δ��������ǰ�Ľ��Ϊ��");
		for (i = 0; i < M; i++) {
			for (j = 0; j < n; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}

		System.out.println("��ÿ��Ͱ���в������򣬽��Ϊ��");
		// ����ʹ��ֱ�Ӳ�������������ά�����������,Ҳ���Ƕ�ÿ��Ͱ��������
		for (i = 0; i < M; i++) {
			// �����ǶԾ������ݵ�һ�н���ֱ�Ӳ�������Ҳ���Ƕ�B[i][]���Ͱ�е����ݽ�������
			if (bucketA[i] != 0) {
				// ��������
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
		
		// �����������˳��
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
