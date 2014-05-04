package WrittenTest.LDS;

// http://eriol.iteye.com/blog/858671
/*解题思路：动态规划。假设0到i-1这段数列的最长递减序列的长度为s，且这些序列们的末尾值中的最大值是t。对于a[i]有一下情况：
 (1) 如果a[i]比t小，那么将a[i]加入任何一个子序列都会使0到i的最长单调序列长度变成s+1，这样的话，在0到i的数列中，长度为s+1的递减子序列的末尾值最大值就是a[i]；
 (2) 如果a[i]和t相等，那么说明数列从0项到i项的最长单调子序列长度就是s；
 (3) 如果a[i]比t大，那么a[i]就不一定能够成为长度为s的递减子序列的末项，这取决于长度为s-1的各个递减子序列的末尾值的最大值t'。
 如果t'比a[i]要大，那么就可以形成长度为s的递减子序列，如果t'比a[i]小，那么问题就在往前递推，把a[i]和长度为s-2的各个递减子序列的末尾值的最大值比较，直到：
 (1) a[i]比长度为s'的递减子序列的末尾值的最大值要小，那么a[i]就是数列0到i部分长度为s'+1的递减子序列的末尾值中的最大值；
 (2) a[i]比任何长度的递减子序列的末尾值的最大值都要大，那么a[i]就是长度为1的递减子序列的最大值。


 所以，引入数组c[i]表示长度为i的递减子序列的末尾值的最大值。显然c数组必然是单调递减的。
 b[i]数组用于子序列的输出，b[i]表示从a[0]到a[i]且终止于a[i]的最长递减序列的长度。主要用于记录最长递减序列长度数组。
 定义变量s，初始化为1，s表示目前为止最长单调序列的长度。

 算法复杂度：O(nlogn)，对于数组c的查找使用二分查找，降低了整体的算法复杂度。


 * */
// 类似《编程之美》中最长递增子序列
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

		System.out.println("最长递减子序列的长度为: " + s);
		System.out.print("最长递减子序列为: ");
		print(array, b, s, n - 1);
		System.out.println();
		System.out.println("数组b，c分别为：");
		print(b);
		print(c);
	}

}
