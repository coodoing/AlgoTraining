package Sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/***
 * 
 http://stackoverflow.com/questions/337664/counting-inversions-in-an-array
 http://www.cs.princeton.edu/~wayne/cs423/lectures/divide-and-conquer-4up.pdf 
 divide and conquer
 * ****/
public class MergeCouting {

	static int merge(int[] arr, int[] left, int[] right) {
		int i = 0, j = 0, count = 0;
		while (i < left.length || j < right.length) {
			if (i == left.length) {
				arr[i + j] = right[j];
				j++;
			} else if (j == right.length) {
				arr[i + j] = left[i];
				i++;
			} else if (left[i] <= right[j]) {
				arr[i + j] = left[i];
				i++;
			} else {
				arr[i + j] = right[j];
				count += left.length - i;
				j++;
			}
		}
		return count;
	}

	static long invCount(int[] arr) {
		if (arr.length < 2)
			return 0;

		int m = (arr.length + 1) / 2;
		int left[] = Arrays.copyOfRange(arr, 0, m);
		int right[] = Arrays.copyOfRange(arr, m, arr.length);

		return invCount(left) + invCount(right) + merge(arr, left, right);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] array = new int[] { 1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7 };
		ArrayList<String> list = new ArrayList<String>();
		try {
			File file = new File("E:/IntegerArray.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
			fr.close();
		} catch (Exception ex) {
		}

		System.out.println("列表长度"+list.size());
		System.out.println("int型的最大整数值："+Integer.MAX_VALUE);

		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = Integer.parseInt(list.get(i));
		}
		int[] arraytest =new int[]{1,2,5,6,4,9,8,3,7,0};
		System.out.println("number of inversions："+invCount(arraytest));
	}
}
