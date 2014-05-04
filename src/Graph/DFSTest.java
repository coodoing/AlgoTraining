package Graph;

public class DFSTest {

	int[] visited;
	ALGraph graph = new ALGraph();

	private void createGraph(int[] vexs, int[][] arcs) {
		graph.createGraph(vexs, arcs);
		visited = new int[graph.vexNum];//new int[]{0};
	}

	public void DFSTraversal(ALGraph graph, int v) {
		for (int i = 0; i < graph.vexNum; i++)
			visited[i] = 0;
		System.out.println();
		visited[v] = 1;
		System.out.print(v + 1 + " ");
		DFS(graph, v);
	}

	private void DFS(ALGraph graph, int v) {
		VNode node = graph.vertices.get(v);
		ArcNode p = node.firstNode;
		if (node != null)//遍历完以及v头结点对应的链表不为空
		{
			while (p != null) {
				if (visited[p.vex - 1] == 0) {
					System.out.print(p.vex + " ");
					visited[p.vex - 1] = 1;
					DFS(graph, p.vex - 1);
				}
				//System.out.println("next"+p.vex);
				p = p.next;
			}
		} else {
			return;
		}

	}

	public static void main(String[] args) {
		DFSTest dfs = new DFSTest();
		int[] vexs = new int[5];
		int[][] arcs = new int[2][];
		// 初始化图
		dfs.createGraph(vexs, arcs);

		ALGraph g = dfs.graph;
		// 获取邻接表
		g.getAdjList(g.vertices);

		// 从V1开始遍历
		dfs.DFSTraversal(g, 0);
		// 从V2开始遍历
		dfs.DFSTraversal(g, 1);
	}

}
