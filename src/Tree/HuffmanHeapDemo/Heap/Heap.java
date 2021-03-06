package Tree.HuffmanHeapDemo.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 基于堆的优先级队列实现
 * 
 * 堆（heap）是一个完使用二叉树，它是一棵空树或 1、根元素大于左右子节点（这时叫大顶堆） 2、左右子节点又是堆
 * 
 * 堆是一种完全二叉树，所以这里先要熟悉要用的二叉树几个性质： N（N>1）个节点的的完全二叉树从层次从左自右编号，最后一个分枝节点（非叶子节点）的编号为
 * N/2 取 整。且对于编号 i（1<=i<=N 编号从1开始）有：父节点为 i/2 向下取整；若2i>N，则节点i没有左孩子
 * ，否则其左孩子为2i；若2i+1>N，则没有右孩子，否则其右孩子为2i+1。
 * 注，这里使用完全二叉树只是为了好描述算法，它只是一种逻辑结构，真真在实现时我们还是使用数组来存
 * 储这棵二叉树的，因为完全二叉树与数组可以连续的一一对应赶快来
 * 
 * 数组具有的随机访问特性对于堆的处理很方便：给定元素的索引值，很快就能得到该元素的子节点元素。例 如，节点编号为i的元素的子节点分别为 2i 或
 * 2i+1，那么对应到数组的索引号就分别为 2i-1 或 2i， 节点编号为j的元素的父节点为 j/2
 * 向下取整，对应的数组元素索引在父节点编号基础上减一即可得到。
 * 所以，堆可以快速交换父节点和小于它的子节点的值，这使得堆成为实现PriorityQueue接口的一种高效 数据结构。
 * 
 * @author jzj
 * @data 2010-1-5
 * @param <E>
 */
public class Heap<E extends Comparable<E>> implements PriorityQueue<E> {
	private E[] heap;// 使用数组来实现堆存储
	private Comparator<E> comp;
	private int size;

	public Heap() {
		heap = (E[]) new Comparable[5];
	}

	public Heap(Comparator<E> c) {
		this();
		comp = c;
	}

	// 类似于TreeMap中的私有方法 compare(Object k1, Object k2)
	private int compare(E elem1, E elem2) {
		return comp == null ? elem1.compareTo(elem2) : comp.compare(elem1,
				elem2);
	}

	// 添加元素，
	public void add(E elem) {
		if (++size == heap.length) {// 预判断放入后是否满，如果满则先扩容后再加
			E[] newHeap = (E[]) new Comparable[2 * heap.length];
			System.arraycopy(heap, 0, newHeap, 0, size);
			heap = newHeap;
		}
		heap[size - 1] = elem;
		adjustUp();// 添加后堆规则可能打破，所以需重新调整堆结构
	}

	// 添加元素后向上调整堆结构，构造小顶堆，即添加的小的元素向上(根)浮
	private void adjustUp() {
		/*
		 * 添加28 28小于50，交换 → 26 → 26 / \ / \ 32 30 32 30 / \ / \ / \ / \ 48 50 85
		 * 36 48 28 85 36 / \ / \ / \ / \ 90 80 55 28 90 80 55 50
		 * 
		 * 现28小于父，还需交换 现28大于父26，所以调整完成 → 26 / \ 28 30 / \ / \ 48 32 85 36 / \ / \
		 * 90 80 55 50
		 * 
		 */
		int child = size;// 新加入的叶节点编号，即最后一个节点
		int parent;// 父节点编号
		while (child > 1) {// 如果调整到了根节点则直接退出
			parent = child / 2;// 新增叶子节点的父节点编号
			// 如果父节点小于等于子节点（新增节点），则退出
			if (compare(heap[parent - 1], heap[child - 1]) <= 0) {
				break;
			}
			// 如果新增节点小于它的父节点时，则交换
			E tmp = heap[parent - 1];
			heap[parent - 1] = heap[child - 1];
			heap[child - 1] = tmp;
			child = parent;// 新增节点移到父节点位置，以便下次循环
		}
	}

	public E getMin() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return heap[0];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 删除堆顶元素，使用最后一个元素替换根元素，然后再进结构调整
	public E removeMin() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		/*
		 * 删除根元素，根元素是最小元素，删除后使用树的最后叶节点替换他，此时堆结构会破坏， 需从根节点向下重新调整堆结构
		 * 
		 * 删除26 交换根26与最后叶节点55 55比最小子节点30大，需交换 → 26 → 55 → / \ / \ 32 30 32 30 / \ / \ / \ / \
		 * 48 50 85 36 48 50 85 36 / \ / /\ 90 80 55 90 80
		 * 
		 * 55比最小子节点36大，需交换 30 → 30 堆结构恢复，调整结束 / \ / \ 32 55 32 36 / \ / \ / \ / \
		 * 48 50 85 36 48 50 85 55 / \ / / \ / 90 80 55 90 80 55
		 */
		// 交换最后与根元素位置
		E minElem = heap[0];// 堆顶元素
		heap[0] = heap[size - 1];
		// 不能把heap[--size]置为null，因为后面的堆排序方法heapSort要用
		heap[--size] = minElem;
		adjustDown(1);// 删除后从根开始向下调整
		return minElem;
	}

	// 堆结构调整，从指定的节点向下开始调整
	private void adjustDown(int nodeNum) {

		int parent = nodeNum;// 从指定节点开始往下调整
		int child = 2 * parent;// 指定节点左子节点编号
		// 如果左孩子存在
		while (child <= size) {
			int minNum = parent;// 假设父就是最小的
			// 与左孩子比，如果比左孩子大，则最小设置为左孩子
			if (compare(heap[parent - 1], heap[child - 1]) > 0) {
				minNum = child;
			}

			// 如果右孩子存在，则更小时
			if ((child + 1) <= size
					&& compare(heap[minNum - 1], heap[child]) > 0) {
				minNum = child + 1;
			}

			// 如果发现最小元素为子节点时需交换
			if (minNum != parent) {
				E tmp = heap[minNum - 1];
				heap[minNum - 1] = heap[parent - 1];
				heap[parent - 1] = tmp;
				parent = minNum;
				child = 2 * minNum;
			} else {// 否则退出
				break;
			}
		}
	}

	/**
	 * 堆排序 使用堆结构对某个数组进行排序
	 * 
	 * @param elems
	 */
	public E[] heapSort(E[] elems) {

		int length = elems.length;

		heap = elems;
		size = length;
		/*
		 * 创建初始堆，从最后一个非叶子节点开始调整所有的非叶子节点，直到根节点， 所有的节点调整都采用向下调整的方法
		 */
		for (int i = length / 2; i >= 1; i--) {
			adjustDown(i);
		}
		// 再对初始堆进行排序
		while (size > 0) {
			// 删除的过程实质上就是排序过程
			removeMin();
		}
		return elems;
	}

	// 树的层次遍历
	private void levelOrder() {
		if (size == 0) {
			return;
		}
		LinkedList queue = new LinkedList();
		queue.add(1);
		System.out.print("层次遍历 - ");
		while (!queue.isEmpty()) {
			int num = (Integer) queue.removeFirst();
			System.out.print(heap[num - 1] + " ");

			if (num * 2 <= size) {
				queue.add(num * 2);
				if (num * 2 + 1 <= size) {
					queue.add(num * 2 + 1);
				}
			}
		}
		System.out.println();
	}

	public int size() {

		return size;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<Integer>();

		Random rr = new Random(System.currentTimeMillis());
		Integer[] itg = new Integer[rr.nextInt(20)];
		for (int i = 0; i < itg.length; i++) {
			Integer tmp = new Integer(rr.nextInt(100));
			h.add(tmp);
			itg[i] = tmp;
		}
		h.levelOrder();
		System.out.print("优先队列 - ");
		while (h.isEmpty() == false) {
			System.out.print(h.removeMin() + " ");
		}

		System.out.println();
		itg = h.heapSort(itg);
		System.out.print("堆排序 - ");
		System.out.println(Arrays.toString(itg));
	}
}
