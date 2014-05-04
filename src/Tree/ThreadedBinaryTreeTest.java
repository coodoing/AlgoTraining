package Tree;

class TBTreeNode {
	int data;
	int LTag; // 0,1
	int RTag; // 0,1
	TBTreeNode lchild;
	TBTreeNode rchild;

	public TBTreeNode(int data) {
		this(data, null, null, 0, 0);
	}

	public TBTreeNode(int data, TBTreeNode lchild, TBTreeNode rchild, int LTag,
			int RTag) {
		this.data = data;
		this.lchild = lchild;
		this.rchild = rchild;
		this.LTag = LTag;
		this.RTag = RTag;
	}
}

class ThreadedBinaryTree {
	TBTreeNode head;
	TBTreeNode root;

	public void initTBTree() {
		head = new TBTreeNode(-1);
	}

	public void buildTBTree(int[] data) {
		head = null;
		root = new TBTreeNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			TBTreeNode tmpNode = root;
			while (true) {
				if (tmpNode.data == data[i])
					break;
				// С�ڵ��ڸ��ڵ�
				if (tmpNode.data > data[i]) {
					// �������Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
					if (tmpNode.lchild == null) {
						tmpNode.lchild = new TBTreeNode(data[i]);
						break;
					}
					// �����Ϊ�յĻ���������ӽڵ������͵�ǰ����Ԫ�����Ƚ�
					tmpNode = tmpNode.lchild;
				} else // ���ڸ��ڵ�
				{
					// ����Һ���Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
					if (tmpNode.rchild == null) {
						tmpNode.rchild = new TBTreeNode(data[i]);
						break;
					}
					// �����Ϊ�յĻ�������Һ��ӽڵ������͵�ǰ����Ԫ�����Ƚ�
					tmpNode = tmpNode.rchild;
				}
			}
		}
	}

	// �������������������������������
	public void inOrderThreading() {
		TBTreeNode current;
		TBTreeNode previous;

		initTBTree();// head�ڵ�ĳ�ʼ��,root�ڵ�Ϊ�û������Ķ�����

		head.LTag = 0;
		head.RTag = 1;
		// ������Ϊ�յ�ʱ��ͷ���ָ���䱾��
		if (root == null) {
			head.lchild = head.rchild = head;
		} else {
			current = root;

			head.lchild = current;
			previous = head;
			/*
			 * current = getFirstTBTNode(root); current.LTag = 1; current.lchild =
			 * head; head.lchild = current;
			 */

			previous = inThreading(current, previous);

			System.out.println("����������������previousָ���ֵΪ��" + previous.data);
			// previous = getLastTBTNode(root);
			previous.RTag = 1;
			previous.rchild = head;
			head.rchild = previous;
			
			System.out.println("�������������������һ���ڵ�Ϊ��" + previous.data
					+ "����Ӧ�ĺ�̽ڵ�Ϊ��" + previous.rchild.data);

		}
	}

	// ǰ����̶��������ͷ����Ҷ�ӽڵ����
	// ����currentָ��ָ��ǰ���ʵĽڵ㣻previous�ڵ�ָ��ոշ��ʹ��Ľڵ�
	// �����������ľ������:
	// (1)��preNode����ָ��Ϊ��,���preNode����ָ��ָ��curNode,����preNode��rightFlag����Ϊtrue;
	// (2)��curNode����ָ��Ϊ��,���curNode����ָ��ָ��preNode,����curNode��leftFlag����Ϊtrue;
	private TBTreeNode inThreading(TBTreeNode current, TBTreeNode previous) {
		if (current != null) {
			//System.out.print("��ǰcurrentָ���ǰ��previousָ��������ֵ�ֱ�Ϊ��" + current.data
			//		+ "   " + previous.data);
			//System.out.println();
			TBTreeNode tmpNode = inThreading(current.lchild, previous);
			// ǰ������
			if (current.lchild == null && current.LTag == 0) {
				current.LTag = 1;
				current.lchild = previous;
			}
			previous = tmpNode;
			// �������
			if (previous.rchild == null && previous.RTag == 0) {
				previous.RTag = 1;
				previous.rchild = current;
			}

			previous = current;// ����previousָ��current��ǰ��
			previous = inThreading(current.rchild, previous);
			//System.out.println("currentָ��=" + current.data + " previousָ��="+
			//		previous.data);
			return previous;
		}
		return previous;
	}

	// ���Ҷ������������С�ڵ�:������������ǰ�������
	public TBTreeNode getFirstTBTNode(TBTreeNode node) {
		if (head != null) {
			while (node.lchild != head) {
				node = node.lchild;
			}
		} else {
			while (node.lchild != null) {
				node = node.lchild;
			}
		}
		return node;
	}

	// ���Ҷ�������������ڵ�
	public TBTreeNode getLastTBTNode(TBTreeNode node) {
		if (head == null) {
			while (node.rchild != null) {
				node = node.rchild;
			}
		} else {
			while (node.rchild != head) {
				node = node.rchild;
			}
		}
		return node;
	}

	// ���ҽڵ��ǰ���ڵ�
	public TBTreeNode getPredecessor(TBTreeNode node) {
		if (node.lchild != null) {
			return getLastTBTNode(node.lchild);// �����������ֵ
		}
		TBTreeNode parent = getParent(node);
		while (parent != null && node == parent.lchild) {// �����ҵ������һ���ڵ㣬�丸�׽ڵ�������������˵�ǰ�ڵ�����丸�׽ڵ�Ϊ��
			node = parent;
			parent = getParent(parent);
		}
		return parent;
	}

	// ���ҽڵ�ĺ�̽ڵ�
	public TBTreeNode getSuccessor(TBTreeNode node) {
		if (node.rchild != null) {
			return getFirstTBTNode(node.rchild);// ����������Сֵ
		}
		TBTreeNode parent = getParent(node);
		if (parent == null)
			return null;
		while (parent != null) {
			if (parent.lchild == node) {
				return parent; // Ϊ��������������Ϊ���ڵ�
			} else {
				node = parent; // ����ݹ�
				parent = getParent(parent);
			}
		}
		return parent;
	}

	// ������׽ڵ㣬�ڶ���ڵ���BSTreeNode��ʱ��û���������׽ڵ㣬��������ר����parent����������׽ڵ㣨��Ҫ�ǲ����޸Ĵ����ˣ����������һ��parent�����ɣ�
	public TBTreeNode getParent(TBTreeNode node) {
		TBTreeNode p = root;
		TBTreeNode tmp = null;
		while (p != null && p.data != node.data) {// ����pΪp.data����k.data�Ľڵ㣬tmpΪp�ĸ��׽ڵ�
			if (p.data > node.data) {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.lchild;
			} else {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.rchild;
			}
		}
		return tmp;
	}

	/**
	 * �������ĵݹ����������
	 */
	public void inOrderReaversal() {
		TBTreeNode node;
		if (head != null) {
			node = head.lchild; // node��ʾheadͷָ��ָ���root�ڵ�
			// �������߱������� node==head
			// System.out.println("���������ڵ�Ϊ��" + node.data);
			while (node != head) {
				// ����������
				while (node.LTag == 0)
					node = node.lchild;
				/*
				 * if (node.data == getFirstTBTNode(root).data) {
				 * System.out.println("First Node"); break; }
				 */
				System.out.print(node.data + "   ");
				while (node.RTag == 1 && node.rchild != head) {
					// ����Ҷ�ӽڵ�ĺ��
					node = node.rchild;
					System.out.print(node.data + "   ");
				}
				/*
				 * if (node.data == root.data) { System.out.println("Root
				 * Node"); break; }
				 */
				// ������Ҷ�ӽڵ�ĺ�̺󣬷���������
				node = node.rchild;
			}
		}
	}

	/**
	 * δ������������ݹ����������
	 */
	public void traversalTBTree() {
		traversalTBTree(root);
		System.out.println();
	}

	private void traversalTBTree(TBTreeNode node) {
		if (node != null) {
			traversalTBTree(node.lchild);
			System.out.print(node.data + "  ");
			traversalTBTree(node.rchild);
		}
	}
}

public class ThreadedBinaryTreeTest {
	public static void main(String[] args) {
		ThreadedBinaryTree tbTree = new ThreadedBinaryTree();
		/***********************************************************************
		 * ��ʼ������
		 **********************************************************************/
		int[] data = { 2, 8, 7, 4, 9, 3, 1, 6, 7, 5 }; // { 8, 7, 1, 6, 4, 5,
														// 10, 3, 2, 9 };
		tbTree.buildTBTree(data);
		System.out.println("########################################");
		System.out.println("δ����������ǰ������������������:");
		tbTree.traversalTBTree();
		System.out.println(tbTree.head == null);
		System.out.println("δ����������ǰ���������е�һ���ڵ�����һ���ڵ�ֵ�ֱ�Ϊ��"
				+ tbTree.getFirstTBTNode(tbTree.root).data + "   "
				+ tbTree.getLastTBTNode(tbTree.root).data);

		/***********************************************************************
		 * ��������������
		 **********************************************************************/
		System.out.println("########################################");
		System.out.println("�������󣬶������������:");
		tbTree.inOrderThreading();
		tbTree.inOrderReaversal();
		System.out.println();
		System.out.println("��������headͷָ������ӽڵ�ͺ�̽ڵ�ֱ�Ϊ��"
				+ tbTree.head.lchild.data + "   " + tbTree.head.rchild.data);
		System.out.println("�������󣬶������е�һ���ڵ�����һ���ڵ�ֵ�ֱ�Ϊ��"
				+ tbTree.getFirstTBTNode(tbTree.root).data + "   "
				+ tbTree.getLastTBTNode(tbTree.root).data);

		rec(5);
	}

	public static void rec(int n) {
		if (n > 0) {
			rec(n - 1);
			System.out.println(n);
		}
	}
}
