package Sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LastQS {
	static int coutingLast = 0;
		
	static int partition(int[] arr,int l, int r)
	{
		int pivot = arr[l];
		int i = l+1;
		int j = l+1;
		for(;j<=r;j++)
		{
			if(arr[j]<pivot)
			{
				swap(arr,i,j);
				i++;
			}
			coutingLast++;
		}
		swap(arr,l,i-1);
		return i-1;
	}
	
	static int lastPartition(int[] arr,int l, int r)
	{		
		swap(arr,l,r);
		return partition(arr,l,r);
	}
	
	static void swap(int[] arr,int i,int j)
	{
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void quickSort2(int[] arr,int low, int high)
	{
		int index;		
		if(low<high)
		{
			index = lastPartition(arr,low,high);
			quickSort2(arr,low,index-1);
			quickSort2(arr,index+1,high);
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			//测试数据：QuickSort 2array0
			File file = new File("E:/PRO/Java/Data/QuickSort.txt");
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
		
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = Integer.parseInt(list.get(i));
		}
		
		quickSort2(array,0,array.length-1);
		System.out.println("\nlast比较次数:"+coutingLast);		
	}
}