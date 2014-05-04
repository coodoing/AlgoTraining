package Tree;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

//http://algs4.cs.princeton.edu/55compression/Huffman.java.html
class HuffmanNode {
	char label;
	int weight;
	int parent;
	int lChild;
	int rChild;
	int frequency;//Ƶ����Ҫ�����������ַ��ڸ��������ַ����г��ֵĴ���

	public HuffmanNode(char label, int weight, int parent, int lChild,
			int rChild) {
		this.label = label;
		this.weight = weight;
		this.lChild = lChild;
		this.rChild = rChild;
	}
}

class HuffmanTree {
	private LinkedHashMap<Character, Integer> charTable; //��Ҫ��hashmap������ַ�������ֵ�Ƶ��
	private Set<Character> charset;
	private ArrayList<HuffmanNode> huffmanTree;//huffman�ڵ㼯��
	private ArrayList<String> huffmanCode;//huffman���뼯��

	public HuffmanTree(LinkedHashMap<Character, Integer> map) {
		charTable = map;
		charset = map.keySet();
		creatHuffmanTree();//���ȴ���huffman��
		creatHuffmanCode();
	}

	private void initTree() {
		huffmanTree = new ArrayList<HuffmanNode>();
		Iterator<Character> charIter = charset.iterator();
		int i = 1;
		huffmanTree.add(0,
				new HuffmanNode((char) 0, Integer.MAX_VALUE, 0, 0, 0));
		while (charIter.hasNext()) {
			Character ch = charIter.next();
			huffmanTree.add(i, new HuffmanNode(ch, charTable.get(ch), 0, 0, 0));
			i++;
		}
		for (int j = charset.size() + 1; j < 2 * charset.size(); j++) {
			huffmanTree.add(j, new HuffmanNode((char) 0, 0, 0, 0, 0));
		}
	}

	// ����huffman��
	private void creatHuffmanTree() {
		initTree();
		int min_child1;
		int min_child2;
		for (int i = charset.size() + 1; i < 2 * charset.size(); i++) {
			// ��huffaman����ѡ��parentΪ0��weight��С�������ڵ㣺��ŷֱ�Ϊ��min_child1��min_child2
			min_child1 = 0;
			min_child2 = 0;

			for (int j = 1; j < i; j++) {
				if (huffmanTree.get(j).parent == 0) {
					if (huffmanTree.get(j).weight < huffmanTree.get(min_child1).weight
							|| huffmanTree.get(j).weight < huffmanTree
									.get(min_child2).weight) {
						if (huffmanTree.get(min_child1).weight < huffmanTree
								.get(min_child2).weight) {
							min_child2 = j;
						} else {
							min_child1 = j;
						}
					}
				}
			}// end for loop
			huffmanTree.get(min_child1).parent = i;
			huffmanTree.get(min_child2).parent = i;

			if (min_child1 < min_child2) {
				huffmanTree.get(i).lChild = min_child1;
				huffmanTree.get(i).rChild = min_child2;
			} else {
				huffmanTree.get(i).rChild = min_child1;
				huffmanTree.get(i).lChild = min_child2;
			}

			huffmanTree.get(i).weight = huffmanTree.get(i).weight
					+ huffmanTree.get(i).weight;
		}
	}

	private void creatHuffmanCode() {
		huffmanCode = new ArrayList<String>(charset.size() + 1);
		huffmanCode.add(0, null);
		char[] tempChars = new char[charset.size() + 1];
		for (int i = 1; i < charset.size() + 1; i++) {
			int startIndex = charset.size();
			int parent = huffmanTree.get(i).parent;
			int ch = i;
			while (parent != 0) {
				if (huffmanTree.get(parent).lChild == ch) {
					tempChars[startIndex] = '0';
				} else {
					tempChars[startIndex] = '1';
				}
				startIndex--;
				ch = parent;
				parent = huffmanTree.get(parent).parent;
			}
			System.out.println(String.valueOf(tempChars, startIndex + 1,
					charset.size() - startIndex));
			huffmanCode.add(i, String.valueOf(tempChars, startIndex + 1,
					charset.size() - startIndex));
		}
	}// end method

	// huffman����
	public String enCodeString(String inString) {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			int ch = inString.charAt(i);
			int j = 1;
			for (; huffmanTree.get(j).label != ch && j < charset.size() + 1; j++) {
			}
			if (j <= charset.size()) {
				temp.append(huffmanCode.get(j));
			} else {
				temp.append(ch);
			}
		}
		return temp.toString();
	}

	// huffman����
	public String deCodeString(String inString) {
		StringBuffer temp = new StringBuffer();
		int root = charset.size() * 2 - 1;
		for (int i = 0; i < inString.length(); i++) {
			char ch = inString.charAt(i);
			if (ch == '0') {
				root = huffmanTree.get(root).lChild;
			} else if (ch == '1') {
				root = huffmanTree.get(root).rChild;
			} else {
				temp.append(ch);
			}
			if (root <= charset.size()) {
				temp.append(huffmanTree.get(root).label);
				root = charset.size() * 2 - 1;
			}
		}
		return temp.toString();
	}

}

public class HuffmanTreeTest {
	public static void main(String[] args) {
		LinkedHashMap<Character, Integer> hasmap = new LinkedHashMap<Character, Integer>();
		hasmap.put('a', 4);
		hasmap.put('b', 5);
		hasmap.put('c', 8);
		hasmap.put('d', 10);

		HuffmanTree huffman = new HuffmanTree(hasmap);
		String temp = huffman.enCodeString("abcd");
		System.out.println(temp);
		System.out.println(huffman.deCodeString(temp));

	}

}