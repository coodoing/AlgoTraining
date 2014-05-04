package Tree;

// ��������˳��洢����ʽ�ṹ����������http://justsee.iteye.com/blog/1097176
// ���ĵȼ�ת������Ϣ����
class BTNode {
	int data;
	BTNode left;
	BTNode right;

	public BTNode(int data) {
		this(data, null, null);
	}

	public BTNode(int data, BTNode left, BTNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}

class BinaryTree {
	BTNode root;

	/**
	 * ����һ���յĶ�����
	 */
	public void initBiTree() {
		root = null;// new BTNode<T>(null);
	}

	/**
	 * �����������빹��������
	 * 
	 * @param data
	 *            Ҫ�������ֵ
	 */
	public void buildBTree(int[] data) {
		root = new BTNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			BTNode tmpNode = root;//new BTNode(data[i]);
			while (true) {
				if (tmpNode.data == data[i])
					break;
				if (tmpNode.data > data[i]) {// С�ڵ��ڸ��ڵ�
					if (tmpNode.left == null) {// �������Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
						tmpNode.left = new BTNode(data[i]);
						break;
					}
					tmpNode = tmpNode.left;// �����Ϊ�յĻ���������ӽڵ������͵�ǰ����Ԫ�����Ƚ�
				} else { // ���ڸ��ڵ�
					if (tmpNode.right == null) {// ����Һ���Ϊ�գ���ѵ�ǰ����Ԫ�ز��뵽���ӽڵ��λ��
						tmpNode.right = new BTNode(data[i]);
						break;
					}
					tmpNode = tmpNode.right;// �����Ϊ�յĻ�������Һ��ӽڵ������͵�ǰ����Ԫ�����Ƚ�
				}
			}
		}
	}

	// ��ն�����
	public void destroyBiTree() {
		destroyBiTree(root);
	}

	private void destroyBiTree(BTNode root) {
		while (root != null) {
			destroyBiTree(root.left);
			destroyBiTree(root.right);
			root = null;
		}
	}

	// ����������Ϊ����
	public void clearBiTree() {
		if (root == null)
			return;
		root = null;
	}

	// ���Ҷ������������С�ڵ�
	public BTNode getMinBTNode(BTNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// ���Ҷ�������������ڵ�
	public BTNode getMaxBTNode(BTNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	// ���ҽڵ��ǰ���ڵ�
	public BTNode getPredecessor(BTNode node) {
		if (node.left != null) {
			return getMaxBTNode(node.left);// �����������ֵ
		}
		BTNode parent = getParent(node);
		//System.out.println("K�ĸ��ڵ�(k��dataΪ54)��" + y.data);
		while (parent != null && node == parent.left) {// �����ҵ������һ���ڵ㣬�丸�׽ڵ�������������˵�ǰ�ڵ�����丸�׽ڵ�Ϊ��
			node = parent;
			parent = getParent(parent);
		}
		return parent;
	}

	// ���ҽڵ�ĺ�̽ڵ�
	public BTNode getSuccessor(BTNode node) {
		if (node.right != null) {
			return getMinBTNode(node.right);// ����������Сֵ
		}
		//Ϊ�˱���54,87,43�����
		BTNode parent = getParent(node);
		//System.out.println("K�ĸ��ڵ�(k��dataΪ54)��" + y.data);		
		if (parent == null)
			return null;
		while (parent != null) {
			if (parent.left == node) {
				return parent; //Ϊ��������������Ϊ���ڵ�
			} else {
				node = parent; //����ݹ�
				parent = getParent(parent);
			}
		}
		return parent;
	}

	// ������׽ڵ㣬�ڶ���ڵ���BSTreeNode��ʱ��û���������׽ڵ㣬��������ר����parent����������׽ڵ㣨��Ҫ�ǲ����޸Ĵ����ˣ����������һ��parent�����ɣ�
	public BTNode getParent(BTNode node) {
		BTNode p = root;
		BTNode tmp = null;
		while (p != null && p.data != node.data) {// ����pΪp.data����k.data�Ľڵ㣬tmpΪp�ĸ��׽ڵ�
			if (p.data > node.data) {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.left;
			} else {
				tmp = p;// ��ʱ��Ÿ��׽ڵ�
				p = p.right;
			}
		}
		return tmp;
	}

	//����root���ڵ�
	public BTNode getRootNode() {
		return root;
	}

	public int getDepth() {
		int depth = 0;
		depth = getRecDepth(root);
		return depth;
	}

	private int getRecDepth(BTNode node) {
		if (node == null) {
			return 0;
		}
		//û������  
		if (node.left == null && node.right == null) {
			return 1;
		} else {
			int leftDeep = getRecDepth(node.left);
			int rightDeep = getRecDepth(node.right);
			//��¼���������������нϴ�����  
			int max = leftDeep > rightDeep ? leftDeep : rightDeep;
			//���������������нϴ����� + 1  
			return max + 1;
		}
	}

	//����ֱ�ӷ��뵽BTNode�����Ե���ȥ
	public int getNodeValue(BTNode node) {
		return node!=null?node.data:-1;
	}

	public BTNode getLeftChild(BTNode node) {
		return node!=null?node.left:null;
	}

	public BTNode getRightChild(BTNode node) {
		return node!=null?node.right:null;
	}

	public BTNode getLeftSibling(BTNode node) {
		BTNode parent = getParent(node);
		//nodeΪ���ӻ��������ֵܽڵ�
		if(parent.data>node.data||parent.left == null)
			return null;
		return parent.left;
	}

	public BTNode getRightSibling(BTNode node) {
		BTNode parent = getParent(node);
		//nodeΪ�Һ��ӻ��������ֵܽڵ��ұ�
		if(parent.data<node.data||parent.right == null)
			return null;
		return parent.right;
	}

	public boolean existNode(BTNode p, int data) {
		if (!isEmpty()) {
			while (p != null) {
				if (p.data == data)
					return true;
				if (p.data > data)
					return existNode(p.left, data);
				else
					return existNode(p.right, data);
			}
		}
		return false;
	}

	public BTNode getExsitNode(int data) {
		if (existNode(root, data)) {
			return getExsitNode(root, data);
		}
		return null;
	}

	private BTNode getExsitNode(BTNode p, int data) {
		if (p.data == data)
			return p;
		if (p.data > data)
			return getExsitNode(p.left, data);
		else
			return getExsitNode(p.right, data);
	}

	public boolean isEmpty() {
		return (root == null);
	}

	//������������ɾ����Щ����
	public boolean deleteChild(BTNode node) {
		BTNode p = root;
		boolean exsits = existNode(root, node.data);
		if (exsits && p != null) {
			//deleteRecChild(node);
			BTNode parent = getParent(node);
			if (parent.data > node.data)//��nodeΪ��ڵ�
			{
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		return false;
	}

	//�ݹ�ɾ��node��Ӧ������
	public void deleteRecChild(BTNode node) {
		BTNode parent = getParent(node);
		System.out.println("parentData=" + parent.data);
		if (node.left != null || node.right != null) {
			//�ݹ��������
			deleteRecChild(node.left);
			deleteRecChild(node.right);
		} else {
			if (parent.data > node.data)//��nodeΪ��ڵ�
			{
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
	}

	//����ָ����node�ڵ�
	public void insertChild(BTNode node) {
		BTNode p = root;
		while (true) {
			if (p.data == node.data)
				return;
			else if (p.data > node.data) {
				if (p.left == null) {
					p.left = node;
					break;
				}
				p = p.left;
			} else {
				if (p.right == null) {
					p.right = node;
					break;
				}
				p = p.right;
			}
		}
	}

	//����data
	public void insertNode(int data) {

	}

	// ��������ڵ�
	public void insertChild(int[] data) {

	}

	/**
	 * �ݹ������������
	 */
	public void traversalBiTree() {
		traversalBiTree(root);
		System.out.println();
	}

	/**
	 * �Ӹ���㿪ʼ��������������߲�Ҷ�ӽ�㿪ʼ�������������
	 * 
	 * @param node
	 *            ��ǰ�Ľ��
	 */
	private void traversalBiTree(BTNode node) {
		if (node != null) {
			traversalBiTree(node.left);
			System.out.print(node.data + "  ");
			traversalBiTree(node.right);
		}
	}
}

public class BinaryTreeTest {
	public static void main(String[] args) {
		Integer a = new Integer(6);
		Integer b = new Integer(7);
		Integer c = new Integer(8);
		System.out.println(c.compareTo(b) + "   " + a);

		BinaryTree biTree = new BinaryTree();

		/************************
		 * ����Ķ������ṹΪ��
		 *         2
		 *        / \
		 *       1   8
		 *     		/ \
		 *     	   7   9
		 *     	  /
		 *       4
		 *      / \
		 *     3   6
		 *        /
		 *       5
		 * **********************/

		/************************
		 * ��ʼ������
		 * **********************/
		int[] data = { 2, 8, 7, 4, 9, 3, 1, 6, 7, 5 };
		biTree.buildBTree(data);
		System.out.println("�����������Ϊ:" + biTree.getDepth());
		System.out.println("��ʼ���󣬶������������:");
		biTree.traversalBiTree();
		BTNode rNode = biTree.root;
		System.out.println("���ڵ������Ϣ:" + rNode.right.right.data + "   "
				+ rNode.right.left.data);
		System.out.println("��������ĵ�һ���ڵ�����һ���ڵ�Ϊ:" + biTree.getMinBTNode(rNode).data + "   "
				+ biTree.getMaxBTNode(rNode).data);

		/************************
		 * �����ӽڵ����
		 * **********************/
		BTNode node = new BTNode(10);
		biTree.insertChild(node);
		System.out.println("�����ӽڵ�󣬶������������:");
		biTree.traversalBiTree();

		node = new BTNode(7);
		boolean result;
		result = biTree.existNode(biTree.root, node.data);
		System.out.println(result == true ? "Node����" : "Node������");

		/************************
		 * �ݹ�ɾ����������
		 * **********************/
		node = biTree.getExsitNode(7);
		System.out.println("���Һ��ӽڵ�ֱ�Ϊ��"+biTree.getNodeValue(node.left)+"   "+biTree.getNodeValue(node.right));
		System.out.println("�ڵ�������ֵܽڵ�ֱ�Ϊ��"+biTree.getLeftSibling(node)+"   "+biTree.getRightSibling(node));
		
		biTree.deleteChild(node);
		System.out.println("�ݹ�ɾ�������󣬶������������:");
		biTree.traversalBiTree();

		/************************
		 * ��ղ���
		 * **********************/
		//biTree.clearBiTree();
		//biTree.traversalBiTree();
	}
}
