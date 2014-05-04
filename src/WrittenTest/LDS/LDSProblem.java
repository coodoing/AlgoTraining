package WrittenTest.LDS;

// ���֮���������������з���
// http://bylijinnan.iteye.com/blog/1627360
public class LDSProblem {

	public static void maxSubstrDecrease(int[] array) {
		// ����array�е�ǰi��Ԫ���У���ݼ������еĳ�����LDS[i]
		int len = array.length;
		int[] LDS = new int[len];
		int max = 0;
		for (int i = 0; i < len; i++) {
			LDS[i] = 1;
			for (int k = 0; k < i; k++) {
				// LDS[i+1]=max{1,LDS[k]+1}, array[i+1]<array[k],for any k<=i;
				if (array[i] < array[k] && LDS[k] + 1 > LDS[i]) {
					LDS[i] = LDS[k] + 1;
				}
			}
		}
		max = maxsub(LDS);
		System.out.println("��ݼ������еĳ���Ϊ:" + max);
		System.out.println("LDS����Ϊ:");
		print(LDS);
		System.out.print("��ݼ�������Ϊ:\n");
		print(array, LDS, max, len - 1);
	}

	// max:������г���
	// end:��ݼ��������е����һ��Ԫ��
	// lds:����array�е�ǰi��Ԫ���У���ݼ������еĳ�������
	private static void print(int[] array, int[] lds, int max, int end) {
		if (max == 0)
			return;
		int i = end;
		while (lds[i] != max)
			i--;
		print(array, lds, max - 1, i - 1);
		System.out.print(array[i] + " ");
	}

	private static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}

	public static int maxsub(int[] LDS) {
		int m = 0;
		for (int i = 0; i < LDS.length; i++) {
			if (LDS[i] > m)
				m = LDS[i];
		}
		return m;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 9, 4, 3, 2, 5, 4, 3, 2 };
		/*		System.out.println("����Ϊ:");
		 print(array);*/
		maxSubstrDecrease(array);
	}
}
