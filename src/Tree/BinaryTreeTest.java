package Tree;

// 二叉树的顺序存储，链式结构和三叉链表：http://justsee.iteye.com/blog/1097176
// 树的等价转换：信息隐藏
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
	 * 创建一个空的二叉树
	 */
	public void initBiTree() {
		root = null;// new BTNode<T>(null);
	}

	/**
	 * 利用数组输入构建二叉树
	 * 
	 * @param data
	 *            要输入的数值
	 */
	public void buildBTree(int[] data) {
		root = new BTNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			BTNode tmpNode = root;//new BTNode(data[i]);
			while (true) {
				if (tmpNode.data == data[i])
					break;
				if (tmpNode.data > data[i]) {// 小于等于根节点
					if (tmpNode.left == null) {// 如果左孩子为空，这把当前数组元素插入到左孩子节点的位置
						tmpNode.left = new BTNode(data[i]);
						break;
					}
					tmpNode = tmpNode.left;// 如果不为空的话，则把左孩子节点用来和当前数组元素作比较
				} else { // 大于根节点
					if (tmpNode.right == null) {// 如果右孩子为空，这把当前数组元素插入到左孩子节点的位置
						tmpNode.right = new BTNode(data[i]);
						break;
					}
					tmpNode = tmpNode.right;// 如果不为空的话，则把右孩子节点用来和当前数组元素作比较
				}
			}
		}
	}

	// 清空二叉树
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

	// 将二叉树清为空树
	public void clearBiTree() {
		if (root == null)
			return;
		root = null;
	}

	// 查找二叉查找树的最小节点
	public BTNode getMinBTNode(BTNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// 查找二叉查找树的最大节点
	public BTNode getMaxBTNode(BTNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	// 查找节点的前驱节点
	public BTNode getPredecessor(BTNode node) {
		if (node.left != null) {
			return getMaxBTNode(node.left);// 左子树的最大值
		}
		BTNode parent = getParent(node);
		//System.out.println("K的父节点(k的data为54)：" + y.data);
		while (parent != null && node == parent.left) {// 向上找到最近的一个节点，其父亲节点的右子树包涵了当前节点或者其父亲节点为空
			node = parent;
			parent = getParent(parent);
		}
		return parent;
	}

	// 查找节点的后继节点
	public BTNode getSuccessor(BTNode node) {
		if (node.right != null) {
			return getMinBTNode(node.right);// 右子树的最小值
		}
		//为了避免54,87,43的情况
		BTNode parent = getParent(node);
		//System.out.println("K的父节点(k的data为54)：" + y.data);		
		if (parent == null)
			return null;
		while (parent != null) {
			if (parent.left == node) {
				return parent; //为左子树情况，后继为父节点
			} else {
				node = parent; //否则递归
				parent = getParent(parent);
			}
		}
		return parent;
	}

	// 求出父亲节点，在定义节点类BSTreeNode的时候，没有申明父亲节点，所以这里专门用parent用来输出父亲节点（主要是不想修改代码了，就在这里加一个parent函数吧）
	public BTNode getParent(BTNode node) {
		BTNode p = root;
		BTNode tmp = null;
		while (p != null && p.data != node.data) {// 最后的p为p.data等于k.data的节点，tmp为p的父亲节点
			if (p.data > node.data) {
				tmp = p;// 临时存放父亲节点
				p = p.left;
			} else {
				tmp = p;// 临时存放父亲节点
				p = p.right;
			}
		}
		return tmp;
	}

	//返回root根节点
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
		//没有子树  
		if (node.left == null && node.right == null) {
			return 1;
		} else {
			int leftDeep = getRecDepth(node.left);
			int rightDeep = getRecDepth(node.right);
			//记录其所有左、右子树中较大的深度  
			int max = leftDeep > rightDeep ? leftDeep : rightDeep;
			//返回其左右子树中较大的深度 + 1  
			return max + 1;
		}
	}

	//或者直接放入到BTNode的属性当中去
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
		//node为左孩子或者无左兄弟节点
		if(parent.data>node.data||parent.left == null)
			return null;
		return parent.left;
	}

	public BTNode getRightSibling(BTNode node) {
		BTNode parent = getParent(node);
		//node为右孩子或者无右兄弟节点右边
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

	//与二叉查找树的删除有些区别
	public boolean deleteChild(BTNode node) {
		BTNode p = root;
		boolean exsits = existNode(root, node.data);
		if (exsits && p != null) {
			//deleteRecChild(node);
			BTNode parent = getParent(node);
			if (parent.data > node.data)//即node为左节点
			{
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		return false;
	}

	//递归删除node对应的子树
	public void deleteRecChild(BTNode node) {
		BTNode parent = getParent(node);
		System.out.println("parentData=" + parent.data);
		if (node.left != null || node.right != null) {
			//递归出现问题
			deleteRecChild(node.left);
			deleteRecChild(node.right);
		} else {
			if (parent.data > node.data)//即node为左节点
			{
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
	}

	//插入指定的node节点
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

	//插入data
	public void insertNode(int data) {

	}

	// 批量插入节点
	public void insertChild(int[] data) {

	}

	/**
	 * 递归遍历出二叉树
	 */
	public void traversalBiTree() {
		traversalBiTree(root);
		System.out.println();
	}

	/**
	 * 从根结点开始遍历，从树的最高层叶子结点开始输出，从左至右
	 * 
	 * @param node
	 *            当前的结点
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
		 * 构造的二叉树结构为：
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
		 * 初始化操作
		 * **********************/
		int[] data = { 2, 8, 7, 4, 9, 3, 1, 6, 7, 5 };
		biTree.buildBTree(data);
		System.out.println("二叉树的深度为:" + biTree.getDepth());
		System.out.println("初始化后，二叉树遍历结果:");
		biTree.traversalBiTree();
		BTNode rNode = biTree.root;
		System.out.println("根节点相关信息:" + rNode.right.right.data + "   "
				+ rNode.right.left.data);
		System.out.println("中序遍历的第一个节点和最后一个节点为:" + biTree.getMinBTNode(rNode).data + "   "
				+ biTree.getMaxBTNode(rNode).data);

		/************************
		 * 插入子节点操作
		 * **********************/
		BTNode node = new BTNode(10);
		biTree.insertChild(node);
		System.out.println("插入子节点后，二叉树遍历结果:");
		biTree.traversalBiTree();

		node = new BTNode(7);
		boolean result;
		result = biTree.existNode(biTree.root, node.data);
		System.out.println(result == true ? "Node存在" : "Node不存在");

		/************************
		 * 递归删除子树操作
		 * **********************/
		node = biTree.getExsitNode(7);
		System.out.println("左右孩子节点分别为："+biTree.getNodeValue(node.left)+"   "+biTree.getNodeValue(node.right));
		System.out.println("节点的左右兄弟节点分别为："+biTree.getLeftSibling(node)+"   "+biTree.getRightSibling(node));
		
		biTree.deleteChild(node);
		System.out.println("递归删除子树后，二叉树遍历结果:");
		biTree.traversalBiTree();

		/************************
		 * 清空操作
		 * **********************/
		//biTree.clearBiTree();
		//biTree.traversalBiTree();
	}
}
