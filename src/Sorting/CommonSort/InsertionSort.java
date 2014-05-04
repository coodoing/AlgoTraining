package Sorting.CommonSort;

public class InsertionSort {
	
	static void insertSort(int[] a)
	{
		int n = a.length;
		for(int i =1;i<n;i++)
		{
			int p = a[i];
			insert(a,i,p);
		}
	}	
	
	static void insert(int[] a, int index, int x) {
		//元素插入数组a[0:index-1]
		int i;
		for(i=index-1;i>=0&&x<a[i];i--)
		{
			a[i+1] = a[i];
		}
		a[i+1]= x;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[]{4,5,6,8,2,9,1,3,7};
		System.out.println("插入排序前");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
		insertSort(a);
		System.out.println("\n插入排序后");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}

	}

}
