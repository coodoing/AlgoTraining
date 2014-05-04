package Sorting;

import java.util.Random;

public class RandomQuickSort {

	public static void quicksort( int[] a, int p, int r ) {
        if ( p < r ) {
            int q = randomizedPartition( a, p, r );
            quicksort( a, p, q );
            quicksort( a, q + 1, r );
        }
    }

    private static int randomizedPartition(int[] a, int p, int r) {
        int i = p + new Random().nextInt(r-p) + 1;
        swap(a, p, i);
        return partition(a, p, r);
    }

    private static int partition( int[] a, int p, int r ) {
        int pivot = a[p];
        int i = p-1;
        int j = r+1;
        while(true) {
            do j--; while(a[j] > pivot); //grow greater region
            do i++; while(a[i] < pivot); //grow lesser region
            if(i < j)
                swap(a, i, j);
            else
                return j;
        }
    }

    private static void swap( int[] a, int i, int j ) {
        int tmp = a[ i ];
        a[ i ] = a[ j ];
        a[ j ] = tmp;
    }
 
    public static void main( String[] args ) {
        int ar[] = new int[100];     
        for(int i = 0; i < ar.length; i++)
            ar[i] = ar.length-i;
        System.out.println("First 20 elements");
        System.out.print("Before sort: ");
        for(int i = 0; i < 100; i++)
            System.out.print(ar[i]+" ");
        System.out.println();
        quicksort(ar, 0, ar.length-1);
        System.out.print("After sort: ");
        for(int i = 0; i < 100; i++)
            System.out.print(ar[i]+" ");
               
    }
}
