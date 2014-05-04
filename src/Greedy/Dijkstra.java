package Greedy;

import java.util.Stack;

public class Dijkstra {
	static int MAX_SIZE = 6;

	static void dijkstra(int startVertex, float[][] weight, float[] minDist,
			int[] prev, int[][] path) {
		int n = minDist.length - 1;
		if (startVertex < 1 || startVertex > n)
			return;
		boolean[] s = new boolean[n + 1]; // �Ƿ����㼯�����㼯�ı�־

		// startVertexΪ����1
		minDist[startVertex] = 0; // ��ʼ��㼯
		s[startVertex] = true;
		for (int i = 2; i <= n; i++) {
			minDist[i] = weight[startVertex][i]; // ���ó�ʼ����Ϊweight<s,i>
			s[i] = false;
			if (minDist[i] == Float.MAX_VALUE) {
				prev[i] = 0;
				path[i][startVertex] = 0;
			} else {
				prev[i] = startVertex;
				path[i][startVertex] = 1;
			}
		}
		// �����㼯
		for (int i = 1; i < n; i++) {
			float temp = Float.MAX_VALUE;
			int u = startVertex;
			// �ڵ�ǰ���㼯��ѡ���ƾ�����С�Ķ���j
			for (int j = 2; j <= n; j++) {
				if ((!s[j]) && (minDist[j] < temp)) {
					u = j;// k��V�����о�����̡�����·���Ķ��㡱,��ν����·�����ǴӶ���v��kֻ����U�еĶ���
					temp = minDist[j];
				}
			}

			// �����Ϊ�������˵��Ϊ����ͨͼ
			if (Float.MAX_VALUE == temp)
				return;

			s[u] = true; // �����㼯u���䵽��㼯
			// ����Դ��s��ʣ������֮��ľ���
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

	// ��һ��ѹջ����
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
		float weight[][] = new float[MAX_SIZE][MAX_SIZE];// ��Ȩ
		float[] minDist = new float[MAX_SIZE]; // Դ�㵽�����������̾�������
		int[] prev = new int[MAX_SIZE]; // s��t��������ļ���
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
		int startVertex = 1;// ����Ӷ���1������

		dijkstra(startVertex, weight, minDist, prev, path);
		System.out.println("��1������2��3��4��5�����·��������:");
		for (int j = 2; j < MAX_SIZE; j++) {
			System.out.println(minDist[j]);
		}
		/*
		 * for (int i = 2; i < MAX_SIZE; i++) { System.out.print(prev[i] + " "); }
		 * System.out.println();
		 */
		System.out.println("path�������£�");
		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("��1��5���·�������ĵ�Ϊ��");
		int endVertex = 5;
		Stack<Integer> stack = new Stack<Integer>();
		System.out.print(startVertex + " ");
		printPath(path, startVertex, endVertex, stack);

	}
}
