package Searching;

//http://jiangzhengjun.iteye.com/blog/561590
//二叉查找树的节点类
class BSTreeNode {
	int data;//数据域
	BSTreeNode right;//左孩子
	BSTreeNode left;//右孩子

	//构造二叉查找树的节点
	public BSTreeNode(int data, BSTreeNode right, BSTreeNode left) {
		this.data = data;
		this.right = right;
		this.left = left;
	}

}

public class BSTree {
	BSTreeNode rootNode;// 创建根节点

	// 批量插入，创建二叉查找树
	public void createBSTree(int[] A) {
		rootNode = new BSTreeNode(A[0], null, null);// 初始化根节点

		for (int i = 1; i < A.length; i++) {// 逐个取出数组A中的元素用来构造二叉查找树
			BSTreeNode tmpNode = rootNode;
			while (true) {
				if (tmpNode.data == A[i])
					break;

				if (tmpNode.data > A[i]) {// 小于等于根节点
					if (tmpNode.left == null) {// 如果左孩子为空，这把当前数组元素插入到左孩子节点的位置
						tmpNode.left = new BSTreeNode(A[i], null, null);
						break;
					}
					tmpNode = tmpNode.left;// 如果不为空的话，则把左孩子节点用来和当前数组元素作比较
				} else {// 大于根节点
					if (tmpNode.right == null) {// 如果右孩子为空，这把当前数组元素插入到左孩子节点的位置
						tmpNode.right = new BSTreeNode(A[i], null, null);
						break;
					}
					tmpNode = tmpNode.right;// 如果不为空的话，则把右孩子节点用来和当前数组元素作比较
				}
			}
		}

	}

	// 中序遍历二叉查找树（中序遍历之后便可以排序成功）
	public void inOrderBSTree(BSTreeNode x) {
		if (x != null) {
			inOrderBSTree(x.left);// 先遍历左子树
			System.out.print(x.data + ",");// 打印中间节点
			inOrderBSTree(x.right);// 最后遍历右子树
		}
	}

	// 查询二叉排序树--递归算法
	public BSTreeNode searchBSTree1(BSTreeNode x, BSTreeNode k) {
		if (x == null || k.data == x.data) {
			return x;// 返回查询到的节点
		}
		if (k.data < x.data) {// 如果k小于当前节点的数据域
			return searchBSTree1(x.left, k);// 从左孩子节点继续遍历
		} else {// 如果k大于当前节点的数据域
			return searchBSTree1(x.right, k);// 从右孩子节点继续遍历
		}
	}

	// 查询二叉排序树--非递归算法
	public BSTreeNode searchBSTree2(BSTreeNode x, BSTreeNode k) {
		while (x != null && k.data != x.data) {
			if (x.data > k.data) {
				x = x.left;// 从左孩子节点继续遍历
			} else {
				x = x.right;// 从右孩子节点继续遍历
			}
		}
		return x;
	}

	// 查找二叉查找树的最小节点
	public BSTreeNode searchMinNode(BSTreeNode x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// 查找二叉查找树的最大节点
	public BSTreeNode searchMaxNode(BSTreeNode x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}

	// 依次插入节点进入二叉查找树
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

	// 删除二叉查找树中指定的节点
	public void delete(BSTreeNode k) {// 分三种情况删除
		if (k.left == null && k.right == null) {// 第一种情况--没有子节点的情况下
			//System.out.println("#####没有子节点的情况");
			BSTreeNode p = parent(k);
			//System.out.println("父节点为:"+p.data+" "+p.left.data+" "+p.right.data);
			if (p.left == k) {// 其为父亲节点的左孩子
				p.left = null;
			} else if (p.right == k) {// 其为父亲节点的右孩子
				p.right = null;
			}
		} else if (k.left != null && k.right != null) {// 第二种情况--有两个孩子节点的情况下
			//System.out.println("\n#####左右子树不为空的情况");
			
			//由直接后继取代删除的节点
			BSTreeNode s = successor(k);// k的后继节点			
			delete(s);
			k.data = s.data;		//只是简单的data替换
			
			//BSTreeNode suc = successor(k); //后继
			//BSTreeNode pre = predecessor(k); //前驱
			//k.data = pre.data;			
			//BSTreeNode parent = parent(pre);
			//parent.right = pre.left;			
			//delete(pre);		
		} else {// 第三种情况--只有一个孩子节点的情况下
			//System.out.println("#####只有一个孩子节点的情况");
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

	// 查找节点的前驱节点
	public BSTreeNode predecessor(BSTreeNode k) {
		if (k.left != null) {
			return searchMaxNode(k.left);// 左子树的最大值
		}
		BSTreeNode y = parent(k);
		//System.out.println("K的父节点(k的data为54)：" + y.data);
		while (y != null && k == y.left) {// 向上找到最近的一个节点，其父亲节点的右子树包涵了当前节点或者其父亲节点为空
			k = y;
			y = parent(y);
		}
		return y;
	}

	// 查找节点的后继节点
	public BSTreeNode successor(BSTreeNode k) {
		if (k.right != null) {
			return searchMinNode(k.right);// 右子树的最小值
		}

		//为了避免54,87,43的情况
		BSTreeNode y = parent(k);
		//System.out.println("K的父节点(k的data为54)：" + y.data);		
		if (y == null)
			return null;
		while (y != null) {
			if (y.left == k) {
				return y; //为左子树情况，后继为父节点
			} else {
				k = y; //否则递归
				y = parent(y);
			}
		}
		return y;
	}

	// 求出父亲节点，在定义节点类BSTreeNode的时候，没有申明父亲节点，所以这里专门用parent用来输出父亲节点（主要是不想修改代码了，就在这里加一个parent函数吧）
	public BSTreeNode parent(BSTreeNode k) {
		BSTreeNode p = rootNode;
		BSTreeNode tmp = null;
		while (p != null && p.data != k.data) {// 最后的p为p.data等于k.data的节点，tmp为p的父亲节点
			if (p.data > k.data) {
				tmp = p;// 临时存放父亲节点
				p = p.left;
			} else {
				tmp = p;// 临时存放父亲节点
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
		 * 构造的二叉排序树结构为：
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
		BSTreeNode searchNode1 = null;// 递归查找到的结果
		BSTreeNode searchNode2 = null;// 非递归查找到的结果
		BSTreeNode searchMinNode = null;// 最小节点
		BSTreeNode searchMaxNode = null;// 最大节点
		BSTreeNode k = null, l = null, p = null, q = null, m = null, n = null;// 申明6个节点k,l,p,q,m,n

		System.out.print("打印出数组A中的元素");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + ",");

		BSTree bs = new BSTree();
		bs.createBSTree(A);// 创建二叉查找树

		System.out.println();
		System.out.print("中序遍历构造的二叉查找树：");
		bs.inOrderBSTree(bs.rootNode);// 中序遍历二叉查找树

		k = new BSTreeNode(23, null, null);// 初始化一节点k，其data为null，左右孩子为null
		l = new BSTreeNode(17, null, null);// 初始化一节点l，其data为null，左右孩子为null
		q = new BSTreeNode(12, null, null);// 初始化一节点q，其data为null，左右孩子为null
		m = bs.searchBSTree2(bs.rootNode, k);// 从二叉查找树里面查找一个节点，其m.data为k.data(这个m节点在后面用来测试程序)
		searchNode1 = bs.searchBSTree1(bs.rootNode, k);// 查询二叉查找树----递归算法
		searchNode2 = bs.searchBSTree2(bs.rootNode, k);// 查询二叉查找树----递归算法

		System.out.println("");
		System.out.println("递归算法--查找节点域：" + searchNode1.data + "左孩子："
				+ searchNode1.left.data + "   右孩子：" + searchNode1.right.data);// 这里的左孩子或者右孩子节点可能打印为空，会出现null异常
		//System.out.println("非递归算法--查找节点域：" + searchNode2.data + "左孩子："
				//+ searchNode2.left.data + "   右孩子：" + searchNode2.right.data);// 这里的左孩子或者右孩子节点可能打印为空，会出现null异常

		searchMinNode = bs.searchMinNode(bs.rootNode);// 找到最小节点
		searchMaxNode = bs.searchMaxNode(bs.rootNode);// 找到最大节点
		System.out.println("最小节点：" + searchMinNode.data);
		System.out.println("最大节点：" + searchMaxNode.data);

		bs.insert(l);// 把l节点插入到二叉查找树中
		System.out.println("插入l节点(l的data为17)之后的二叉查找树的中序遍历结果:");
		bs.inOrderBSTree(bs.rootNode);// 中序遍历二叉查找树

		p = bs.parent(bs.searchBSTree2(bs.rootNode, q));// 取q节点的父亲节点
		System.out.println("\nq的父亲节点(q的data为12)：" + p.data + "   左孩子："
				+ p.left.data + "   右孩子：" + p.right.data);// 这里的左孩子或者右孩子节点可能打印为空，会出现null异常

		//bs.delete(l); //删除17
		//System.out.print("删除l节点(l的data为17)之后的二叉查找树的中序遍历结果:");
		//bs.inOrderBSTree(bs.rootNode);// 中序遍历二叉查找树
		
		bs.insert(new BSTreeNode(15, null, null));// 把l节点插入到二叉查找树中
		System.out.print("\n插入节点15之后的二叉查找树的中序遍历结果:");
		bs.inOrderBSTree(bs.rootNode);
		bs.delete(bs.searchBSTree2(bs.rootNode, q)); //删除12
		System.out.print("\n删除节点12之后的二叉查找树的中序遍历结果:");
		bs.inOrderBSTree(bs.rootNode);
		m = bs.searchBSTree2(bs.rootNode, k);
		System.out.println("\nm节点(m的data为23)：" + m.data + "   左孩子："
				+ m.left.data + "   右孩子：" + m.right.data);

		BSTreeNode node = bs.searchBSTree1(bs.rootNode, new BSTreeNode(87, null,
				null));
		System.out.println("\ndata:" + node.data);
		n = bs.predecessor(node);
		if (n == null)
			System.out.println("K的前驱节点为：null");
		else
			System.out.println("K的前驱节点(k的data为" + node.data + ")：" + n.data);
		n = bs.successor(node);
		if (n == null)
			System.out.println("K的后继节点为：null");
		else
			System.out.println("K的前驱节点(k的data为" + node.data + ")：" + n.data);

	}

}
