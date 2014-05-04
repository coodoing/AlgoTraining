package BackTrack;

// ����ʵ��01��������
public class BT_Knapsack01 {

	int[] weight;
	int[] value;
	int max; // ��������������

	int n; // 
	int[] selection; // ����ѡ������

	int c_weight; // ��ǰ��������
	int c_value; // ��ǰ������ֵ

	int bestv; // ���ŵı�����ֵ
	int[] best_selection; // ���ű���ѡ������

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
			// �޽纯������ȥ��Щ���У��������������Ž����֦
			if (c_value > bestv) {
				bestv = c_value;
				// �������ŵ�selectionֵ���붯̬�Ż���������Ϊselection���ڲ�ͬ�ļ�֦�����з����仯
				for (int i = 0; i < n; i++) {
					best_selection[i] = selection[i]; // �����ŵ�ѡ�����б�����best_selection��
					// System.out.print(selection[i] + " ");
				}
			}
		}

		else {
			// ����������
			if (c_weight + weight[t] <= max) {
				c_weight += weight[t];
				c_value += value[t];

				selection[t] = 1;
				backtrack(t + 1);

				// ������ǰ��ֵ��������ԭ������������
				c_weight -= weight[t];
				c_value -= value[t];
			}

			selection[t] = 0;
			backtrack(t + 1); // ����������

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
		System.out.println("���ݷ�����������ֵΪ��" + knap.bestv);
		System.out.println("���ݶ�Ӧ��valueֵ�ֱ�Ϊ��");
		for (int i = 0; i < len; i++) {
			if (knap.best_selection[i] == 1)
				System.out.print(knap.value[i] + " ");
		}
		System.out.println();
		System.out.println("���ݶ�Ӧ��weightֵ�ֱ�Ϊ��");
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
