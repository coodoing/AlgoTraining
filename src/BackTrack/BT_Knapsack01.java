package BackTrack;

// 回溯实现01背包问题
public class BT_Knapsack01 {

	int[] weight;
	int[] value;
	int max; // 背包的最大承重量

	int n; // 
	int[] selection; // 背包选择序列

	int c_weight; // 当前背包重量
	int c_value; // 当前背包价值

	int bestv; // 最优的背包价值
	int[] best_selection; // 最优背包选择序列

	public BT_Knapsack01() {
		weight = new int[] { 16, 15, 15 };
		value = new int[] { 45, 25, 25 };
		max = 30;

		n = weight.length;
		selection = new int[n];
		best_selection = new int[n];

		c_weight = 0;
		c_value = 0;
		bestv = 0;
	}

	private void backtrack(int t) {
		if (t >= n) {
			// 限界函数：剪去那些可行，但不可能是最优解的树枝
			if (c_value > bestv) {
				bestv = c_value;
				// 保存最优的selection值：与动态优化的区别，因为selection会在不同的剪枝过程中发生变化
				for (int i = 0; i < n; i++) {
					best_selection[i] = selection[i]; // 把最优的选择序列保存在best_selection中
					// System.out.print(selection[i] + " ");
				}
			}
		}

		else {
			// 搜索左子树
			if (c_weight + weight[t] <= max) {
				c_weight += weight[t];
				c_value += value[t];

				selection[t] = 1;
				backtrack(t + 1);

				// 背包当前价值和重量还原，搜索右子树
				c_weight -= weight[t];
				c_value -= value[t];
			}

			selection[t] = 0;
			backtrack(t + 1); // 搜索右子树

		}
	}

	private static void test(int i) {
		if (i == 1)
			System.out.println("AA");
		System.out.println("BB");
	}

	private static void testA(int i) {
		if (i == 1)
			System.out.println("CC");
		else
			System.out.println("DD");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BT_Knapsack01 knap = new BT_Knapsack01();
		knap.backtrack(0);

		int len = knap.n;
		System.out.println("回溯法背包的最优值为：" + knap.bestv);
		System.out.println("回溯对应的value值分别为：");
		for (int i = 0; i < len; i++) {
			if (knap.best_selection[i] == 1)
				System.out.print(knap.value[i] + " ");
		}
		System.out.println();
		System.out.println("回溯对应的weight值分别为：");
		for (int i = 0; i < len; i++) {
			if (knap.best_selection[i] == 1)
				System.out.print(knap.weight[i] + " ");
		}
		System.out.println();
		test(1);
		test(2);
		testA(1);
		testA(2);
	}

}
