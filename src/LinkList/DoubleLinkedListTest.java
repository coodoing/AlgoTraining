package LinkList;

// 双向链表
// http://en.wikipedia.org/wiki/Doubly_linked_list#Data_type_declarations
// http://www.chinasb.org/archives/2011/09/3872.shtml
// http://www.cnblogs.com/matrix1024/archive/2011/12/31/2308380.html
class Node<T> {
	T value;
	Node<T> prev = null;
	Node<T> next = null;

	public boolean equals(Node<T> node) {
		if (value.equals(node.value)) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return value.hashCode();
	}

	public String toString() {
		if (value == null)
			return "-1";
		return value.toString();
	}
}

class DoubleLinkedList<T> {
	private Node<T> head; // 头节点
	private Node<T> tail;
	private int len; // 链表大小  

	//初始化LinkedList
	public DoubleLinkedList() {
		/*head = new Node<T>();
		tail = new Node<T>();

		head.next = tail;
		head.prev = null;
		tail.next = null;
		tail.prev = head;
		len = 0;*/
	}

	public void init() {
		head = new Node<T>();
		tail = new Node<T>();

		head.next = tail;
		head.prev = null;
		tail.next = null;
		tail.prev = head;
		len = 0;
	}

	public void destroyList() {

	}

	public void clearList() {
		init();
	}

	// 判断链表是否为空
	public boolean isEmpty() {
		if (tail == head.next || head == tail.prev) {
			return true;
		}
		return false;
	}

	// 链表的遍历(从head顺序遍历)
	public void traversalFromHead() {
		if (isEmpty()) {
			System.out.println("The Doubly Linked List is empty");
		} else {
			/*Node<T> node = head.next;
			while (null != node.next) {
				System.out.print(node + "-->");               
				node = node.next;
			}*/
			Node<T> node = head;
			len = 0;
			while (tail != node.next) {
				System.out.print(node.next + "-->");
				len++;
				node = node.next;
			}
			System.out.println();
		}
	}

	// 链表的遍历(从tail倒序遍历)
	public void traversalFromTail() {
		if (isEmpty()) {
			System.out.println("The Doubly Linked List is empty");
		} else {
			Node<T> node = tail.prev;
			while (null != node.prev) {
				System.out.print(node + "-->");
				node = node.prev;
			}
			System.out.println();
		}
	}

	public int getLength() {
		return len;
	}

	// 添加节点到链表的头部
	public void addToHead(Node<T> node) {
		/*if (tail == head.next) {
			head.next = node;
			tail.prev = node;
		} else {
			node.next = head.next;
			node.prev = head;
			head.next.prev = node;
			head.next = node;
		}*/
		
		//这里需要做判断，判断首尾指针是否为空。即判断是否是第一次插入。
		
		// 修改node节点的前驱和后继
		node.next = head.next;
		node.prev = head; 
		// 修改node前驱节点的后继及后继节点的前驱
		head.next.prev = node;
		head.next = node;
	}

	// 添加节点到链表的尾部
	public void addToTail(Node<T> node) {
		/*if (tail.prev == head) {
			tail.prev = node;
			head.next = node;
		} else {
			node.prev = tail.prev;
			node.next = tail;
			tail.prev.next = node;
			tail.prev = node;
		}*/
		// 修改node节点的前驱和后继
		node.prev = tail.prev;
		node.next = tail;
		// 修改node前驱节点的后继及后继节点的前驱
		tail.prev.next = node;
		tail.prev = node;
	}

	// 添加某个值到指定的数值的节点前面
	public void insertBefore(Node<T> node, T key) {
		if (null == head.next || null == tail.prev) {
			System.out.println("The Doubly Linked List is empty");
		} else {
			Node<T> theNode = head.next;
			while (null != theNode.next) {
				if (theNode.next.value.equals(key)) {
					node.next = theNode.next;
					theNode.next.prev = node;
					theNode.next = node;
					node.prev = theNode;
					break;
				}
				theNode = theNode.next;
			}
		}
	}

	// 添加某个值到指定的数值的节点后面
	public void insertAfter(Node<T> node, T key) {
		if (null == head.next || null == tail.prev) {
			System.out.println("The Doubly Linked List is empty");
		} else {
			Node<T> theNode = head;
			while (tail != theNode.next) {
				if (theNode.next.value.equals(key)) {
					node.next = theNode.next.next;
					theNode.next.next.prev = node;
					theNode.next.next = node;
					node.prev = theNode.next;
					break;
				}
				theNode = theNode.next;
			}
		}
	}

	//删除第一个元素
	public void deleteFirst() {
		if (isEmpty()) {
			return;
		} else {
			head.next = head.next.next;//修改后继
			head.next.next.prev = head;//修改前驱
		}
	}

	//删除最后一个元素
	public void deleteLast() {
		if (!isEmpty()) {
			//顺序不能错
			tail.prev.prev.next = tail;//修改后继
			tail.prev = tail.prev.prev;//修改前驱			
		}
	}

	//判断指定的节点是否存在
	public boolean existsNode(Node<T> node) {
		boolean result = false;
		Node<T> tmp = head;
		while (tail != tmp.next) {
			if (!node.value.equals(tmp.next.value)) {
				result = false;
			} else {
				result = true;
				return result;
			}
			tmp = tmp.next;
		}
		return result;
	}

	//搜索指定位置的节点
	public Node<T> searchNode(int pos) {
		if (isEmpty()) {
			System.out.println("The Doubly Linked List is empty");
		} else {
			Node<T> node = head;
			int count = 0;
			if (pos >= len) {
				return null;
			} else {
				while (tail != node.next) {
					if (count == pos) {
						return node.next;
					}
					count++;
					node = node.next;
				}
				System.out.println();
			}
		}
		return null;
	}

	//删除指定的node
	public void deleteNode(Node<T> node) {
		Node<T> tmp = head;
		if (existsNode(node)) {
			System.out.println("删除节点的值为：" + node.value);
			while (tail != tmp.next) {
				if (node.value.equals(tmp.next.value)) {
					System.out.println("删除节点的前驱节点的值为：" + tmp.value);
					tmp.next = tmp.next.next;//修改后继
					tmp.next.next.prev = tmp;//修改前驱
				}
				tmp = tmp.next;
			}
		}
	}

	//删除指定位置的node
	public void deleteNode(int pos) {
		 Node<T> node = searchNode(pos);
		 System.out.println("位置从0开始，pos="+pos+"位置对应的节点值为:"+node.value);
		 deleteNode(node);
	}
	
	//获取双向链表中第一个元素：不包括头指针
	public Node<T> getFirstNode()
	{
		return searchNode(0);
	}
	
	//获取双向链表最后一个元素：不包括尾指针
	public Node<T> getLastNode()
	{
		return searchNode(len-1);
	}

}

public class DoubleLinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoubleLinkedList<Integer> doublyLinkedList = new DoubleLinkedList<Integer>();
		/************************
		 * 初始化操作
		 * **********************/
		doublyLinkedList.init();
		System.out.println("########################################");
		System.out.println("新建双向链表：***");
		doublyLinkedList.traversalFromHead();
		for (int i = 0; i < 2; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = i;
			doublyLinkedList.addToHead(node);
		}
		System.out.println("########################################");
		System.out.println("初始化双向链表：***");
		doublyLinkedList.traversalFromHead();

		for (int i = 10; i < 16; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = i;
			doublyLinkedList.addToHead(node);
		}

		/************************
		 * 插入操作
		 * **********************/
		Node<Integer> node = new Node<Integer>();
		node.value = 88;
		doublyLinkedList.insertAfter(node, 11);

		node = new Node<Integer>();
		node.value = 24;
		doublyLinkedList.insertBefore(node, 11);

		node = new Node<Integer>();
		node.value = 100;
		doublyLinkedList.addToTail(node);
		System.out.println("########################################");
		System.out.println("双向链表插入操作：***");
		doublyLinkedList.traversalFromHead();
		System.out.println("双向链表长度为：" + doublyLinkedList.getLength());

		/************************
		 * 删除操作
		 * **********************/
		//删除当前双向链表中第一个节点
		doublyLinkedList.deleteFirst();
		//删除当前双向链表中最后节点
		doublyLinkedList.deleteLast();
		//删除当前双向链表中存在的节点
		node = new Node<Integer>();
		node.value = 11;
		doublyLinkedList.deleteNode(node);
		//删除不存在的节点
		node = new Node<Integer>();
		node.value = 1000;
		doublyLinkedList.deleteNode(node);
		System.out.println("########################################");
		System.out.println("双向链表删除操作：***");
		doublyLinkedList.traversalFromHead();
		System.out.println("双向链表长度为：" + doublyLinkedList.getLength());
		//删除指定位置的节点
		doublyLinkedList.deleteNode(1);
		System.out.println("双向链表删除操作：***");
		doublyLinkedList.traversalFromHead();
		System.out.println("双向链表长度为：" + doublyLinkedList.getLength());

		
		
		/************************
		 * 清空列表操作
		 * **********************/
		doublyLinkedList.clearList();
		System.out.println("########################################");
		System.out.println("双向链表清空操作：***");
		doublyLinkedList.traversalFromHead();
	}
}
