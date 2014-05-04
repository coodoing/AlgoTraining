package Sorting.CommonSort;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[]{4,5,6,8,2,9,1,3,7};
		System.out.println("√∞≈›≈≈–Ú«∞");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
		bubbleSort(a);
		System.out.println("\n√∞≈›≈≈–Ú∫Û");
		for(int i =0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
	}

	public static int[] bubbleSort(int[] numbers) {
	    /*boolean swapped = true;
	    for(int i = numbers.length - 1; i > 0 && swapped; i--) {
	        swapped = false;
	        for (int j = 0; j < i; j++) {
	            if (numbers[j] > numbers[j+1]) {
	                int temp = numbers[j];
	                numbers[j] = numbers[j+1];
	                numbers[j+1] = temp;
	                swapped = true;
	            }
	        }
	    }*/
	    /*for(int i = numbers.length - 1; i > 0; i--) {
	        for (int j = 0; j < i; j++) {
	            if (numbers[j] > numbers[j+1]) {
	                int temp = numbers[j];
	                numbers[j] = numbers[j+1];
	                numbers[j+1] = temp;
	            }
	        }
	    }*/
		// √∞≈›≈≈–ÚÀ„∑®£∫¥”◊Û÷¡”“£¨¥Û ˝∫Û“∆°£
		for(int i = numbers.length - 1; i > 0; i--) {
	        for (int j = 0; j < i; j++) {
	            if (numbers[j] > numbers[j+1]) {
	                int temp = numbers[j];
	                numbers[j] = numbers[j+1];
	                numbers[j+1] = temp;
	            }
	        }
	    }
	    return numbers;
	}
}
