package Tree.HuffmanHeapDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Tree.HuffmanHeapDemo.Heap.Heap;


/**
 * 哈夫曼树与哈夫曼编解码
 * 
 * @author jzj
 * @data 2010-1-8
 */
public class Huffman {

	// 哈夫曼树节点
	private static class Entry implements Comparable<Entry> {

		int freq;// 节点使用频率，优先级就是根据此决定
		String code;// 节点huffman编码
		char c;// 节点所对应的字符
		Entry left, right, parent;// 哈夫树遍历相关字段

		// 节点的优先级比较
		public int compareTo(Entry entry) {
			return freq - entry.freq;
		}

		public String toString() {
			return "(" + c + ":" + code + ")";
		}
	}

	// 这里我们仅只对Unicodeue前256个字符编码，所以只能输入ISO8859-1字符串
	protected final int SIZE = 256;

	// 哈夫编码表，用于快速查询某字符的哈夫编码
	protected Entry[] leafEntries;

	// 堆，用来动态进行优先级排序
	protected Heap<Entry> pq;

	// 要编码的输入串
	protected String input;

	public Huffman(String input) {
		this.input = input;
		createPQ();
		createHuffmanTree();
		calculateHuffmanCodes();
	}

	// 创建初始堆
	public void createPQ() {

		// 初始化哈夫编码表
		Entry entry;
		leafEntries = new Entry[SIZE];
		for (int i = 0; i < SIZE; i++) {
			leafEntries[i] = new Entry();
			leafEntries[i].freq = 0;// 使用频率
			/*
			 * leafEntries哈夫编码表中的索引与字符的编码对应，这样在读取时 很方便
			 */

			leafEntries[i].c = (char) i;// 节点点是对应的字符

		}

		// 填充哈夫编码表
		fillLeafEntries();

		// 开始创建初始堆
		pq = new Heap<Entry>();
		for (int i = 0; i < SIZE; i++) {
			entry = leafEntries[i];
			if (entry.freq > 0) {// 如果被使用过，则放入堆中
				pq.add(entry);
			}
		}
	}

	// 根据输入的字符串填充leafEntries哈夫编码表
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

	// 创建哈夫曼树
	public void createHuffmanTree() {

		Entry left, right, parent;

		// 每次需从堆中取两个，所以需大于1，如果小于等于1时表示哈夫曼树已创建完毕
		while (pq.size() > 1) {

			// 使用贪婪法，每次从优先级队列中读取最小的两个元素
			left = (Entry) pq.removeMin();
			left.code = "0";// 如果做为左子节点，则为路径编码为0

			right = (Entry) pq.removeMin();
			right.code = "1";// 如果做为右子节点，则为路径编码为1

			parent = new Entry();
			parent.parent = null;

			// 父节点的使用频度为两者之和
			parent.freq = left.freq + right.freq;
			parent.left = left;
			parent.right = right;
			left.parent = parent;
			right.parent = parent;

			// 再把父节点放入堆中，将会进行重组堆结构
			pq.add(parent);
		}
	}

	// 计算输入串的每个字符的哈夫编码
	public void calculateHuffmanCodes() {

		String code;
		Entry entry;

		for (int i = 0; i < SIZE; i++) {

			code = "";
			entry = leafEntries[i];
			if (entry.freq > 0) {// 如果使用过该字符时就需要求哈夫编码

				do {
					/*
					 * 拼接从叶节点到根节点路径上各元素的路径编码，最后得到哈夫编码， 注，这里倒着来的，所以不能有这样：code =
					 * code + entry.code;
					 */
					code = entry.code + code;
					entry = entry.parent; // 要一直循环到根
				} while (entry.parent != null);

				leafEntries[i].code = code;// 设置最后真真的哈夫编码

			}
		}
	}

	// 得到哈夫曼编码表
	public Map<String, String> getHuffmancodeTable() {

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < SIZE; i++) {
			Entry entry = leafEntries[i];
			if (entry.freq > 0) {// 如果使用过该字符时就需求哈夫编码
				map.put(String.valueOf(entry.c), entry.code);
			}
		}

		return map;
	}

	// 得到字符串所对应的哈夫曼编码
	public String getHuffmancodes() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			Entry entry = leafEntries[input.charAt(i)];
			sb.append(entry.code);
		}
		return sb.toString();
	}

	// 将huffman消息串还原成字符串
	public static String huffmancodesToString(Map<String, String> map,
			String huffmanCodes) {
		Entry root = createTreeFromCode(map);
		return encoding(root, huffmanCodes);
	}

	// 根据指定的哈夫曼编码创建哈夫曼树
	private static Entry createTreeFromCode(Map<String, String> map) {
		Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
		Map.Entry<String, String> mapEntry;
		Entry root = new Entry(), parent = root, tmp;

		while (itr.hasNext()) {
			mapEntry = itr.next();

			// 从根开始创建树
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

	// 根据给定的哈夫曼编码解码成字符
	private static String encoding(Entry root, String huffmanCodes) {
		Entry tmp = root;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < huffmanCodes.length(); i++) {
			if (huffmanCodes.charAt(i) == '0') {
				tmp = tmp.left;// 找到与当前编码对应的节点
				// 如果哈夫曼树左子树为空，则右子树也肯定为空，也就是说，分支节点一定是用两个节点的节点
				if (tmp.left == null) {// 如果为叶子节点，则找到完整编码
					sb.append(tmp.c);
					tmp = root;// 准备下解码下一个字符
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
		System.out.println("输入字符串 - " + inputStr);
		System.out.println("哈夫曼编码对照表 - " + map);
		System.out.println("哈夫曼编码 - " + huffmancodes);
		String encodeStr = Huffman.huffmancodesToString(map, huffmancodes);
		System.out.println("哈夫曼解码 - " + encodeStr);
		/*
		 * output: 输入字符串 - 3334444555556666667777777 哈夫曼编码对照表 - {3=110, 5=00,
		 * 7=10, 4=111, 6=01} 哈夫曼编码 -
		 * 110110110111111111111000000000001010101010110101010101010 哈夫曼解码 -
		 * 3334444555556666667777777
		 */
	}
}
