package Graph;

import java.util.ArrayList;
import java.util.List;

// ��Algorithm��http://algs4.cs.princeton.edu/41undirected
// ��C��http://blog.csdn.net/akof1314/article/details/4388722
//      http://blog.csdn.net/whz_zb/article/details/7326082
// jGraphT
class ArcNode {
	int vex; //��ָ��Ķ����λ��
	ArcNode next; //ָ����һ������ָ��
	String info; //�û������Ϣ
	double weight; // ����Ȩ��

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
	int vex; // ����ڵ���Ϣ
	ArcNode firstNode; // ָ���һ�������ö���Ļ���ָ��

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
	int vexNum; //��������
	int arcNum; //������
	int type; //���0����ͼ��1����ͼ

	int[] visited;
	public ALGraph() {
		vertices = new ArrayList<VNode>();
		vexNum = 0;
		arcNum = 0;
		type = 0;//����ͼ
	}

	public void createGraph(int[] vexs, int[][] arcs) {
		//��ʵӦ�ô������������������ͣ�����Ϊ�˲�����㣬�ͼ򵥵�ֱ�Ӹ�ֵ
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

	// ֱ�ӽ���
	private void addArc(int start, int end, double weight) {
		/* ��Ҫ��һ�����ԣ�indexof����-1
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
				//System.out.println("ͷ����Ϊ��"+temp.vex);
				if (temp.next == null && temp.vex != node.vex) {
					temp.next = node;
					//System.out.println("nextָ��ָ��:"+temp.next.vex);
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
		System.out.print("ͷ���ΪV" + v.vex + ",��Ӧ���ڽӱ�Ϊ: ");
		while (p != null) {
			System.out.print(p.vex + "--->");
			p = p.next;
		}
		System.out.print(p);
	}
	
	// �ӵ�v�����㿪ʼ����
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
		if(node!=null)//�������Լ�vͷ����Ӧ������Ϊ��
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
		// ��ʼ��ͼ
		graph.createGraph(vexs, arcs);
		graph.visited = new int[graph.vexNum];
		// ��ȡ�������Ŀ�ͱߵ���Ŀ
		int arcNum = graph.arcNum;
		if (graph.type == 0)
			arcNum = graph.arcNum / 2;
		System.out.println("�������ĿΪ: " + graph.vexNum + ";�ߵ���ĿΪ: " + arcNum);
		// ��ȡ�ڽӱ�
		graph.getAdjList(graph.vertices);
		
		graph.DFSTraversal(graph, 0);

		graph.DFSTraversal(graph, 1);
		
	}
}
