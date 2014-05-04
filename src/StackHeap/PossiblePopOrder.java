package StackHeap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// http://zhedahht.blog.163.com/blog/static/25411174200732102055385/
class OrderStack {
	// 栈的数据区
	private List<Object> list;
	// 栈顶指针
	private int topPointer;

	@SuppressWarnings("unchecked")
	public OrderStack() {
		this.list = new ArrayList();
		this.topPointer = -1;
	}

	@SuppressWarnings("unchecked")
	public OrderStack(List<Object> list, int topPointer) {
		if (null == list) {
			list = new ArrayList();
		}
		this.list = list;
		this.topPointer = list.size() - 1;
	}

	/**
	 * 入栈方法
	 */
	public void push(Object Object) {
		this.list.add(Object);
		topPointer++;
	}

	/**
	 * 出栈方法
	 */
	public Object pop() {
		if (!isEmpty()) {
			Object obj = this.list.remove(this.topPointer);
			this.topPointer--;
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * 判断栈是否为空
	 */
	public boolean isEmpty() {
		if (topPointer == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取栈的大小
	 */
	public int getStackSize() {
		return this.list.size();
	}

	/**
	 * 返回栈顶元素
	 */
	public Object top() {
		if (topPointer == -1) {
			return null;
		} else {
			return list.get(topPointer);
		}
	}

	/**
	 * 遍历栈的内部数据信息:按list打印出数据
	 */
	public void printStack() {
		if (this.list.size() > 0) {
			//System.out.println("The data int stack is:");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(this.list.get(i).toString() + "  ");
			}
			System.out.println();
		} else {
			System.out.println("The stack is empty!");
		}
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getTopPointer() {
		return topPointer;
	}

	public void setTopPointer(int topPointer) {
		this.topPointer = topPointer;
	}
}

public class PossiblePopOrder {

	public static boolean isPossiblePopOrder(LinkedList<Integer> queue,
			LinkedList<Integer> possible) {
		LinkedList<Integer> pops = new LinkedList<Integer>();
		LinkedList<Integer> leftpush = new LinkedList<Integer>();
		while (queue.size() > 0 && queue.getFirst() != possible.getFirst()) {
			pops.add(queue.remove());
		}
		pops.add(queue.remove());
		leftpush.addAll(queue);
		//printLinkedList(pops);
		//printLinkedList(leftpush);
		// 1 2 3
		// System.out.println(pushes.getFirst() + " " + pushes.getLast());

		/*
		 * leftpush      pops
		 *    5           1    first
		 *                2
		 *    			  3    
		 *                4    last
		 * */
		// 注意LinkedList 数据结构的特点
		while (possible.size() > 0) {
			if (pops.size() > 0 && pops.getLast() == possible.getFirst()) {
				pops.removeLast();
				possible.remove();
			} else if (leftpush.size() > 0) {
				while (leftpush.size() > 0
						&& leftpush.getFirst() != possible.getFirst()) {
					pops.add(leftpush.remove());
				}
				//将leftpush.getFirst()==possible.getFirst()的数据，从leftpush中移除
				pops.add(leftpush.remove());
			} else {
				break;
			}
		}
		if (possible.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	static void printLinkedList(LinkedList<Integer> list) {
		for (int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + " ");
		System.out.println();
	}

	public static boolean isPossiblePopOrder(OrderStack pushS,
			OrderStack possible) {

		/*
		 *             辅助栈      入栈      出栈
		 *              pops     pushS   possible
		 *   top                    1       4
		 *                          2       5
		 *    			            3 		3
		 *                          4       2
		 *   base                   5 		1
		 * */
		                     
		/*
		 *             辅助栈      入栈      出栈
		 *              pops     pushS   possible
		 *   top          4         5       4
		 *                3                 5
		 *    			  2           		3
		 *                1                 2
		 *   base                     		1
		 * */		
		OrderStack pops = new OrderStack();
		
		while (pushS.getStackSize() > 0 && pushS.top() != possible.top()) {
			pops.push(pushS.pop());
		}
		pops.push(pushS.pop());
		//pops.printStack();

		while (possible.getStackSize() > 0) {
			// 如果辅助栈pops中栈顶元素与出栈possible中栈顶元素相同，则该元素出栈
			if (pops.getStackSize() > 0 && pops.top() == possible.top()) {
				pops.pop();
				possible.pop();
			} 
			// 若不等，继续将pushS中数据压入到辅助栈pops中
			else if (pushS.getStackSize() > 0) {
				while (pushS.getStackSize() > 0
						&& pushS.top() != possible.top()) {
					pops.push(pushS.pop());
				}
				//对pushS.pop()==possible.pop()的数据，从pushS中移除，压入到辅助栈
				pops.push(pushS.pop());
			}
			// pushS中无元素可压入辅助栈，说明不是可弹出的顺序，此时possible栈不为空
			else {
				break;
			}
		}
		
		if (possible.getStackSize() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub  
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		LinkedList<Integer> possible = new LinkedList<Integer>();
		possible.add(4);
		possible.add(5);
		possible.add(3);
		possible.add(2);
		possible.add(1);
		System.out.println(isPossiblePopOrder(queue, possible));

		queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		LinkedList<Integer> possible2 = new LinkedList<Integer>();
		possible2.add(4);
		possible2.add(3);
		possible2.add(5);
		possible2.add(1);
		possible2.add(2);
		System.out.println(isPossiblePopOrder(queue, possible2));

		queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		LinkedList<Integer> possible3 = new LinkedList<Integer>();
		possible3.add(2);
		possible3.add(3);
		possible3.add(5);
		possible3.add(1);
		possible3.add(4);
		System.out.println(isPossiblePopOrder(queue, possible3));
		/*OrderStack stack = new OrderStack();
		int i = 2;
		stack.push(i);
		String str = "test";
		stack.push(str);
		double d = 23;
		stack.push(d);
		System.out.println(stack.top());
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();*/

		OrderStack pushS = new OrderStack();
		int[] arr = { 5, 4, 3, 2, 1 };//{1,2,3,4,5}; //push入栈序列反转		
		for (int data : arr)
			pushS.push(data);
		//pushS.printStack();
		//System.out.println(pushS.top());
		OrderStack popS = new OrderStack();
		int[] arr2 = { 1, 2, 5, 3, 4 };//{4,3,5,1,2}; // pop出栈序列反转
		for (int data : arr2)
			popS.push(data);
		//popS.printStack();

		System.out.println(isPossiblePopOrder(pushS, popS));
	}

}
