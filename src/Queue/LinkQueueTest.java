package Queue;

// ��ʽ����
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

	//�����β
	public void enQueue(T key) {
		QNode<T> node = new QNode<T>();
		node.next = null;
		node.data = key;

		// ��Ҫ�����ж϶����Ƿ�Ϊ�գ��Ƿ�Ϊ��һ�β��롣
		// ����Ϊ��ʱ��ӵ�node
		if (front.next == null) {
			front.next = node;
		}
		rear.next = node;//�޸ļ�Ӻ��
		rear = node;//�޸�ֱ�Ӻ��
	}

	//ɾ����ͷ
	public QNode<T> deQueue() {
		QNode<T> node = new QNode<T>();
		if (!isEmpty()) {
			node = front.next;
			front.next = node.next;
			System.out.println("ɾ����valueֵΪ:" + node.data);

			//ɾ�����������һ��Ԫ�صĴ���
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
			System.out.print("��ǰ����Ϊ��");
		} else {
			//��˫���б���������
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
		 * ����в���
		 * **********************/
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.queueTraverse();
		/************************
		 * �����в���
		 * **********************/
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.queueTraverse();
	}
}