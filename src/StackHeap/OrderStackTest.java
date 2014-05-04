package StackHeap;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * ˳��ջ������ʽջ
 * http://www.cppblog.com/pencil/archive/2009/09/27/97391.html
 * 
 * ***/
class Stack {

	// ջ��������
	private List<Object> list;
	// ջ��ָ��
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
	 * ��ջ����
	 */
	public void push(Object Object) {
		this.list.add(Object);
		topPointer++;
	}

	/**
	 * ��ջ����
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
	 * �ж�ջ�Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		if (topPointer == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ȡջ�Ĵ�С
	 */
	public int getStackSize() {
		return this.list.size();
	}

	/**
	 * ����ջ��Ԫ��
	 */
	public Object top() {
		if (topPointer == -1) {
			return null;
		} else {
			return list.get(topPointer);
		}
	}

	/**
	 * ��ӡջ���ڲ�������Ϣ
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
