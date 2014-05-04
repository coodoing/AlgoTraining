package LinkList;

// ˫������
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
	private Node<T> head; // ͷ�ڵ�
	private Node<T> tail;
	private int len; // �����С  

	//��ʼ��LinkedList
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

	// �ж������Ƿ�Ϊ��
	public boolean isEmpty() {
		if (tail == head.next || head == tail.prev) {
			return true;
		}
		return false;
	}

	// ����ı���(��head˳�����)
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

	// ����ı���(��tail�������)
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

	// ��ӽڵ㵽�����ͷ��
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
		
		//������Ҫ���жϣ��ж���βָ���Ƿ�Ϊ�ա����ж��Ƿ��ǵ�һ�β��롣
		
		// �޸�node�ڵ��ǰ���ͺ��
		node.next = head.next;
		node.prev = head; 
		// �޸�nodeǰ���ڵ�ĺ�̼���̽ڵ��ǰ��
		head.next.prev = node;
		head.next = node;
	}

	// ��ӽڵ㵽�����β��
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
		// �޸�node�ڵ��ǰ���ͺ��
		node.prev = tail.prev;
		node.next = tail;
		// �޸�nodeǰ���ڵ�ĺ�̼���̽ڵ��ǰ��
		tail.prev.next = node;
		tail.prev = node;
	}

	// ���ĳ��ֵ��ָ������ֵ�Ľڵ�ǰ��
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

	// ���ĳ��ֵ��ָ������ֵ�Ľڵ����
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

	//ɾ����һ��Ԫ��
	public void deleteFirst() {
		if (isEmpty()) {
			return;
		} else {
			head.next = head.next.next;//�޸ĺ��
			head.next.next.prev = head;//�޸�ǰ��
		}
	}

	//ɾ�����һ��Ԫ��
	public void deleteLast() {
		if (!isEmpty()) {
			//˳���ܴ�
			tail.prev.prev.next = tail;//�޸ĺ��
			tail.prev = tail.prev.prev;//�޸�ǰ��			
		}
	}

	//�ж�ָ���Ľڵ��Ƿ����
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

	//����ָ��λ�õĽڵ�
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

	//ɾ��ָ����node
	public void deleteNode(Node<T> node) {
		Node<T> tmp = head;
		if (existsNode(node)) {
			System.out.println("ɾ���ڵ��ֵΪ��" + node.value);
			while (tail != tmp.next) {
				if (node.value.equals(tmp.next.value)) {
					System.out.println("ɾ���ڵ��ǰ���ڵ��ֵΪ��" + tmp.value);
					tmp.next = tmp.next.next;//�޸ĺ��
					tmp.next.next.prev = tmp;//�޸�ǰ��
				}
				tmp = tmp.next;
			}
		}
	}

	//ɾ��ָ��λ�õ�node
	public void deleteNode(int pos) {
		 Node<T> node = searchNode(pos);
		 System.out.println("λ�ô�0��ʼ��pos="+pos+"λ�ö�Ӧ�Ľڵ�ֵΪ:"+node.value);
		 deleteNode(node);
	}
	
	//��ȡ˫�������е�һ��Ԫ�أ�������ͷָ��
	public Node<T> getFirstNode()
	{
		return searchNode(0);
	}
	
	//��ȡ˫���������һ��Ԫ�أ�������βָ��
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
		 * ��ʼ������
		 * **********************/
		doublyLinkedList.init();
		System.out.println("########################################");
		System.out.println("�½�˫������***");
		doublyLinkedList.traversalFromHead();
		for (int i = 0; i < 2; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = i;
			doublyLinkedList.addToHead(node);
		}
		System.out.println("########################################");
		System.out.println("��ʼ��˫������***");
		doublyLinkedList.traversalFromHead();

		for (int i = 10; i < 16; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = i;
			doublyLinkedList.addToHead(node);
		}

		/************************
		 * �������
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
		System.out.println("˫��������������***");
		doublyLinkedList.traversalFromHead();
		System.out.println("˫��������Ϊ��" + doublyLinkedList.getLength());

		/************************
		 * ɾ������
		 * **********************/
		//ɾ����ǰ˫�������е�һ���ڵ�
		doublyLinkedList.deleteFirst();
		//ɾ����ǰ˫�����������ڵ�
		doublyLinkedList.deleteLast();
		//ɾ����ǰ˫�������д��ڵĽڵ�
		node = new Node<Integer>();
		node.value = 11;
		doublyLinkedList.deleteNode(node);
		//ɾ�������ڵĽڵ�
		node = new Node<Integer>();
		node.value = 1000;
		doublyLinkedList.deleteNode(node);
		System.out.println("########################################");
		System.out.println("˫������ɾ��������***");
		doublyLinkedList.traversalFromHead();
		System.out.println("˫��������Ϊ��" + doublyLinkedList.getLength());
		//ɾ��ָ��λ�õĽڵ�
		doublyLinkedList.deleteNode(1);
		System.out.println("˫������ɾ��������***");
		doublyLinkedList.traversalFromHead();
		System.out.println("˫��������Ϊ��" + doublyLinkedList.getLength());

		
		
		/************************
		 * ����б����
		 * **********************/
		doublyLinkedList.clearList();
		System.out.println("########################################");
		System.out.println("˫��������ղ�����***");
		doublyLinkedList.traversalFromHead();
	}
}
