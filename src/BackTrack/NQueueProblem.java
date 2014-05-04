package BackTrack;

// �ʺ�����ȼ��������������������Ӽ���

/*
 * ���ݷ���N�ʺ�����
 * ʹ��һ��һά�����ʾ�ʺ��λ��
 * ����������±��ʾ�ʺ����ڵ���
 * ����Ԫ�ص�ֵ��ʾ�ʺ����ڵ���
 * ������Ƶ����̣����лʺ�ض�����ͬһ��
 *
 * ����ǰn-1�еĻʺ��Ѿ����չ������к�
 * ��ô����ʹ�û��ݷ�����Գ���n�лʺ�ĺϷ�λ��
 * ���лʺ�ĳ�ʼλ�ö��ǵ�0��
 * ��ô������Ծ��Ǵ�0�Ե�N-1
 * ����ﵽN����δ�ҵ��Ϸ�λ��
 * ��ô���õ�ǰ�еĻʺ��λ��Ϊ��ʼλ��0
 * Ȼ�����һ�У��Ҹ��еĻʺ��λ�ü�1����������
 * ���Ŀǰ���ڵ�0�У���Ҫ�ٻ��ˣ�˵�������������޽�
 *
 * �����ǰ�еĻʺ��λ�û�����0��N-1�ĺϷ���Χ��
 * ��ô����Ҫ�жϸ��еĻʺ��Ƿ���ǰ���еĻʺ����ͻ
 * �����ͻ�����еĻʺ��λ�ü�1����������
 * �������ͻ���ж���һ�еĻʺ�
 * ����Ѿ������һ�У�˵���Ѿ��ҵ�һ���⣬��������
 * Ȼ�����һ�еĻʺ��λ�ü�1������������һ����
 */
public class NQueueProblem {

	private int[] yPos;
	private int n;
	private static int sum;

	public NQueueProblem(int n) {
		this.n = n;
		init(n);
	}

	private void init(int n) {
		yPos = new int[n];
		for (int i = 0; i < n; i++) {
			yPos[i] = 0;
		}
	}

	// �ݹ���ݷ���������� 
	// ���ʺ����ڵ�t��
	private void backtrack(int t) {
		if (t == n) {
			sum++;
			//�����ǰ�� 
			printSolution();
		} else {
			for (int j = 0; j < n; j++) {
				yPos[t] = j; // �ʺ�����ڵ�t��j��  
				if (isPlace(t)) {
					// ���ǵ�t+1��
					backtrack(t + 1);
				}
			}
		}
	}

	// ��������ʵ�֣�����һ��������
	// �������ķǵݹ�������ȱ����㷨�������ݷ���ʾΪһ���ǵݹ��������
	private void iterativeBacktrack() {
		int t = 0; //�ӵ�0�п�ʼ����  
		yPos[0] = -1;
		while (t>=0) {
			yPos[t] += 1; // ������һ��
			/* ���������������л���Է��ûʺ���� */
			while ((yPos[t] <n) && !(isPlace(t))) {
				yPos[t] += 1;
			}
			// ������δ�Ƴ����� 
			if (yPos[t] < n)
			{
				// ���������һ��  
				if (t == n-1) { 
					sum++;   
					printSolution();
				} 
				else {
					/* ������һ�� */  
					t++;
					yPos[t] = -1;
				}
			} 
			else {
				// ��yPos[t]>n, ���������һ�����޽⣬����ݵ�ǰһ��  
				t--;
			}
		}
	}

	private boolean isPlace(int t) {
		for (int i = 0; i < t; i++) {
			if ((Math.abs(i - t) == Math.abs(yPos[i] - yPos[t]))
					|| (yPos[i] == yPos[t])) {
				return false;
			}
		}
		return true;
	}

	void printSolution() {
		for (int i = 0; i < n; i++) {
			System.out.print("[" + i + "," + yPos[i] + "]" + " ");
			if (i == n - 1) {
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		NQueueProblem queen = new NQueueProblem(8);
		System.out.println("�ݹ���ݽ��");
		//long start = System.currentTimeMillis();		
		queen.backtrack(0);
		//long end = System.currentTimeMillis();
		//System.out.println("time=" + (end - start));
		//System.out.println("sum=" + sum);
		System.out.println("�������ݽ��");
		queen.iterativeBacktrack();
		System.out.println(1 << 8);
		// ʮ���Ƶĸ����ö����Ʊ�ʾ���ǲ��룺
		// ���룺
		// ԭ��ȡ�������룩�������һ
		System.out.println(~(-5 | 5));
	}

}


/*class Queue {
	int x;
	int y;

	public Queue(int xPos, int yPos) {
		this.x = xPos;
		this.y = yPos;
	}
}

class Queues {
	int sum;// ���и���
	ArrayList<Queue> solution; // ��ǰ�Ľ������
	int queues;

	public Queues(int queues) {
		this.queues = queues;
		solution = new ArrayList<Queue>();
	}

	boolean place(int pos) {
		for (int i = 0; i < pos; i++) {
			if (Math.abs(pos - i) == Math.abs(solution.get(i).y
					- solution.get(pos).y))
				return false;
		}
		return true;
	}
}*/
