package Strings;

public class NonRecursivePermutation {

	static int[] arr = new int[] { 1, 2, 3, 4, 5, 6 };
	static int count = 1;

	// ��ת����
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
		int replace = 0; // ���滻��
		int firstR = 0; // �Ӻ���ǰ�ұ��滻����ĵ�һ����
		// �ҽ��������2��,ǰһ���������滻��
		for (int i = len - 1; i > 0; i--) {
			if (arr[i] > arr[i - 1]) {
				replace = i - 1;
				break;
			}
		}

		//System.out.println("���滻��λ�ã�" + replace);

		// �Ӻ���ǰ�ұ��滻����ĵ�һ����
		// ����926540�ĺ��Ӧ����946520,������956240
		for (int i = len - 1; i > replace; i--) {
			if (arr[i] > arr[replace]) {
				firstR = i;
				break; //�ҵ�֮��ֱ���˳�
			}
		}

		//System.out.println("�滻��λ�ã�" + firstR);

		// replace��firstR����
		swap(arr, replace, firstR);
		// �滻�������ȫ����ת
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
		System.out.println("������ȫ��ת��Ľ����");
		reverse(arr, 0, arr.length - 1);
		print();
		System.out.println("ȫ���е���ĿΪ��" + count);

		/*********************/
		System.out.println("�����Ƿ���ں�̣�" + hasNext());
		System.out.println("����һ���滻��Ľ����");
		next();
	}

}
