package WrittenTest.LDS;

// http://eriol.iteye.com/blog/858671
/*����˼·����̬�滮������0��i-1������е���ݼ����еĳ���Ϊs������Щ�����ǵ�ĩβֵ�е����ֵ��t������a[i]��һ�������
 (1) ���a[i]��tС����ô��a[i]�����κ�һ�������ж���ʹ0��i����������г��ȱ��s+1�������Ļ�����0��i�������У�����Ϊs+1�ĵݼ������е�ĩβֵ���ֵ����a[i]��
 (2) ���a[i]��t��ȣ���ô˵�����д�0�i�������������г��Ⱦ���s��
 (3) ���a[i]��t����ôa[i]�Ͳ�һ���ܹ���Ϊ����Ϊs�ĵݼ������е�ĩ���ȡ���ڳ���Ϊs-1�ĸ����ݼ������е�ĩβֵ�����ֵt'��
 ���t'��a[i]Ҫ����ô�Ϳ����γɳ���Ϊs�ĵݼ������У����t'��a[i]С����ô���������ǰ���ƣ���a[i]�ͳ���Ϊs-2�ĸ����ݼ������е�ĩβֵ�����ֵ�Ƚϣ�ֱ����
 (1) a[i]�ȳ���Ϊs'�ĵݼ������е�ĩβֵ�����ֵҪС����ôa[i]��������0��i���ֳ���Ϊs'+1�ĵݼ������е�ĩβֵ�е����ֵ��
 (2) a[i]���κγ��ȵĵݼ������е�ĩβֵ�����ֵ��Ҫ����ôa[i]���ǳ���Ϊ1�ĵݼ������е����ֵ��


 ���ԣ���������c[i]��ʾ����Ϊi�ĵݼ������е�ĩβֵ�����ֵ����Ȼc�����Ȼ�ǵ����ݼ��ġ�
 b[i]�������������е������b[i]��ʾ��a[0]��a[i]����ֹ��a[i]����ݼ����еĳ��ȡ���Ҫ���ڼ�¼��ݼ����г������顣
 �������s����ʼ��Ϊ1��s��ʾĿǰΪֹ��������еĳ��ȡ�

 �㷨���Ӷȣ�O(nlogn)����������c�Ĳ���ʹ�ö��ֲ��ң�������������㷨���Ӷȡ�


 * */
// ���ơ����֮�����������������
public class LongestDecendingSequence {

	// 
	public static int bsearch(int[] a, int s, int m) {
		int low = 1;
		int high = s;
		int mid;

		while (low < high) {
			mid = (low + high) / 2;
			if (a[mid] == m)
				return mid;
			if (a[mid] > m)
				low = mid + 1;
			else
				high = mid;
		}
		if (a[low] <= m)
			return low;
		else
			return low + 1;
	}

	public static void print(int[] a, int[] b, int level, int start) {
		if (level == 0)
			return;
		int i = start;
		while (b[i] != level)
			i--;
		print(a, b, level - 1, i - 1);
		System.out.print(a[i] + " ");
	}

	private static void print(int[] arr) {
		for (int data : arr)
			System.out.print(data + " ");
		System.out.println();
	}

	public static void main(String[] args) {

		int[] array = new int[] { 9, 4, 6, 2, 5, 4, 3, 2 };
		int n = array.length;
		int[] b = new int[n];
		int[] c = new int[n + 1];

		for (int i = 0; i < n; i++) {
			c[i] = -1;
		}

		int s = 1;
		int k;
		c[1] = array[0];

		for (int i = 0; i < n; i++) {
			k = bsearch(c, s, array[i]);
			if (k > s)
				s++;
			c[k] = array[i];
			b[i] = k;
		}

		System.out.println("��ݼ������еĳ���Ϊ: " + s);
		System.out.print("��ݼ�������Ϊ: ");
		print(array, b, s, n - 1);
		System.out.println();
		System.out.println("����b��c�ֱ�Ϊ��");
		print(b);
		print(c);
	}

}
