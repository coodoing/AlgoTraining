package DynamicProgramming;

// ��̬�滮ʵ��01��������
// �ǵݹ����:http://blog.csdn.net/livelylittlefish/article/details/2186206
// �ݹ���⣺http://www.cnblogs.com/justinzhang/archive/2012/04/10/2441199.html
public class DP_Knapsack01 {

	int[] weight;
	int[] value;
	int max;

	int[][] p;
	
	boolean[] selection ; //�����Ƿ�ѡ��

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
		//��ʼ����0��
	    for(i=0;i<row;i++)
	        p[i][0]=0;
		
	    // ��ʾ��ʼʱ��ûװ����Ʒʱ������ֵΪ0
	    for(j=0;j<col;j++)
	        p[0][j]=0;
	    
	}

	/*
	 * DP:ѡ�����������ӽṹ����
	 * p[i][j]��ʾǰi����Ʒ��װ��������Ϊj�ı����е�����ֵ
	 * p[i][j] = max{p[i-1][j],value[i]+p[i-1][j-weight[i]]}
	 * */
	/*
	 * �öγ����������n���׶Σ�
     1��ֻװ��1����Ʒ��ȷ���ڸ��ֲ�ͬ�������ı����£��ܹ��õ�������ֵ
     2��װ��2����Ʒ��ȷ���ڸ��ֲ�ͬ�������ı����£��ܹ��õ�������ֵ
       ������
     n���Դ����ƣ�װ��n����Ʒ��ȷ���ڸ��ֲ�ͬ�������ı����£��ܹ��õ�������ֵ
	 * */
	private void knapsack() {
		int i, j;
		for (i = 1; i < weight.length; i++) {
			for (j = 1; j < max+1; j++) {
				//weight[i]>j,��i����Ʒ��װ�뱳��
				p[i][j] = p[i - 1][j];
				//weight[i]<=j,�ҵ�i����Ʒװ�뱳����ļ�ֵ>value[i-1][j],���¼��ǰ����ֵ
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
	
	/* ��װ�����Ʒ
	 * ���ƴ�p[n][max]��ǰ���ơ�
	 * ��p[n][max]>p[n-1][max]�����n����Ʒ��װ�뱳������ǰn-1����Ʒ
	 * ��װ��������Ϊmax-weight[n]�ı����С�
        ���򣬵�n����Ʒû��װ�뱳������ǰn-1����Ʒ��װ��������Ϊmax�ı����С�
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
		
		System.out.println("��̬�滮��Ӧ��valueֵ�ֱ�Ϊ��");
		for(int i= 0;i<len;i++)
		{
			if(knap.selection[i])
				System.out.print(knap.value[i]+" ");
		}
		System.out.println();
		System.out.println("��̬�滮��Ӧ��weightֵ�ֱ�Ϊ��");
		for(int i= 0;i<len;i++)
		{
			if(knap.selection[i])
				System.out.print(knap.weight[i]+" ");
		}
	}

}
