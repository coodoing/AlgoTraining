package DynamicProgramming;

/** 
 * �����Ǿ�����������Ķ�̬�滮�㷨 
 * ������6������ 
 *   A1    A2   A3   A4    A5   A6 
 * 30*35 35*15 15*5 5*10 10*20 20*25 ��matrixChainΪ 
 * {30, 35, 15, 5, 10, 20, 25} ���Ϊ 
 * ((A1 * (A1 * A2)) * ((A4 * A5) * A6) ) 
 *  
 *  ע�ͣ�
 *  http://blog.csdn.net/liguisen/article/details/2158127
 *  http://blog.csdn.net/kay_sprint/article/details/7220573
 */  
public class MatrixMultiply {
	// Traceback��ӡA[i:j]�ļ����ŷ�ʽ
	public static void traceback(int[][] s, int i, int j) {
		//s[i][j]��¼�˶Ͽ���λ�ã�������A[i:j]�ļ����ŷ�ʽΪ��
	    //(A[i:s[i][j]])*(A[s[i][j]+1:j])
		if (i == j)
			return;
		traceback(s, i, s[i][j]);//�ݹ��ӡA[i:s[i][j]]�ļ����ŷ�ʽ
		traceback(s, s[i][j] + 1, j);//�ݹ��ӡA[s[i][j]+1:j]�ļ����ŷ�ʽ
		System.out.println("Multiply A(" + i + "," + s[i][j] + ")and A("
				+ (s[i][j] + 1) + "," + j+")");

	}

	public static void matrixChain(int[] p, int[][] m, int[][] s) {
		int n = p.length - 1;
		for (int i = 0; i <= n; i++)
			m[i][i] = 0;
		// �����Ǿ���
		for (int r = 2; r <= n; r++)//rΪ���˾���ĸ���   
			for (int i = 1; i <= n - r + 1; i++) {//i��������r������ĵ�һ�� 
				int j = i + r - 1;//j��������r����������һ��   
				m[i][j] = 99999999;//m[i + 1][j] + p[i - 1] * p[i] * p[j];
				s[i][j] = i;
				//��m[i][j],m[i][j]����Ai...Aj�� j-i+1 ������������Ҫ�����ٵĳ˷�����
				for (int k = i; k < j; k++) {
					//A[i]��ά��Ϊ��p[i - 1] * p[k]
					int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (t < m[i][j]) {//Ѱ����Сֵ 
						m[i][j] = t; 
						s[i][j] = k;//��¼���ֱ��  
					}
				}
			}
		
		
		//n���������˵�������˴���
		System.out.println("������"+n+"���������˵�������˴���:"+m[1][n]);
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
		System.out.println("�﷨��Ϊ��");
		//((A1(A2A3))((A4A5)A6))
		traceback(s, 1, 6);
	
	}
}