package Sorting.CommonSort;

//��������
public class HeapSort {

	public static void buildHeap(int a[]) {
		int heapSize = a.length;
		int filter = (int) Math.floor(heapSize / 2);
		// i�ӵ�һ����Ҷ�ӽ�㿪ʼ
		for (int i = filter - 1; i >= 0; i--) {
			heapAdjust(a, i, heapSize);
		}
	}

	public static void heapAdjust(int arr[], int i, int heapSize) {
		// ��ǰ��������Ԫ��
		int tmp = arr[i];
		// ��Ԫ�ص�����
		int index = 2 * i + 1;
		while (index < heapSize) {
			// ����Һ��Ӵ�������,��index+1���������Һ��Ӻ�˫�׽ڵ�
			if (index + 1 < heapSize && arr[index] < arr[index + 1]) {
				index = index + 1;
			}
			if (arr[i] < arr[index]) {
				// �������Ӻ�˫�׽ڵ�
				arr[i] = arr[index];
				
				// ���¸���ֵ
				i = index;
				index = 2 * i + 1;
			} 
			// �Ѿ�������
			else {
				break;
			}
			// ��˫��ֵ�������ӽڵ�
			arr[i] = tmp;
		}
	}
	
	@SuppressWarnings("unused")
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
			// �����Ѷ������һ��Ԫ��
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			heapAdjust(a, 0, i); // ��heapSize��Χ�ڸ����������������Ѿ�������
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 6, 5, 3, 1, 8, 7, 2, 4 };
		buildHeap(arr);
		System.out.println("��ʼ������������:");
		for (int data : arr)
			System.out.print(data + " ");
		System.out.println();
		System.out.println("�Ѿ���ɸѡ������������Ϊ:");
		heapSort(arr);
		for (int data : arr)
			System.out.print(data + " ");
	}

}