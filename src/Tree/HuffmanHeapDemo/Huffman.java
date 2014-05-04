package Tree.HuffmanHeapDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Tree.HuffmanHeapDemo.Heap.Heap;


/**
 * ��������������������
 * 
 * @author jzj
 * @data 2010-1-8
 */
public class Huffman {

	// ���������ڵ�
	private static class Entry implements Comparable<Entry> {

		int freq;// �ڵ�ʹ��Ƶ�ʣ����ȼ����Ǹ��ݴ˾���
		String code;// �ڵ�huffman����
		char c;// �ڵ�����Ӧ���ַ�
		Entry left, right, parent;// ��������������ֶ�

		// �ڵ�����ȼ��Ƚ�
		public int compareTo(Entry entry) {
			return freq - entry.freq;
		}

		public String toString() {
			return "(" + c + ":" + code + ")";
		}
	}

	// �������ǽ�ֻ��Unicodeueǰ256���ַ����룬����ֻ������ISO8859-1�ַ���
	protected final int SIZE = 256;

	// �����������ڿ��ٲ�ѯĳ�ַ��Ĺ������
	protected Entry[] leafEntries;

	// �ѣ�������̬�������ȼ�����
	protected Heap<Entry> pq;

	// Ҫ��������봮
	protected String input;

	public Huffman(String input) {
		this.input = input;
		createPQ();
		createHuffmanTree();
		calculateHuffmanCodes();
	}

	// ������ʼ��
	public void createPQ() {

		// ��ʼ����������
		Entry entry;
		leafEntries = new Entry[SIZE];
		for (int i = 0; i < SIZE; i++) {
			leafEntries[i] = new Entry();
			leafEntries[i].freq = 0;// ʹ��Ƶ��
			/*
			 * leafEntries���������е��������ַ��ı����Ӧ�������ڶ�ȡʱ �ܷ���
			 */

			leafEntries[i].c = (char) i;// �ڵ���Ƕ�Ӧ���ַ�

		}

		// ����������
		fillLeafEntries();

		// ��ʼ������ʼ��
		pq = new Heap<Entry>();
		for (int i = 0; i < SIZE; i++) {
			entry = leafEntries[i];
			if (entry.freq > 0) {// �����ʹ�ù�����������
				pq.add(entry);
			}
		}
	}

	// ����������ַ������leafEntries��������
	public void fillLeafEntries() {

		Entry entry;

		for (int i = 0; i < input.length(); i++) {

			entry = leafEntries[(int) (input.charAt(i))];
			entry.freq++;
			entry.left = null;
			entry.right = null;
			entry.parent = null;
		}
	}

	// ������������
	public void createHuffmanTree() {

		Entry left, right, parent;

		// ÿ����Ӷ���ȡ���������������1�����С�ڵ���1ʱ��ʾ���������Ѵ������
		while (pq.size() > 1) {

			// ʹ��̰������ÿ�δ����ȼ������ж�ȡ��С������Ԫ��
			left = (Entry) pq.removeMin();
			left.code = "0";// �����Ϊ���ӽڵ㣬��Ϊ·������Ϊ0

			right = (Entry) pq.removeMin();
			right.code = "1";// �����Ϊ���ӽڵ㣬��Ϊ·������Ϊ1

			parent = new Entry();
			parent.parent = null;

			// ���ڵ��ʹ��Ƶ��Ϊ����֮��
			parent.freq = left.freq + right.freq;
			parent.left = left;
			parent.right = right;
			left.parent = parent;
			right.parent = parent;

			// �ٰѸ��ڵ������У������������ѽṹ
			pq.add(parent);
		}
	}

	// �������봮��ÿ���ַ��Ĺ������
	public void calculateHuffmanCodes() {

		String code;
		Entry entry;

		for (int i = 0; i < SIZE; i++) {

			code = "";
			entry = leafEntries[i];
			if (entry.freq > 0) {// ���ʹ�ù����ַ�ʱ����Ҫ��������

				do {
					/*
					 * ƴ�Ӵ�Ҷ�ڵ㵽���ڵ�·���ϸ�Ԫ�ص�·�����룬���õ�������룬 ע�����ﵹ�����ģ����Բ�����������code =
					 * code + entry.code;
					 */
					code = entry.code + code;
					entry = entry.parent; // Ҫһֱѭ������
				} while (entry.parent != null);

				leafEntries[i].code = code;// �����������Ĺ������

			}
		}
	}

	// �õ������������
	public Map<String, String> getHuffmancodeTable() {

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < SIZE; i++) {
			Entry entry = leafEntries[i];
			if (entry.freq > 0) {// ���ʹ�ù����ַ�ʱ������������
				map.put(String.valueOf(entry.c), entry.code);
			}
		}

		return map;
	}

	// �õ��ַ�������Ӧ�Ĺ���������
	public String getHuffmancodes() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			Entry entry = leafEntries[input.charAt(i)];
			sb.append(entry.code);
		}
		return sb.toString();
	}

	// ��huffman��Ϣ����ԭ���ַ���
	public static String huffmancodesToString(Map<String, String> map,
			String huffmanCodes) {
		Entry root = createTreeFromCode(map);
		return encoding(root, huffmanCodes);
	}

	// ����ָ���Ĺ��������봴����������
	private static Entry createTreeFromCode(Map<String, String> map) {
		Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
		Map.Entry<String, String> mapEntry;
		Entry root = new Entry(), parent = root, tmp;

		while (itr.hasNext()) {
			mapEntry = itr.next();

			// �Ӹ���ʼ������
			for (int i = 0; i < mapEntry.getValue().length(); i++) {

				if (mapEntry.getValue().charAt(i) == '0') {
					tmp = parent.left;
					if (tmp == null) {
						tmp = new Entry();
						parent.left = tmp;
						tmp.parent = parent;
						tmp.code = "0";
					}
				} else {
					tmp = parent.right;
					if (tmp == null) {
						tmp = new Entry();
						parent.right = tmp;
						tmp.parent = parent;
						tmp.code = "1";
					}
				}

				if (i == mapEntry.getValue().length() - 1) {
					tmp.c = mapEntry.getKey().charAt(0);
					tmp.code = mapEntry.getValue();
					parent = root;
				} else {
					parent = tmp;
				}
			}

		}
		return root;
	}

	// ���ݸ����Ĺ��������������ַ�
	private static String encoding(Entry root, String huffmanCodes) {
		Entry tmp = root;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < huffmanCodes.length(); i++) {
			if (huffmanCodes.charAt(i) == '0') {
				tmp = tmp.left;// �ҵ��뵱ǰ�����Ӧ�Ľڵ�
				// �����������������Ϊ�գ���������Ҳ�϶�Ϊ�գ�Ҳ����˵����֧�ڵ�һ�����������ڵ�Ľڵ�
				if (tmp.left == null) {// ���ΪҶ�ӽڵ㣬���ҵ���������
					sb.append(tmp.c);
					tmp = root;// ׼���½�����һ���ַ�
				}
			} else {
				tmp = tmp.right;
				if (tmp.right == null) {
					sb.append(tmp.c);
					tmp = root;
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String inputStr = "3334444555556666667777777";
		Huffman hfm = new Huffman(inputStr);

		Map<String, String> map = hfm.getHuffmancodeTable();
		String huffmancodes = hfm.getHuffmancodes();
		System.out.println("�����ַ��� - " + inputStr);
		System.out.println("������������ձ� - " + map);
		System.out.println("���������� - " + huffmancodes);
		String encodeStr = Huffman.huffmancodesToString(map, huffmancodes);
		System.out.println("���������� - " + encodeStr);
		/*
		 * output: �����ַ��� - 3334444555556666667777777 ������������ձ� - {3=110, 5=00,
		 * 7=10, 4=111, 6=01} ���������� -
		 * 110110110111111111111000000000001010101010110101010101010 ���������� -
		 * 3334444555556666667777777
		 */
	}
}
