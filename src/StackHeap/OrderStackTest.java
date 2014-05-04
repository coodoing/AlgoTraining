package StackHeap;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * 顺序栈，非链式栈
 * http://www.cppblog.com/pencil/archive/2009/09/27/97391.html
 * 
 * ***/
class Stack {

	// 栈的数据区
	private List<Object> list;
	// 栈顶指针
	private int topPointer;

	@SuppressWarnings("unchecked")
	public Stack() {
		this.list = new ArrayList();
		this.topPointer = -1;
	}

	@SuppressWarnings("unchecked")
	public Stack(List<Object> list, int topPointer) {
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
	 * 打印栈的内部数据信息
	 */
	public void printStack() {
		if (this.list.size() > 0) {
			System.out.println("The data int stack is:");
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

public class OrderStackTest {
	public static void main(String[] args) {
		Stack stack = new Stack();
		int i = 5;
		stack.push(i);
		String str = "stack";
		stack.push(str);
		double d = 1.2345;
		stack.push(d);
		System.out.println(stack.top());
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();
	}

}
