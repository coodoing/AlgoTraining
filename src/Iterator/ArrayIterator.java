package Iterator;

import java.util.*;

public class ArrayIterator implements Iterator<Object> {

	protected Object[] array = { "one", "two", "three" };
	protected int index = 0;

	public ArrayIterator() {
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return (index < array.length);
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if (index >= array.length)
			throw new IndexOutOfBoundsException("only " + array.length
					+ " elements");
		return array[index++];
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"This demo does not implement the remove method");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayIterator ait = new ArrayIterator();
		while(ait.hasNext())
		{
			System.out.println(ait.next());
		}
	}
}
