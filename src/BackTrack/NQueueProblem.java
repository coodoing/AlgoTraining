package BackTrack;

// 皇后问题等价于求所有满足条件的子集树

/*
 * 回溯法解N皇后问题
 * 使用一个一维数组表示皇后的位置
 * 其中数组的下标表示皇后所在的行
 * 数组元素的值表示皇后所在的列
 * 这样设计的棋盘，所有皇后必定不在同一行
 *
 * 假设前n-1行的皇后已经按照规则排列好
 * 那么可以使用回溯法逐个试出第n行皇后的合法位置
 * 所有皇后的初始位置都是第0列
 * 那么逐个尝试就是从0试到N-1
 * 如果达到N，仍未找到合法位置
 * 那么就置当前行的皇后的位置为初始位置0
 * 然后回退一行，且该行的皇后的位置加1，继续尝试
 * 如果目前处于第0行，还要再回退，说明此问题已再无解
 *
 * 如果当前行的皇后的位置还是在0到N-1的合法范围内
 * 那么首先要判断该行的皇后是否与前几行的皇后互相冲突
 * 如果冲突，该行的皇后的位置加1，继续尝试
 * 如果不冲突，判断下一行的皇后
 * 如果已经是最后一行，说明已经找到一个解，输出这个解
 * 然后最后一行的皇后的位置加1，继续尝试下一个解
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

	// 递归回溯方法进行求解 
	// 将皇后置于第t行
	private void backtrack(int t) {
		if (t == n) {
			sum++;
			//输出当前解 
			printSolution();
		} else {
			for (int j = 0; j < n; j++) {
				yPos[t] = j; // 皇后放置在第t行j列  
				if (isPlace(t)) {
					// 考虑第t+1行
					backtrack(t + 1);
				}
			}
		}
	}

	// 迭代回溯实现，迭代一步步深入
	// 采用树的非递归深度优先遍历算法，将回溯法表示为一个非递归迭代过程
	private void iterativeBacktrack() {
		int t = 0; //从第0行开始迭代  
		yPos[0] = -1;
		while (t>=0) {
			yPos[t] += 1; // 向右移一列
			/* 向右移至出最右列或可以放置皇后的列 */
			while ((yPos[t] <n) && !(isPlace(t))) {
				yPos[t] += 1;
			}
			// 向右移未移出棋盘 
			if (yPos[t] < n)
			{
				// 已移至最后一行  
				if (t == n-1) { 
					sum++;   
					printSolution();
				} 
				else {
					/* 向下移一行 */  
					t++;
					yPos[t] = -1;
				}
			} 
			else {
				// 即yPos[t]>n, 迭代到最后一列仍无解，则回溯到前一行  
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
		System.out.println("递归回溯结果");
		//long start = System.currentTimeMillis();		
		queen.backtrack(0);
		//long end = System.currentTimeMillis();
		//System.out.println("time=" + (end - start));
		//System.out.println("sum=" + sum);
		System.out.println("迭代回溯结果");
		queen.iterativeBacktrack();
		System.out.println(1 << 8);
		// 十进制的负数用二进制表示就是补码：
		// 补码：
		// 原码取反（反码），反码加一
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
	int sum;// 排列个数
	ArrayList<Queue> solution; // 当前的解决方案
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
