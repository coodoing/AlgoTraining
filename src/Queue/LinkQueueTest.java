package Queue;

// 链式队列
class QNode<T> {
	T data;
	QNode<T> next;

	public boolean equals(QNode<T> node) {
		if (data.equals(node.data)) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return data.hashCode();
	}
}

class LinkQueue<T> {
	QNode<T> front;
	QNode<T> rear;
	int size;

	public LinkQueue() {

	}

	public void init() {
		front = new QNode<T>();
		rear = front;

		front.next = null;
		size = 0;
	}

	public void destroy() {

	}

	//插入队尾
	public void enQueue(T key) {
		QNode<T> node = new QNode<T>();
		node.next = null;
		node.data = key;

		// 主要用于判断队列是否为空，是否为第一次插入。
		// 队列为空时添加的node
		if (front.next == null) {
			front.next = node;
		}
		rear.next = node;//修改间接后继
		rear = node;//修改直接后继
	}

	//删除队头
	public QNode<T> deQueue() {
		QNode<T> node = new QNode<T>();
		if (!isEmpty()) {
			node = front.next;
			front.next = node.next;
			System.out.println("删除的value值为:" + node.data);

			//删除队列中最后一个元素的处理
			if (rear == node)
				rear = front;
			return node;
		}
		return null;
	}

	public boolean isEmpty() {
		if (front == rear)
			return true;
		return false;
	}

	public void queueTraverse() {
		if (isEmpty()) {
			System.out.print("当前队列为空");
		} else {
			//与双向列表有所区别
			QNode<T> node = front;
			while (node.next != null) {
				System.out.print(node.next.data + "-->");
				node = node.next;
			}
			System.out.println();
		}
	}
}

public class LinkQueueTest {

	public static void main(String[] args) {
		LinkQueue<Integer> queue = new LinkQueue<Integer>();
		queue.init();
		/************************
		 * 入队列操作
		 * **********************/
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.queueTraverse();
		/************************
		 * 出队列操作
		 * **********************/
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.queueTraverse();
	}
}