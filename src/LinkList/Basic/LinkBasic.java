package LinkList.Basic;

// 单链表是否有环和两个链表是否有公共节点问题
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
		head = new Node<T>();// 空链表
		len = 0;

		head.next = null;
	}

	// 链表插入操作
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

	// 产生环
	void insertFormLoop(Node<T> node) {
		Node<T> p = getLastNode();

		node.next = p; // 产生环
		p.next = node;
		len++;
	}

	Node<T> getHeadNode() {
		return head;
	}

	Node<T> getLastNode() {
		Node<T> p = head;
		// 获取最后一个节点
		while (p.next != null) {
			p = p.next;
		}
		return p;
	}

	int getLength() {
		return len;
	}

	// 遍历
	void traversal() {
		Node<T> node = head.next;
		while (node != null) {
			System.out.print(node + "-->");
			node = node.next;
		}
		System.out.println();
	}

	/* http://www.cppblog.com/humanchao/archive/2008/04/17/47357.html
	 设置两个指针(fast, slow)，初始值都指向头，slow每次前进一步，fast每次前进二步，如果链表存在环，则fast必定先进入环，而slow后进入环，两个指针必定相遇。
	 (当然，fast先行头到尾部为NULL，则为无环链表)*/
	// 判断是否有环
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
	
	// 有环的情况下，获取相遇节点
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

	// 找出环的入口
	/*
	 * 当fast若与slow相遇时，slow肯定没有走遍历完链表，而fast已经在环内循环了n圈(1<=n)。假设slow走了s步，则fast走了2s步（fast步数还等于s 加上在环上多转的n圈），设环长为r，则：
	   2s = s + nr
	   s= nr
	   设整个链表长L，入口环与相遇点距离为x，起点到环入口点的距离为a。
	   a + x = nr
	   a + x = (n – 1)r +r = (n-1)r + L - a
	   a = (n-1)r + (L – a – x)
	   (L – a – x)为相遇点到环入口点的距离，由此可知，从链表头到环入口点等于(n-1)循环内环+相遇点到环入口点，于是我们从链表头、与相遇点分别设一个指针，每次各走一步，两个指针必定相遇，且相遇第一点为环入口点。
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

		// slow指向链表头节点
		// fast指向slow和fast相遇的节点
		slow = head;

		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return fast;
	}	

}

public class LinkBasic {
	// java内部类的初始化
	class StaticInnerTest {
		void innerClass() {
			System.out.println("java内部类测试，innerClass");
		}
	}
	
	/* http://blog.csdn.net/v_JULY_v/article/details/6447013
	   判断是否有公共点
	   1、两个链表都无环的情况:判断尾节点是否相等 
	   2、如果都带环，判断一链表上俩指针相遇的那个节点，在不在另一条链表上。
	*/
	static boolean isCommonNode(LinkList<Integer> link, LinkList<Integer> link2) {
		Node<Integer> last;
		Node<Integer> last2;
		// 两链表均无环
		if (link.isExsitLoop() == false && link2.isExsitLoop() == false) {
			// 获取最后一个节点
			last = (Node<Integer>) link.getLastNode();
			last2 = (Node<Integer>) link2.getLastNode();
			System.out.println("链表1的最后一个节点为:"+last.value+"   链表2的最后一个节点为:"+last2.value);
			return last.value == last2.value;
		}
		//一个有环，一个无环
		else if(link.isExsitLoop()!=link2.isExsitLoop())
		{
			return false;
		}	
		//两个都有环，判断环里的节点是否能到达另一个链表环里的节点
		else
		{
			// 相遇节点
			Node<Integer> meetNode = link.getMeetNode();
			Node<Integer> meetNode2 = link2.getMeetNode();
			Node<Integer> p = meetNode.next;
			
			// 环中遍历
			while(p!=meetNode)
			{
				if(p == meetNode2)
					return true;
				p = p.next;
			}
			return false;
		}
	}
	
	// 求两个链表相交的第一个节点
	/*
	 * 思路：如果两个尾结点是一样的，说明它们有重合；否则两个链表没有公共的结点。
        在上面的思路中，顺序遍历两个链表到尾结点的时候，我们不能保证在两个链表上同时到达尾结点。   
    这是因为两个链表不一定长度一样。但如果假设一个链表比另一个长L个结点，我们先在长的链表上遍历L个结点，
    之后再同步遍历，这个时候我们就能保证同时到达最后一个结点了。
        由于两个链表从第一个公共结点开始到链表的尾结点，这一部分是重合的。因此，它们肯定也是同时到达第一公共结点的。
    于是在遍历中，第一个相同的结点就是第一个公共的结点。
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
		
		// 长的先走diff长度  
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
		 * 形成初始单链表
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
		 * 在链表尾部插入新的结点
		 * **********************/
		Node<Integer> node = new Node<Integer>();
		node.value = 7;
		link.insertAtLast(node);

		System.out.println("链表1长度：" + link.getLength());
		System.out.println("链表1表头信息：" + link.getHeadNode().value);
		System.out.println("链表1遍历结果：");
		link.traversal();

		System.out.println("链表2长度：" + link2.getLength());
		System.out.println("链表2遍历结果：");
		link2.traversal();

		/************************
		 * 判断两个链表是否有公共节点
		 * **********************/
		System.out.println("是否存在公共节点："+isCommonNode(link,link2));
		System.out.println("第一个公共节点为："+getFirstCommonNode(link,link2).value);

		/************************
		 * 在链表尾部插入结点，形成环
		 * 
		 * 即在链表的最后一个元素p后插入节点node，使node的next指针域指向p，形成环结构
		 * **********************/
		Node<Integer> node2 = new Node<Integer>();
		node2.value = 19;
		link.insertFormLoop(node2);
		if (link.isExsitLoop()) {
			System.out.println("单链表存在环。");
			System.out.println("环的入口节点:" + link.getLoopEntry().value);
		}

	}

}
