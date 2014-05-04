package Sorting.CommonSort;

public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[]{4,5,6,8,2,9,1,3,7};
		System.out.println("选择排序前");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
		selectionSort(a,a.length);
		System.out.println("\n选择排序后");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
	}
	
	static void selectionSort(int[] arr,int len)
	{
		int j;
		for(int i=0;i<len;i++)
		{
			j = getMinKey(arr,i);
			if(i!=j)
				swap(arr,j,i);
		}		
	}
	
	static void swap(int[] arr,int i,int j)
	{
		int temp;
		temp = arr[i];
		arr[i]= arr[j];
		arr[j] = temp;
	}

	static int getMinKey(int[] arr,int start)
	{
		int min = start;
		for(int i =start+1;i<arr.length;i++)
		{
			if(arr[min]>arr[i])
				min = i;
		}		
		return min;
	}
}
