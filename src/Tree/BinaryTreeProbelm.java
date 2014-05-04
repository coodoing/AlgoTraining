package Tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 *��ջ��ɶ������ı���
 */
public class BinaryTreeProbelm {

	// �������� ֵ ��ڵ� �ҽڵ�
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
	 * ���������
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
		// ���ظ��ڵ�
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
	 * ǰ�����������
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
	 * �������������1
	 * 
	 * @param args
	 */
	public static void iterarorInNodeTree(BinaryTreeProbelm node) {
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();

		while (node != null) {
			while (node != null) {
				if (node.nodeRight != null) {
					stack.push(node.nodeRight);// ���ҽڵ�ѹ��ջ
				}
				stack.push(node);// ����ǰ�ڵ�ѹ��ջ
				node = node.nodeLeft;
			}
			node = stack.pop();
			while (!stack.empty() && node.nodeRight == null) {
				showValue(node.value);
				node = stack.pop();// ȡ����ڵ�֮��ȡ�����ڵ�
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
	 * �������������2
	 * ��������˼ά����
	 * @param args
	 */
	public static void inOrderTraverse(BinaryTreeProbelm node) {

		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		while (node != null || !stack.empty()) {
			if (node != null) { //��ָ���ջ������������
				stack.push(node);
				node = node.nodeLeft;
			} else {// ��ָ����ջ�����ʸ��ڵ㣬����������
				node = stack.pop();
				showValue(node.value);
				node = node.nodeRight;
			}
		}
	}

	/**
	 * �������������1 ˼·���ٽ���ջ����Ҷ�ӽڵ�ĸ��ڵ㣬������Ҷ�ӽڵ㲻��ջ
	 * 
	 * @param node
	 */
	public static void iteratorPostNodeTree(BinaryTreeProbelm node) {
		// ������node ��¼��һ���ڵ�
		BinaryTreeProbelm tempNode = node;
		Stack<BinaryTreeProbelm> stack = new Stack<BinaryTreeProbelm>();
		while (node != null || !stack.empty()) {
			// ��ڵ���ջ
			while (node.nodeLeft != null) {
				stack.push(node);
				node = node.nodeLeft;
			}
			// ��ǰ�ڵ����Һ��� ���������Ѿ����
			while (node != null
					&& (node.nodeRight == null || node.nodeRight == tempNode)) {
				showValue(node.value);
				tempNode = node;
				if (stack.empty()) {
					return;
				}
				node = stack.pop();// ջ��root���ڵ�A��ʱ�򣬱��
			}

			// ��������
			stack.push(node);
			node = node.nodeRight;
		}
	}

	/**
	 * �������������2������ ������
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

	// ��Ԫ����
	public static void main(String[] args) {
		BinaryTreeProbelm root = init();
		// Node root = createBiTree();
		// System.out.println("ǰ�����������");
		// iteratorPreNodeTree(root);
		System.out.println("�������������1");
		iterarorInNodeTree(root);
		System.out.println("�������������2");
		inOrderTraverse(root);
		System.out.println("�������������1");
		iteratorPostNodeTree(root);
		System.out.println("�������������2");
		postOrderTraverse(root);
	}

}
