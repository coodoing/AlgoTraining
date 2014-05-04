package Searching;

//http://jiangzhengjun.iteye.com/blog/561590
//����������Ľڵ���
class BSTreeNode {
	int data;//������
	BSTreeNode right;//����
	BSTreeNode left;//�Һ���

	//�������������Ľڵ�
	public BSTreeNode(int data, BSTreeNode right, BSTreeNode left) {
		this.data = data;
		this.right = right;
		this.left = left;
	}

}

public class BSTree {
	BSTreeNode rootNode;// �������ڵ�

	// �������룬�������������
	public void createBSTree(int[] A) {
		rootNode = new BSTreeNode(A[0], null, null);// ��ʼ�����ڵ�

		for (int i = 1; i < A.length; i++) {// ���ȡ������A�е�Ԫ������������������
			BSTreeNode tmpNode = rootNode;
			while (true) {
				if (tmpNode.data == A[i])
					break;

				if (tmpNode.data > A[i]) {// С�ڵ��ڸ��ڵ�
					if (tmpNode.left == null) {// �������Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
						tmpNode.left = new BSTreeNode(A[i], null, null);
						break;
					}
					tmpNode = tmpNode.left;// �����Ϊ�յĻ���������ӽڵ������͵�ǰ����Ԫ�����Ƚ�
				} else {// ���ڸ��ڵ�
					if (tmpNode.right == null) {// ����Һ���Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
						tmpNode.right = new BSTreeNode(A[i], null, null);
						break;
					}
					tmpNode = tmpNode.right;// �����Ϊ�յĻ�������Һ��ӽڵ������͵�ǰ����Ԫ�����Ƚ�
				}
			}
		}

	}

	// �������������������������֮����������ɹ���
	public void inOrderBSTree(BSTreeNode x) {
		if (x != null) {
			inOrderBSTree(x.left);// �ȱ���������
			System.out.print(x.data + ",");// ��ӡ�м�ڵ�
			inOrderBSTree(x.right);// ������������
		}
	}

	// ��ѯ����������--�ݹ��㷨
	public BSTreeNode searchBSTree1(BSTreeNode x, BSTreeNode k) {
		if (x == null || k.data == x.data) {
			return x;// ���ز�ѯ���Ľڵ�
		}
		if (k.data < x.data) {// ���kС�ڵ�ǰ�ڵ��������
			return searchBSTree1(x.left, k);// �����ӽڵ��������
		} else {// ���k���ڵ�ǰ�ڵ��������
			return searchBSTree1(x.right, k);// ���Һ��ӽڵ��������
		}
	}

	// ��ѯ����������--�ǵݹ��㷨
	public BSTreeNode searchBSTree2(BSTreeNode x, BSTreeNode k) {
		while (x != null && k.data != x.data) {
			if (x.data > k.data) {
				x = x.left;// �����ӽڵ��������
			} else {
				x = x.right;// ���Һ��ӽڵ��������
			}
		}
		return x;
	}

	// ���Ҷ������������С�ڵ�
	public BSTreeNode searchMinNode(BSTreeNode x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// ���Ҷ�������������ڵ�
	public BSTreeNode searchMaxNode(BSTreeNode x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}

	// ���β���ڵ������������
	public void insert(BSTreeNode k) {
		BSTreeNode root = rootNode;
		//System.out.println("\n*****************");
		//System.out.println("rootNode=" + rootNode.data);

		while (true) {
			if (root.data == k.data)
				return;
			else if (root.data > k.data) {
				if (root.left == null) {
					root.left = k;
					break;
				}
				root = root.left;
			} else {
				if (root.right == null) {
					root.right = k;
					break;
				}
				root = root.right;
			}
		}
	}

	// ɾ�������������ָ���Ľڵ�
	public void delete(BSTreeNode k) {// ���������ɾ��
		if (k.left == null && k.right == null) {// ��һ�����--û���ӽڵ�������
			//System.out.println("#####û���ӽڵ�����");
			BSTreeNode p = parent(k);
			//System.out.println("���ڵ�Ϊ:"+p.data+" "+p.left.data+" "+p.right.data);
			if (p.left == k) {// ��Ϊ���׽ڵ������
				p.left = null;
			} else if (p.right == k) {// ��Ϊ���׽ڵ���Һ���
				p.right = null;
			}
		} else if (k.left != null && k.right != null) {// �ڶ������--���������ӽڵ�������
			//System.out.println("\n#####����������Ϊ�յ����");
			
			//��ֱ�Ӻ��ȡ��ɾ���Ľڵ�
			BSTreeNode s = successor(k);// k�ĺ�̽ڵ�			
			delete(s);
			k.data = s.data;		//ֻ�Ǽ򵥵�data�滻
			
			//BSTreeNode suc = successor(k); //���
			//BSTreeNode pre = predecessor(k); //ǰ��
			//k.data = pre.data;			
			//BSTreeNode parent = parent(pre);
			//parent.right = pre.left;			
			//delete(pre);		
		} else {// ���������--ֻ��һ�����ӽڵ�������
			//System.out.println("#####ֻ��һ�����ӽڵ�����");
			BSTreeNode p = parent(k);
			if (p.left == k) {
				if (k.left != null) {
					p.left = k.left;
				} else {
					p.left = k.right;
				}
			} else if (p.right == k) {
				if (k.left != null) {
					p.right = k.left;
				} else {
					p.right = k.right;
				}
			}

		}
	}

	// ���ҽڵ��ǰ���ڵ�
	public BSTreeNode predecessor(BSTreeNode k) {
		if (k.left != null) {
			return searchMaxNode(k.left);// �����������ֵ
		}
		BSTreeNode y = parent(k);
		//System.out.println("K�ĸ��ڵ�(k��dataΪ54)��" + y.data);
		while (y != null && k == y.left) {// �����ҵ������һ���ڵ㣬�丸�׽ڵ�������������˵�ǰ�ڵ�����丸�׽ڵ�Ϊ��
			k = y;
			y = parent(y);
		}
		return y;
	}

	// ���ҽڵ�ĺ�̽ڵ�
	public BSTreeNode successor(BSTreeNode k) {
		if (k.right != null) {
			return searchMinNode(k.right);// ����������Сֵ
		}

		//Ϊ�˱���54,87,43�����
		BSTreeNode y = parent(k);
		//System.out.println("K�ĸ��ڵ�(k��dataΪ54)��" + y.data);		
		if (y == null)
			return null;
		while (y != null) {
			if (y.left == k) {
				return y; //Ϊ��������������Ϊ���ڵ�
			} else {
				k = y; //����ݹ�
				y = parent(y);
			}
		}
		return y;
	}

	// ������׽ڵ㣬�ڶ���ڵ���BSTreeNode��ʱ��û���������׽ڵ㣬��������ר����parent����������׽ڵ㣨��Ҫ�ǲ����޸Ĵ����ˣ����������һ��parent�����ɣ�
	public BSTreeNode parent(BSTreeNode k) {
		BSTreeNode p = rootNode;
		BSTreeNode tmp = null;
		while (p != null && p.data != k.data) {// ����pΪp.data����k.data�Ľڵ㣬tmpΪp�ĸ��׽ڵ�
			if (p.data > k.data) {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.left;
			} else {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.right;
			}
		}
		return tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/************************
		 * ����Ķ����������ṹΪ��
		 *         23
		 *        /  \
		 *       12  43
		 *     	 /	   \
		 *     	2      87
		 *     	       /
		 *            54
		 *     
		 * **********************/
		
		int[] A = { 23, 12, 12, 43, 2, 87, 54 };
		BSTreeNode searchNode1 = null;// �ݹ���ҵ��Ľ��
		BSTreeNode searchNode2 = null;// �ǵݹ���ҵ��Ľ��
		BSTreeNode searchMinNode = null;// ��С�ڵ�
		BSTreeNode searchMaxNode = null;// ���ڵ�
		BSTreeNode k = null, l = null, p = null, q = null, m = null, n = null;// ����6���ڵ�k,l,p,q,m,n

		System.out.print("��ӡ������A�е�Ԫ��");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + ",");

		BSTree bs = new BSTree();
		bs.createBSTree(A);// �������������

		System.out.println();
		System.out.print("�����������Ķ����������");
		bs.inOrderBSTree(bs.rootNode);// ����������������

		k = new BSTreeNode(23, null, null);// ��ʼ��һ�ڵ�k����dataΪnull�����Һ���Ϊnull
		l = new BSTreeNode(17, null, null);// ��ʼ��һ�ڵ�l����dataΪnull�����Һ���Ϊnull
		q = new BSTreeNode(12, null, null);// ��ʼ��һ�ڵ�q����dataΪnull�����Һ���Ϊnull
		m = bs.searchBSTree2(bs.rootNode, k);// �Ӷ���������������һ���ڵ㣬��m.dataΪk.data(���m�ڵ��ں����������Գ���)
		searchNode1 = bs.searchBSTree1(bs.rootNode, k);// ��ѯ���������----�ݹ��㷨
		searchNode2 = bs.searchBSTree2(bs.rootNode, k);// ��ѯ���������----�ݹ��㷨

		System.out.println("");
		System.out.println("�ݹ��㷨--���ҽڵ���" + searchNode1.data + "���ӣ�"
				+ searchNode1.left.data + "   �Һ��ӣ�" + searchNode1.right.data);// ��������ӻ����Һ��ӽڵ���ܴ�ӡΪ�գ������null�쳣
		//System.out.println("�ǵݹ��㷨--���ҽڵ���" + searchNode2.data + "���ӣ�"
				//+ searchNode2.left.data + "   �Һ��ӣ�" + searchNode2.right.data);// ��������ӻ����Һ��ӽڵ���ܴ�ӡΪ�գ������null�쳣

		searchMinNode = bs.searchMinNode(bs.rootNode);// �ҵ���С�ڵ�
		searchMaxNode = bs.searchMaxNode(bs.rootNode);// �ҵ����ڵ�
		System.out.println("��С�ڵ㣺" + searchMinNode.data);
		System.out.println("���ڵ㣺" + searchMaxNode.data);

		bs.insert(l);// ��l�ڵ���뵽�����������
		System.out.println("����l�ڵ�(l��dataΪ17)֮��Ķ��������������������:");
		bs.inOrderBSTree(bs.rootNode);// ����������������

		p = bs.parent(bs.searchBSTree2(bs.rootNode, q));// ȡq�ڵ�ĸ��׽ڵ�
		System.out.println("\nq�ĸ��׽ڵ�(q��dataΪ12)��" + p.data + "   ���ӣ�"
				+ p.left.data + "   �Һ��ӣ�" + p.right.data);// ��������ӻ����Һ��ӽڵ���ܴ�ӡΪ�գ������null�쳣

		//bs.delete(l); //ɾ��17
		//System.out.print("ɾ��l�ڵ�(l��dataΪ17)֮��Ķ��������������������:");
		//bs.inOrderBSTree(bs.rootNode);// ����������������
		
		bs.insert(new BSTreeNode(15, null, null));// ��l�ڵ���뵽�����������
		System.out.print("\n����ڵ�15֮��Ķ��������������������:");
		bs.inOrderBSTree(bs.rootNode);
		bs.delete(bs.searchBSTree2(bs.rootNode, q)); //ɾ��12
		System.out.print("\nɾ���ڵ�12֮��Ķ��������������������:");
		bs.inOrderBSTree(bs.rootNode);
		m = bs.searchBSTree2(bs.rootNode, k);
		System.out.println("\nm�ڵ�(m��dataΪ23)��" + m.data + "   ���ӣ�"
				+ m.left.data + "   �Һ��ӣ�" + m.right.data);

		BSTreeNode node = bs.searchBSTree1(bs.rootNode, new BSTreeNode(87, null,
				null));
		System.out.println("\ndata:" + node.data);
		n = bs.predecessor(node);
		if (n == null)
			System.out.println("K��ǰ���ڵ�Ϊ��null");
		else
			System.out.println("K��ǰ���ڵ�(k��dataΪ" + node.data + ")��" + n.data);
		n = bs.successor(node);
		if (n == null)
			System.out.println("K�ĺ�̽ڵ�Ϊ��null");
		else
			System.out.println("K��ǰ���ڵ�(k��dataΪ" + node.data + ")��" + n.data);

	}

}
