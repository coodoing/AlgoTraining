package DynamicProgramming;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LCSMemo {
	static Set<Character> lcs = new HashSet<Character>() ;
	static int LCS_Memo(int[][] c,char[] X,char[] Y,int i,int j)  
	{  
	    if( (i == 0) || (j == 0) )  
	        c[i][j]=0;  
	    else if(X[i-1] == Y[j-1])  
	    {	    	
	        c[i][j]=LCS_Memo(c,X,Y,i-1,j-1)+1;
	        //lcs.add(X[i-1]);
	        //System.out.print(X[i-1] + "\t");
	    }
	    else  
	    {  
	        int p=LCS_Memo(c,X,Y,i-1,j);  
	        int q=LCS_Memo(c,X,Y,i,j-1);  
	        if(p >= q)  
	            c[i][j]=p;  
	        else  
	            c[i][j]=q;  
	    }  	      
	    return c[i][j];  
	}  
	
	static int LCS_Length(int[][] c,char[] X,char[] Y)  
	{  
	    int m=X.length;  
	    int n=Y.length;  
	    return LCS_Memo(c,X,Y,m,n);  
	}  
	
	static void printC(int[][] c) {
		// TODO Auto-generated method stub
		for (int i = 1; i < c.length; i++) {
			for (int j = 1; j < c.length; j++) {
				System.out.print(c[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	static void printLCS(Set<Character> lcs)  
	{  
		 Iterator<Character> iterator = lcs.iterator();  
		 while(iterator.hasNext())
		 {
			 System.out.print(iterator.next() + "\t");
		 }
	} 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String X = "BCDB";
		String Y = "ABCBDAB";
		char[] x = X.toCharArray();
		char[] y = Y.toCharArray();
		
		int MAX = x.length>y.length?x.length+1:y.length+1;
		//c二维数组存放的是LCS的长度
		int[][] c = new int[MAX][MAX];
		System.out.println("LCS长度为："+LCS_Length(c,x,y));
		printC(c);	
		
	}
}
