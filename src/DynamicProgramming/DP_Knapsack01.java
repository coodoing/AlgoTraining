package DynamicProgramming;

// 动态规划实现01背包问题
// 非递归求解:http://blog.csdn.net/livelylittlefish/article/details/2186206
// 递归求解：http://www.cnblogs.com/justinzhang/archive/2012/04/10/2441199.html
public class DP_Knapsack01 {

	int[] weight;
	int[] value;
	int max;

	int[][] p;
	
	boolean[] selection ; //背包是否被选择

	public DP_Knapsack01() {
		/*weight = new int[] {0, 10, 8, 5 };
		value = new int[] {0, 5, 4, 1 };
		max = 16;*/
		weight = new int[] {0, 16, 15, 15 };
		value = new int[] {0, 45, 25, 25 };
		max = 30;
		selection = new boolean[weight.length];

		init();
	}

	private void init() {
		int row = weight.length;
		int col = max+1 ;
		int i, j;
		
		p = new int[row][col];
		//初始化第0列
	    for(i=0;i<row;i++)
	        p[i][0]=0;
		
	    // 表示初始时刻没装入物品时，最大价值为0
	    for(j=0;j<col;j++)
	        p[0][j]=0;
	    
	}

	/*
	 * DP:选择与否的最优子结构性质
	 * p[i][j]表示前i个物品能装入载重量为j的背包中的最大价值
	 * p[i][j] = max{p[i-1][j],value[i]+p[i-1][j-weight[i]]}
	 * */
	/*
	 * 该段程序完成以下n个阶段：
     1：只装入1个物品，确定在各种不同载重量的背包下，能够得到的最大价值
     2：装入2个物品，确定在各种不同载重量的背包下，能够得到的最大价值
       。。。
     n：以此类推，装入n个物品，确定在各种不同载重量的背包下，能够得到的最大价值
	 * */
	private void knapsack() {
		int i, j;
		for (i = 1; i < weight.length; i++) {
			for (j = 1; j < max+1; j++) {
				//weight[i]>j,第i个物品不装入背包
				p[i][j] = p[i - 1][j];
				//weight[i]<=j,且第i个物品装入背包后的价值>value[i-1][j],则记录当前最大价值
				int temp;
				if(j >=weight[i])
				{
					temp = p[i - 1][j - weight[i]] + value[i];
					if (temp > p[i][j])
						p[i][j] = temp;
				}				
			}
		}		
		printP();		
	}
	
	/* 求装入的物品
	 * 递推从p[n][max]向前逆推。
	 * 若p[n][max]>p[n-1][max]，则第n个物品被装入背包，且前n-1个物品
	 * 被装入载重量为max-weight[n]的背包中。
        否则，第n个物品没有装入背包，且前n-1个物品被装入载重量为max的背包中。
	 * */
	private void traceback()
	{
		int temp = max;
		int len = weight.length;
		for (int i = len-1; i >0; i--) { 
			if(p[i-1][temp]<p[i][temp])
			{
				selection[i] = true;	
				temp-=weight[i];
			}
		}
		/*for (int i = 1;i<len;i++) { 
			if(p[i][temp]>p[i-1][temp] )
			{
				selection[i] = true;	
				temp-=weight[i];
			}
			else
				selection[i] = false;
		}
		for(int i = 0;i<len;i++)
		{
			System.out.print(selection[i]+" ");
		}	*/	
	}
	
	private void printP()
	{
		int row = weight.length;
		int col = max+1 ;
		int i, j;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				System.out.print(p[i][j]+" ");
			}
			System.out.println();
		}
		
		//System.out.println(p[row-1][max]);		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DP_Knapsack01 knap = new DP_Knapsack01();
		knap.knapsack();
		
		knap.traceback();	
		int len = knap.weight.length;
		
		System.out.println("动态规划对应的value值分别为：");
		for(int i= 0;i<len;i++)
		{
			if(knap.selection[i])
				System.out.print(knap.value[i]+" ");
		}
		System.out.println();
		System.out.println("动态规划对应的weight值分别为：");
		for(int i= 0;i<len;i++)
		{
			if(knap.selection[i])
				System.out.print(knap.weight[i]+" ");
		}
	}

}
