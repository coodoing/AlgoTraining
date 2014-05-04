package Tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 *用栈完成二叉树的遍历
 */
public class BinaryTreeProbelm {

	// 三个属性 值 左节点 右节点
	private String value;
	private BinaryTreeProbelm nodeLeft;
	private BinaryTreeProbelm nodeRight;

	private BinaryTreeProbelm() {
	}

	public BinaryTreeProbelm(String value) {
		this.value = value;
	}

	private static void showValue(String value) {
		System.out.println(value);
	}

	/**
	 * 构造二叉树
	 * 
	 * @return
	 */
	private static BinaryTreeProbelm init() {
		BinaryTreeProbelm node1 = new BinaryTreeProbelm("A");
		BinaryTreeProbelm node2 = new BinaryTreeProbelm("B");
		BinaryTreeProbelm node3 = new BinaryTreeProbelm("C");
		BinaryTreeProbelm node4 = new BinaryTreeProbelm("D");
		BinaryTreeProbelm node5 = new BinaryTreeProbelm("E");
		BinaryTreeProbelm node6 = new BinaryTreeProbelm("F");
		BinaryTreeProbelm node7 = new BinaryTreeProbelm("G");
		node1.nodeLeft = node2;
		node1.nodeRight = node3;
		node2.nodeLeft = node4;
		node2.nodeRight = node5;
		node3.nodeLeft = node6;
		node3.nodeRight = node7;
		// 返回根节点
		return node1;
	}

	@SuppressWarnings("unused")
	private static BinaryTreeProbelm createBiTree() {
		String ch = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			ch = br.readLine();
		} catch (Exception ex) {
		}
		BinaryTreeProbelm node = new BinaryTreeProbelm();
		if (ch == "&")
			node = null;
		else {
			node = new BinaryTreeProbelm(ch);
			node.nodeLeft = createBiTree();
			node.nodeRight = createBiTree();
		}
		return node;
	}

	/**
	 * 前序遍历二叉树
	 * 
	 * @param node
	 */
	public static void iteratorPreNodeTree(BinaryTreeProbelm node) {
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		if (node != null) {
			stack.push(node);
			while (!stack.empty()) {
				node = stack.pop();
				showValue(node.value);
				if (node.nodeRight != null) {
					stack.push(node.nodeRight);
				}
				if (node.nodeLeft != null) {
					stack.push(node.nodeLeft);
				}
			}
		}
	}

	/**
	 * 中序遍历二叉树1
	 * 
	 * @param args
	 */
	public static void iterarorInNodeTree(BinaryTreeProbelm node) {
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();

		while (node != null) {
			while (node != null) {
				if (node.nodeRight != null) {
					stack.push(node.nodeRight);// 将右节点压入栈
				}
				stack.push(node);// 将当前节点压入栈
				node = node.nodeLeft;
			}
			node = stack.pop();
			while (!stack.empty() && node.nodeRight == null) {
				showValue(node.value);
				node = stack.pop();// 取出左节点之后取出根节点
			}
			showValue(node.value);
			if (!stack.empty()) {
				node = stack.pop();
			} else {
				node = null;
			}
		}
	}

	/**
	 * 中序遍历二叉树2
	 * 容易陷入思维误区
	 * @param args
	 */
	public static void inOrderTraverse(BinaryTreeProbelm node) {

		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		while (node != null || !stack.empty()) {
			if (node != null) { //根指针进栈，遍历左子树
				stack.push(node);
				node = node.nodeLeft;
			} else {// 根指针退栈，访问根节点，遍历右子树
				node = stack.pop();
				showValue(node.value);
				node = node.nodeRight;
			}
		}
	}

	/**
	 * 后序遍历二叉树1 思路：操进出栈左、右叶子节点的父节点，而左、右叶子节点不进栈
	 * 
	 * @param node
	 */
	public static void iteratorPostNodeTree(BinaryTreeProbelm node) {
		// 定义标记node 记录上一个节点
		BinaryTreeProbelm tempNode = node;
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		while (node != null || !stack.empty()) {
			// 左节点入栈
			while (node.nodeLeft != null) {
				stack.push(node);
				node = node.nodeLeft;
			}
			// 当前节点无右孩子 或者右子已经输出
			while (node != null
					&& (node.nodeRight == null || node.nodeRight == tempNode)) {
				showValue(node.value);
				tempNode = node;
				if (stack.empty()) {
					return;
				}
				node = stack.pop();// 栈在root根节点A的时候，变空
			}

			// 处理右子
			stack.push(node);
			node = node.nodeRight;
		}
	}

	/**
	 * 后序遍历二叉树2：：： 有问题
	 * 
	 * @param node
	 */
	public static void postOrderTraverse(BinaryTreeProbelm node) {
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		int flag = 0;
		stack.push(node);
		BinaryTreeProbelm newNode, oldNode;
		while (!stack.empty()) {
			if (node.nodeLeft != null && flag != 1) {
				stack.push(node.nodeLeft);
				node = node.nodeLeft;
			} else {
				if (node.nodeRight != null) {
					stack.push(node.nodeRight);
					flag = 0;
				} else {
					oldNode = stack.peek();
					stack.pop();
					showValue(oldNode.value);

					while (!stack.empty()) {
						newNode = stack.peek();

						if (oldNode == newNode.nodeLeft) {
							flag = 1;
							break;
						} else {
							stack.pop();
							showValue(newNode.value);
							oldNode = newNode;
							flag = 0;
						}
					}
				}

			}
		}
	}

	// 单元测试
	public static void main(String[] args) {
		BinaryTreeProbelm root = init();
		// Node root = createBiTree();
		// System.out.println("前序遍历二叉树");
		// iteratorPreNodeTree(root);
		System.out.println("中序遍历二叉树1");
		iterarorInNodeTree(root);
		System.out.println("中序遍历二叉树2");
		inOrderTraverse(root);
		System.out.println("后序遍历二叉树1");
		iteratorPostNodeTree(root);
		System.out.println("后序遍历二叉树2");
		postOrderTraverse(root);
	}

}
