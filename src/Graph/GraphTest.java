package Graph;

import java.util.ArrayList;
import java.util.List;

// （Algorithm）http://algs4.cs.princeton.edu/41undirected
// （C）http://blog.csdn.net/akof1314/article/details/4388722
//      http://blog.csdn.net/whz_zb/article/details/7326082
// jGraphT
class ArcNode {
	int vex; //弧指向的顶点的位置
	ArcNode next; //指向下一条弧的指针
	String info; //该弧相关信息
	double weight; // 弧的权重

	public ArcNode(int vex, double weight) {
		this(vex, null, "", weight);
	}

	public ArcNode(int vex, ArcNode next, String info, double weight) {
		this.vex = vex;
		this.next = next;
		this.info = info;
		this.weight = weight;
	}
}

class VNode {
	int vex; // 顶点节点信息
	ArcNode firstNode; // 指向第一条依附该顶点的弧的指针

	public VNode(int vex) {
		this(vex, null);
	}

	public VNode(int vex, ArcNode firstNode) {
		this.vex = vex;
		this.firstNode = firstNode;
	}

	public boolean equals(VNode node) {
		if (vex == node.vex) {
			return true;
		}
		return false;
	}
}

class ALGraph {
	List<VNode> vertices;
	int vexNum; //顶点数量
	int arcNum; //弧数量
	int type; //类别：0无向图，1有向图

	int[] visited;
	public ALGraph() {
		vertices = new ArrayList<VNode>();
		vexNum = 0;
		arcNum = 0;
		type = 0;//无向图
	}

	public void createGraph(int[] vexs, int[][] arcs) {
		//其实应该传进来参数是数组类型，这里为了操作简便，就简单的直接赋值
		/*addVertex(1);
		addVertex(2);
		addVertex(3);
		addVertex(4);
		addVertex(5);

		addArc(1, 2);
		addArc(1, 4);
		addArc(2, 3);
		addArc(2, 5);
		addArc(3, 4);
		addArc(3, 5);*/
		
		addVertex(1);
		addVertex(2);
		addVertex(3);
		addVertex(4);
		addVertex(5);
		addVertex(6);
		addVertex(7);
		addVertex(8);

		addArc(1, 2);
		addArc(1, 3);
		addArc(2, 4);
		addArc(2, 5);
		addArc(3, 6);
		addArc(3, 7);
		addArc(4, 8);
		addArc(5, 8);
		addArc(6, 7);
		
	}

	public void destroyGraph() {

	}

	public void addVertex(int vex) {
		VNode node = new VNode(vex);
		if (!vertices.contains(node)) {
			vertices.add(node);
			vexNum++;
		}
	}

	public void addArc(int start, int end) {
		addArc(start, end, 0);
	}

	// 直接建立
	private void addArc(int start, int end, double weight) {
		/* 需要进一步调试：indexof返回-1
		 * int startIndex = vertices.indexOf(new VNode(start));
		int endIndex = vertices.indexOf(new VNode(end));
		VNode sNode = vertices.get(startIndex);
		VNode eNode = vertices.get(endIndex);*/
		VNode sNode = vertices.get(start - 1);
		VNode eNode = vertices.get(end - 1);

		if (type == 0)
			addArcNext(eNode, sNode, weight);
		addArcNext(sNode, eNode, weight);
	}

	private void addArcNext(VNode sNode, VNode eNode, double weight) {
		ArcNode p = sNode.firstNode;
		ArcNode node = new ArcNode(eNode.vex, weight);
		if (p == null) {
			sNode.firstNode = node;
		} else {
			ArcNode temp = sNode.firstNode;
			while (true) {
				//System.out.println("头结点后为："+temp.vex);
				if (temp.next == null && temp.vex != node.vex) {
					temp.next = node;
					//System.out.println("next指针指向:"+temp.next.vex);
					break;
				} else if (temp.next != null) {
					temp = temp.next;
				}
			}
		}
		arcNum++;
	}

	public void getAdjList(List<VNode> list) {
		VNode v;
		for (int i = 0; i < list.size(); i++) {
			v = list.get(i);
			getAdjNode(v);
			System.out.println();
		}
	}

	private void getAdjNode(VNode v) {
		ArcNode p = v.firstNode;
		System.out.print("头结点为V" + v.vex + ",对应的邻接表为: ");
		while (p != null) {
			System.out.print(p.vex + "--->");
			p = p.next;
		}
		System.out.print(p);
	}
	
	// 从第v个顶点开始遍历
	public void DFSTraversal(ALGraph graph, int v) {
		//System.out.println(visited.length+" ");
		for (int i = 0; i < graph.vexNum; i++)
			visited[i] = 0;
		/*for (int i = 0; i < graph.vexNum; i++)
			System.out.print(visited[i]+" ");*/
		System.out.println();
		/*for(int j=0;j<graph.vexNum;j++)
		{
			if(visited[j]==0)
			{
				visited[j] = 1;
				DFS(graph,j);
			}
		}*/
		visited[v] = 1;
		System.out.print(v+1+" ");
		DFS(graph,v);
		
	}
	
	private void DFS(ALGraph graph, int v)
	{		
		VNode node = graph.vertices.get(v);
		ArcNode p = node.firstNode;
		if(node!=null)//遍历完以及v头结点对应的链表不为空
		{	
			while(p!=null)
			{
				if(visited[p.vex-1]==0)
				{
					System.out.print(p.vex+" ");
					visited[p.vex-1]=1;
					DFS(graph,p.vex-1);
				}
				p = p.next;
			}
		}
		else
		{
			return;
		}
		
	}
}

public class GraphTest {
	public static void main(String[] args) {
		ALGraph graph = new ALGraph();
		int[] vexs = new int[5];
		int[][] arcs = new int[2][];
		// 初始化图
		graph.createGraph(vexs, arcs);
		graph.visited = new int[graph.vexNum];
		// 获取顶点的数目和边的数目
		int arcNum = graph.arcNum;
		if (graph.type == 0)
			arcNum = graph.arcNum / 2;
		System.out.println("顶点的数目为: " + graph.vexNum + ";边的数目为: " + arcNum);
		// 获取邻接表
		graph.getAdjList(graph.vertices);
		
		graph.DFSTraversal(graph, 0);

		graph.DFSTraversal(graph, 1);
		
	}
}
