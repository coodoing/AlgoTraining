package Sorting.CommonSort;

import java.util.Random;

// http://rdc.taobao.com/team/jm/archives/252
public class QuickSort {

	static Random rand = new Random();

	// �ο��㷨����
	// ֻʹ����һ������i��i��������ɨ�裬������pivotС�ģ��͸���p+1��ʼ��λ�ã���j�������е����� ־�����н��������յĻ��ֵ�������j��Ȼ��pivot������j�ϣ��ٵݹ������������������С�
	static int partition(int[] arr, int low, int high) {
		// ѡ����˵�Ϊpivot
		int pivot = arr[low];
		int i = low + 1;
		int j = low + 1;
		for (; j <= high; j++) {
			// С��pivot�ķŵ����
			if (arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		// �����������̺󣬵õ��Ľ��Ϊ��ARR[pivot,x1,x2,x3,y1,y2,..]
		// ����pivotΪ�ᣬx1,x2,x3ΪС��pivot��ֵ��y1,y2...Ϊ����pivot��ֵ
		// ���i-1�x3��pivot�������õ�ARR[x1,x2,x3,pivot,y1,y2,..]�����һ�ο���
		// ������˵��pivotλ��
		swap(arr, low, i - 1);
		return i - 1;
	}
	
	static void quickSort(int[] arr, int low, int high) {
		int index;
		// ���ȴ���1
		if (low < high) {
			index = partitionImprove3(arr, low, high);
			//System.out.println("pivotλ���ǣ�"+index);
			quickSort(arr, low, index - 1);
			quickSort(arr, index + 1, high);
		}
	}

	static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] = { 2, 568, 34, 46, 9, 23, 89, 43, 572, 684, 783, 543 };
		System.out.println("��������ǰ��");
		print(arr);
		quickSort(arr, 0, arr.length - 1);
		System.out.println("���������");
		print(arr);

	}
	
	// ����ȡ������������
	static int med3(int arr[], int a, int b, int c) {
	    return arr[a] < arr[b] ? (arr[b] < arr[c] ? b : arr[a] < arr[c] ? c : a)
	            : arr[b] > arr[c] ? b : arr[a] > arr[c] ? c : a;
	}

	// ���ѡ��pivot��λ��
	static int partitionImprove1(int[] arr, int low, int high) {
		// ���ѡ��pivot
		int rdm = low + rand.nextInt(high - low + 1);
		swap(arr, low, rdm);

		return partition(arr, low, high);
	}
	
	// ʹ����������i��j���ֱ�������� �˽���ɨ�裬iɨ�赽���ڵ���pivot��Ԫ�ؾ�ֹͣ��jɨ�赽С�ڵ���pivot��Ԫ��Ҳֹͣ����������Ԫ�أ������������ֱ������������������ʱ�� pivot��λ�þ�������j��Ȼ�󽻻�pivot��j��λ��
	static int partitionImprove2(int[] arr, int low, int high) {
		int pivot = arr[low];
		//�ӱ����˽������м�ɨ��,ֱ��low==high��������ʱλ�ò���������λ�ã������˶�ν�������
		while (low < high) {
			while (low < high && arr[high] >= pivot)
				--high;
			arr[low] = arr[high];
			while (low < high && arr[low] <= pivot)
				++low;
			arr[high] = arr[low];
		}

		arr[low] = pivot;
		return low;
	}

	// �������СС��7�������ʹ�ò�������
	static int partitionImprove3(int[] arr, int low, int high) {
		// �������СС��7�������ʹ�ò�������
		/*if (high - low + 1 < 7) {
			for (int i = low; i <= high; i++) {
				for (int j = i; j > low && arr[j - 1] > arr[j]; j--) {
					swap(arr, j, j - 1);
				}
			}
		}*/
		
		// �������СС��7�������ʹ�ò�������
		if (high - low + 1 < 7) {
			for (int i = low; i < high - low + 1; i++) {
				int x = arr[i];
				int j;
				for (j = i - 1; j >= 0 && x < arr[j]; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = x;
			}
		}
		
		// ���ѡ��pivot
		int rdm = low + rand.nextInt(high - low + 1);
		swap(arr, low, rdm);
		
		return partitionImprove2(arr, low, high);
	}

	// ��С����40������ʹ��median-of-nineѡ��pivot����С��7��40֮�������ʹ��three-of-medianѡ��pivot,
	// ��С����7������ֱ��ѡ��������Ϊpivot����СС��7��������ֱ��ʹ�ò�������
	static int partitionImprove4(int[] arr, int low, int high) {

		// �������СС��7�������ʹ�ò�������
		if (high - low + 1 < 7) {
			for (int i = low; i <= high; i++) {
				for (int j = i; j > low && arr[j - 1] > arr[j]; j--) {
					swap(arr, j, j - 1);
				}
			}
		}
		
		// �������鳤��
        int len = high - low + 1;
        // ����е㣬��С����7������ѡ��pivot
        int m = low + (len >> 1);
        // ��С����7
        if (len > 7) {
            int l = low;
            int n = low + len - 1;
            if (len > 40) { // �����飬����median-of-nineѡ��
                int s = len / 8;
                l = med3(arr, l, l + s, l + 2 * s); // ȡ����˵�3�������ó�����
                m = med3(arr, m - s, m, m + s); // ȡ���е�3�������ó�����
                n = med3(arr, n - 2 * s, n - s, n); // ȡ���Ҷ˵�3�������ó�����
            }
            m = med3(arr, l, m, n); // ȡ�����е�����
        }
        // ����pivot����˵㣬����Ĳ�����qsort4��ͬ
        swap(arr, low, m);

		return partitionImprove2(arr, low, high);
	}
	
	// split-end����ظ�Ԫ�ع�������
	static int partitionImprove5(int[] arr, int low, int high) {
		

		return partitionImprove2(arr, low, high);
	}
}
