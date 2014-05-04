package Greedy;

import java.util.Stack;

public class Dijkstra {
	static int MAX_SIZE = 6;

	static void dijkstra(int startVertex, float[][] weight, float[] minDist,
			int[] prev, int[][] path) {
		int n = minDist.length - 1;
		if (startVertex < 1 || startVertex > n)
			return;
		boolean[] s = new boolean[n + 1]; // 是否将蓝点集加入红点集的标志

		// startVertex为顶点1
		minDist[startVertex] = 0; // 初始红点集
		s[startVertex] = true;
		for (int i = 2; i <= n; i++) {
			minDist[i] = weight[startVertex][i]; // 设置初始距离为weight<s,i>
			s[i] = false;
			if (minDist[i] == Float.MAX_VALUE) {
				prev[i] = 0;
				path[i][startVertex] = 0;
			} else {
				prev[i] = startVertex;
				path[i][startVertex] = 1;
			}
		}
		// 扩充红点集
		for (int i = 1; i < n; i++) {
			float temp = Float.MAX_VALUE;
			int u = startVertex;
			// 在当前蓝点集中选估计距离最小的顶点j
			for (int j = 2; j <= n; j++) {
				if ((!s[j]) && (minDist[j] < temp)) {
					u = j;// k是V集合中具有最短“特殊路径的顶点”,所谓特殊路径即是从顶点v到k只经过U中的顶点
					temp = minDist[j];
				}
			}

			// 如果仍为无穷大，则说明为不连通图
			if (Float.MAX_VALUE == temp)
				return;

			s[u] = true; // 将蓝点集u扩充到红点集
			// 调整源点s与剩余蓝点之间的距离
			for (int j = 2; j <= n; j++)
				if ((!s[j]) && (weight[u][j] < Float.MAX_VALUE)) {
					float newDist = minDist[u] + weight[u][j];
					if (newDist < minDist[j]) {
						minDist[j] = newDist;
						prev[j] = u;

						path[j][startVertex] = u;
					}
				}
		}
	}

	// 做一个压栈处理
	static void printPath(int[][] path, int startVertex, int endVertex,
			Stack<Integer> pathStack) {
		if (path[endVertex][startVertex] != 0)// &&path[endVertex][startVertex]!=startVertex
		{
			pathStack.push(endVertex);
			int temp = path[endVertex][startVertex];
			printPath(path, startVertex, temp, pathStack);
			System.out.print(pathStack.pop() + " ");
		}
	}

	public static void main(String args[]) {
		float weight[][] = new float[MAX_SIZE][MAX_SIZE];// 边权
		float[] minDist = new float[MAX_SIZE]; // 源点到其它顶点的最短距离数组
		int[] prev = new int[MAX_SIZE]; // s到t经过顶点的集合
		int[][] path = new int[MAX_SIZE][MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++)
			for (int j = 0; j < MAX_SIZE; j++)
				weight[i][j] = Float.MAX_VALUE;
		for (int i = 0; i < MAX_SIZE; i++)
			for (int j = 0; j < MAX_SIZE; j++)
				path[i][j] = 0;
		weight[1][2] = 10;
		weight[1][4] = 30;
		weight[1][5] = 100;
		weight[2][3] = 50;
		weight[3][5] = 10;
		weight[4][3] = 20;
		weight[4][5] = 60;
		int startVertex = 1;// 假设从顶点1处出发

		dijkstra(startVertex, weight, minDist, prev, path);
		System.out.println("从1出发到2、3、4、5的最短路径依次是:");
		for (int j = 2; j < MAX_SIZE; j++) {
			System.out.println(minDist[j]);
		}
		/*
		 * for (int i = 2; i < MAX_SIZE; i++) { System.out.print(prev[i] + " "); }
		 * System.out.println();
		 */
		System.out.println("path矩阵如下：");
		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("从1到5最短路径经过的点为：");
		int endVertex = 5;
		Stack<Integer> stack = new Stack<Integer>();
		System.out.print(startVertex + " ");
		printPath(path, startVertex, endVertex, stack);

	}
}
