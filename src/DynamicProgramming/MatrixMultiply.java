package DynamicProgramming;

/** 
 * 下面是矩阵连乘问题的动态规划算法 
 * 假设有6个矩阵： 
 *   A1    A2   A3   A4    A5   A6 
 * 30*35 35*15 15*5 5*10 10*20 20*25 则matrixChain为 
 * {30, 35, 15, 5, 10, 20, 25} 结果为 
 * ((A1 * (A1 * A2)) * ((A4 * A5) * A6) ) 
 *  
 *  注释：
 *  http://blog.csdn.net/liguisen/article/details/2158127
 *  http://blog.csdn.net/kay_sprint/article/details/7220573
 */  
public class MatrixMultiply {
	// Traceback打印A[i:j]的加括号方式
	public static void traceback(int[][] s, int i, int j) {
		//s[i][j]记录了断开的位置，即计算A[i:j]的加括号方式为：
	    //(A[i:s[i][j]])*(A[s[i][j]+1:j])
		if (i == j)
			return;
		traceback(s, i, s[i][j]);//递归打印A[i:s[i][j]]的加括号方式
		traceback(s, s[i][j] + 1, j);//递归打印A[s[i][j]+1:j]的加括号方式
		System.out.println("Multiply A(" + i + "," + s[i][j] + ")and A("
				+ (s[i][j] + 1) + "," + j+")");

	}

	public static void matrixChain(int[] p, int[][] m, int[][] s) {
		int n = p.length - 1;
		for (int i = 0; i <= n; i++)
			m[i][i] = 0;
		// 上三角矩阵
		for (int r = 2; r <= n; r++)//r为连乘矩阵的个数   
			for (int i = 1; i <= n - r + 1; i++) {//i就是连续r个矩阵的第一个 
				int j = i + r - 1;//j就是连续r个矩阵的最后一个   
				m[i][j] = 99999999;//m[i + 1][j] + p[i - 1] * p[i] * p[j];
				s[i][j] = i;
				//求m[i][j],m[i][j]就是Ai...Aj这 j-i+1 个矩阵连乘需要的最少的乘法次数
				for (int k = i; k < j; k++) {
					//A[i]的维数为：p[i - 1] * p[k]
					int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (t < m[i][j]) {//寻找最小值 
						m[i][j] = t; 
						s[i][j] = k;//记录划分标记  
					}
				}
			}
		
		
		//n个矩阵连乘的最少相乘次数
		System.out.println("给定的"+n+"个矩阵连乘的最少相乘次数:"+m[1][n]);
		printM(m);
		printS(s);
	}

	private static void printM(int[][] m) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private static void printS(int[][] s) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				System.out.print(s[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		int[][] m = new int[7][7];
		int[][] s = new int[7][7];
		int[] p = new int[] { 30, 35, 15, 5, 10, 20, 25 };
		matrixChain(p, m, s);
		System.out.println("语法树为：");
		//((A1(A2A3))((A4A5)A6))
		traceback(s, 1, 6);
	
	}
}