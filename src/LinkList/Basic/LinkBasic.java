package LinkList.Basic;

// �������Ƿ��л������������Ƿ��й����ڵ�����
// http://blog.csdn.net/v_JULY_v/article/details/6447013
// http://www.cppblog.com/humanchao/archive/2008/04/17/47357.html
class Node<T> {
	T value;
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

class LinkList<T> {
	private Node<T> head;
	private int len;

	public LinkList() {
		initLinkList();
	}

	private void initLinkList() {
		head = new Node<T>();// ������
		len = 0;

		head.next = null;
	}

	// ����������
	void insertAfterHead(Node<T> node) {
		node.next = head.next;
		head.next = node;
		len++;
	}

	void insertAtLast(Node<T> node) {
		Node<T> p = getLastNode();

		node.next = p.next; // null
		p.next = node;
		len++;

	}

	// ������
	void insertFormLoop(Node<T> node) {
		Node<T> p = getLastNode();

		node.next = p; // ������
		p.next = node;
		len++;
	}

	Node<T> getHeadNode() {
		return head;
	}

	Node<T> getLastNode() {
		Node<T> p = head;
		// ��ȡ���һ���ڵ�
		while (p.next != null) {
			p = p.next;
		}
		return p;
	}

	int getLength() {
		return len;
	}

	// ����
	void traversal() {
		Node<T> node = head.next;
		while (node != null) {
			System.out.print(node + "-->");
			node = node.next;
		}
		System.out.println();
	}

	/* http://www.cppblog.com/humanchao/archive/2008/04/17/47357.html
	 ��������ָ��(fast, slow)����ʼֵ��ָ��ͷ��slowÿ��ǰ��һ����fastÿ��ǰ�����������������ڻ�����fast�ض��Ƚ��뻷����slow����뻷������ָ��ض�������
	 (��Ȼ��fast����ͷ��β��ΪNULL����Ϊ�޻�����)*/
	// �ж��Ƿ��л�
	boolean isExsitLoop() {
		Node<T> slow = head;
		Node<T> fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				return true;
		}
		return false;
	}
	
	// �л�������£���ȡ�����ڵ�
	Node<T> getMeetNode()
	{
		Node<T> slow = head;
		Node<T> fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				return slow;
		}
		return null;
	}

	// �ҳ��������
	/*
	 * ��fast����slow����ʱ��slow�϶�û���߱�����������fast�Ѿ��ڻ���ѭ����nȦ(1<=n)������slow����s������fast����2s����fast����������s �����ڻ��϶�ת��nȦ�����軷��Ϊr����
	   2s = s + nr
	   s= nr
	   ����������L����ڻ������������Ϊx����㵽����ڵ�ľ���Ϊa��
	   a + x = nr
	   a + x = (n �C 1)r +r = (n-1)r + L - a
	   a = (n-1)r + (L �C a �C x)
	   (L �C a �C x)Ϊ�����㵽����ڵ�ľ��룬�ɴ˿�֪��������ͷ������ڵ����(n-1)ѭ���ڻ�+�����㵽����ڵ㣬�������Ǵ�����ͷ����������ֱ���һ��ָ�룬ÿ�θ���һ��������ָ��ض���������������һ��Ϊ����ڵ㡣
	 * */
	Node<T> getLoopEntry() {
		Node<T> slow = head;
		Node<T> fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				break;
		}

		if (fast == null || fast.next == null)
			return null;

		// slowָ������ͷ�ڵ�
		// fastָ��slow��fast�����Ľڵ�
		slow = head;

		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return fast;
	}	

}

public class LinkBasic {
	// java�ڲ���ĳ�ʼ��
	class StaticInnerTest {
		void innerClass() {
			System.out.println("java�ڲ�����ԣ�innerClass");
		}
	}
	
	/* http://blog.csdn.net/v_JULY_v/article/details/6447013
	   �ж��Ƿ��й�����
	   1�����������޻������:�ж�β�ڵ��Ƿ���� 
	   2��������������ж�һ��������ָ���������Ǹ��ڵ㣬�ڲ�����һ�������ϡ�
	*/
	static boolean isCommonNode(LinkList<Integer> link, LinkList<Integer> link2) {
		Node<Integer> last;
		Node<Integer> last2;
		// ��������޻�
		if (link.isExsitLoop() == false && link2.isExsitLoop() == false) {
			// ��ȡ���һ���ڵ�
			last = (Node<Integer>) link.getLastNode();
			last2 = (Node<Integer>) link2.getLastNode();
			System.out.println("����1�����һ���ڵ�Ϊ:"+last.value+"   ����2�����һ���ڵ�Ϊ:"+last2.value);
			return last.value == last2.value;
		}
		//һ���л���һ���޻�
		else if(link.isExsitLoop()!=link2.isExsitLoop())
		{
			return false;
		}	
		//�������л����жϻ���Ľڵ��Ƿ��ܵ�����һ��������Ľڵ�
		else
		{
			// �����ڵ�
			Node<Integer> meetNode = link.getMeetNode();
			Node<Integer> meetNode2 = link2.getMeetNode();
			Node<Integer> p = meetNode.next;
			
			// ���б���
			while(p!=meetNode)
			{
				if(p == meetNode2)
					return true;
				p = p.next;
			}
			return false;
		}
	}
	
	// �����������ཻ�ĵ�һ���ڵ�
	/*
	 * ˼·���������β�����һ���ģ�˵���������غϣ�������������û�й����Ľ�㡣
        �������˼·�У�˳�������������β����ʱ�����ǲ��ܱ�֤������������ͬʱ����β��㡣   
    ������Ϊ��������һ������һ�������������һ���������һ����L����㣬�������ڳ��������ϱ���L����㣬
    ֮����ͬ�����������ʱ�����Ǿ��ܱ�֤ͬʱ�������һ������ˡ�
        ������������ӵ�һ��������㿪ʼ�������β��㣬��һ�������غϵġ���ˣ����ǿ϶�Ҳ��ͬʱ�����һ�������ġ�
    �����ڱ����У���һ����ͬ�Ľ����ǵ�һ�������Ľ�㡣
	 * */
	
	static Node<Integer> getFirstCommonNode(LinkList<Integer> link, LinkList<Integer> link2)
	{
		Node<Integer> l = link.getHeadNode();
		Node<Integer> s = link2.getHeadNode();
		int len1 = link.getLength();
		int len2 = link2.getLength();		
		int diff = len1 - len2;  
		
		if(len1<len2)
		{
			l = link2.getHeadNode();
			s = link.getHeadNode();
			diff = len2 - len1;
		}
		
		// ��������diff����  
	    for(int i = 0; i < diff; i++ )  
	        l = l.next;  
	   
	    while(l!=null&&s!=null&&l.value!=s.value)
	    {
	    	l = l.next;
	    	s = s.next;
	    }
	    
	    Node<Integer> comNode = null ;
	    if(l.value==s.value)
	    	comNode = l;
	    return comNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkBasic.StaticInnerTest test = new LinkBasic().new StaticInnerTest();
		test.innerClass();

		/************************
		 * �γɳ�ʼ������
		 * **********************/
		LinkList<Integer> link = new LinkList<Integer>();
		LinkList<Integer> link2 = new LinkList<Integer>();
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		int[] arr2 = { 7, 1, 2, 3, 6, 8, 9, 10 };
		for (int i = 0; i < arr.length; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = arr[i];
			link.insertAfterHead(node);
		}

		for (int i = 0; i < arr2.length; i++) {
			Node<Integer> node = new Node<Integer>();
			node.value = arr2[i];
			link2.insertAfterHead(node);
		}

		/************************
		 * ������β�������µĽ��
		 * **********************/
		Node<Integer> node = new Node<Integer>();
		node.value = 7;
		link.insertAtLast(node);

		System.out.println("����1���ȣ�" + link.getLength());
		System.out.println("����1��ͷ��Ϣ��" + link.getHeadNode().value);
		System.out.println("����1���������");
		link.traversal();

		System.out.println("����2���ȣ�" + link2.getLength());
		System.out.println("����2���������");
		link2.traversal();

		/************************
		 * �ж����������Ƿ��й����ڵ�
		 * **********************/
		System.out.println("�Ƿ���ڹ����ڵ㣺"+isCommonNode(link,link2));
		System.out.println("��һ�������ڵ�Ϊ��"+getFirstCommonNode(link,link2).value);

		/************************
		 * ������β�������㣬�γɻ�
		 * 
		 * ������������һ��Ԫ��p�����ڵ�node��ʹnode��nextָ����ָ��p���γɻ��ṹ
		 * **********************/
		Node<Integer> node2 = new Node<Integer>();
		node2.value = 19;
		link.insertFormLoop(node2);
		if (link.isExsitLoop()) {
			System.out.println("��������ڻ���");
			System.out.println("������ڽڵ�:" + link.getLoopEntry().value);
		}

	}

}
