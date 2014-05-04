package WrittenTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisjointSetProblem {

	/** 
	题目：给定一个字符串的集合，格式如：{aaa bbb ccc}， {bbb ddd}，{eee fff}，{ggg}，{ddd hhh}要求将其中交集不为空的集合合并，要求合并完成后的集合之间无交集，例如上例应输出{aaa bbb ccc ddd hhh}，{eee fff}， {ggg}。 
	（1）请描述你解决这个问题的思路； 
	（2）请给出主要的处理流程，算法，以及算法的复杂度 
	（3）请描述可能的改进。 
	解答： 
	1. 假定每个集合编号为0，1，2，3... 
	2. 创建一个hash_map，key为字符串，value为一个链表，链表节点为字符串所在集合的编号。遍历所有的集合，将字符串和对应的集合编号插入到hash_map中去。 
	3. 创建一个长度等于集合个数的int数组，表示集合间的合并关系。例如，下标为5的元素值为3，表示将下标为5的集合合并到下标为3的集合中去。开始时将所有值都初始化为-1，表示集合间没有互相合并。在集合合并的过程中，我们将所有的字符串都合并到编号较小的集合中去。 
	遍历第二步中生成的hash_map，对于每个value中的链表，首先找到最小的集合编号（有些集合已经被合并过，需要顺着合并关系数组找到合并后的集合编号），然后将链表中所有编号的集合都合并到编号最小的集合中（通过更改合并关系数组）。 
	4.现在合并关系数组中值为-1的集合即为最终的集合，它的元素来源于所有直接或间接指向它的集合。 
	0: {aaa bbb ccc} 
	1: {bbb ddd} 
	2: {eee fff} 
	3: {ggg} 
	4: {ddd hhh} 
	生成的hash_map，和处理完每个值后的合并关系数组分别为 
	aaa: 0          [-1, -1, -1, -1, -1] 
	bbb: 0, 1       [-1, 0, -1, -1, -1] 
	ccc: 0          [-1, 0, -1, -1, -1] 
	ddd: 1, 4       [-1, 0, -1, -1, 0] 
	eee: 2          [-1, 0, -1, -1, 0] 
	fff: 2          [-1, 0, -1, -1, 0] 
	ggg: 3          [-1, 0, -1, -1, 0] 
	hhh: 4          [-1, 0, -1, -1, 0] 
	所以合并完后有三个集合，第0，1，4个集合合并到了一起， 
	第2，3个集合没有进行合并。 
	Use "Disjoin-set".But I use "HashSet" and "HashMap" of Java API.Does "Disjoin-set" have its own data structure? 
	see also [url]http://www.csie.ntnu.edu.tw/~u91029/DisjointSets.html[/url] 
	 */
	private final int SIZE = 7;
	private int[] father;//the root in disjion set.  
	private static List<Set<String>> resultList = new ArrayList<Set<String>>();

	public static void main(String[] args) {
		String[] str0 = { "aaa", "bbb", "ccc", };
		String[] str1 = { "bbb", "ddd", };
		String[] str2 = { "eee", "fff", };
		String[] str3 = { "ggg", };
		String[] str4 = { "ddd", "hhh", };
		String[] str5 = { "xx", "yy", };
		String[] str6 = { "zz", "yy", };
		String[][] strs = { str0, str1, str2, str3, str4, str5, str6 };
		//change String[][] to List<Set>  
		for (String[] str : strs) {
			//when I write--"Arraylist list=Arrays.asList(strArray)","addAll()" is unsupported for such a arraylist.  
			Set<String> set = new HashSet<String>();
			set.addAll(Arrays.asList(str));
			resultList.add(set);
		}
		DisjointSetProblem disjointSet = new DisjointSetProblem();
		disjointSet.disjoin(strs);
	}

	/*
	 * 获取hashmap过程
	 * */
	public void disjoin(String[][] strings) {
		if (strings == null || strings.length < 2)
			return;
		initial();
		// 获得hash_map：key为字符串，value为一个链表
		Map<String, List<Integer>> map = storeInHashMap(strings);
		// 并查集进行合并
		union(map);
	}

	//in the beginning,each element is in its own "group".  
	public void initial() {
		father = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			father[i] = i;
		}
	}

	/* Map<k,v> 
	 * key:String 
	 * value:List<Integer>-in which sets the string shows up. 
	 */
	public Map<String, List<Integer>> storeInHashMap(String[][] strings) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (int i = 0; i < SIZE; i++) {
			for (String each : strings[i]) {
				if (!map.containsKey(each)) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(i);
					map.put(each, list);
				} else {
					map.get(each).add(i);
				}
			}
		}

		// 打印出map
		System.out.println("集合映射所生成的hashmap为：");
		printMap(map);
		return map;
	}

	private void printMap(Map<String, List<Integer>> map) {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<String, List<Integer>>> iter = map.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, List<Integer>> entry = iter.next();
			String key = entry.getKey();
			List<Integer> value = entry.getValue();
			System.out.println(key + ":" + value);
		}
		System.out.println();
	}

	/*
	 * 对hashmap进行并查集合并操作
	 * */
	public void union(Map<String, List<Integer>> map) {
		Iterator<Map.Entry<String, List<Integer>>> it = map.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<Integer>> entry = it.next();
			List<Integer> value = entry.getValue();
			unionHelp(value);//the arrays whose indexes are in the same list should be merged to one set. 			
		}
		// 打印出father父节点信息
		System.out.println("hashmap集合合并之后的父节点信息为：");
		printFather(father);//System.out.println("the father array is " + Arrays.toString(father));
		printSetList(resultList);
		//merge two sets  
		for (int i = 0; i < SIZE; i++) {
			if (i != father[i]) {
				// set：无重复元素
				Set<String> dest = resultList.get(father[i]);
				Set<String> source = resultList.get(i);
				dest.addAll(source);
			}
		}
		//clear a set which has been added.  
		// 当B集合添加到A集合后，清空B集合
		for (int i = 0; i < SIZE; i++) {
			if (i != father[i]) {
				resultList.get(i).clear();
			}
		}
		System.out.println("合并后:" + resultList);
	}

	public void unionHelp(List<Integer> list) {
		int minFather = getFather(list.get(0));//list[0] is the smaller.  
		// 传过来的list参数已经排好序
		for (int i = 0, size = list.size(); i < size; i++) {
			father[list.get(i)] = minFather;
			//unionHelp(list.get(0),list.get(i));
		}
		
		/*int minFather = getFather(list.get(0));//list[0] is the smaller.  
		// 传过来的list参数已经排好序
		for (int i = 0, size = list.size(); i < size; i++) {
			//father[list.get(i)] = minFather;
			unionHelp(list.get(0),list.get(i));
		}*/
	}

	// 路径压缩
	public int getFather(int x) {
		while (x != father[x]) {
			x = father[x];
		}
		return x;
	}	

	private void printFather(int[] fatherNode) {
		// TODO Auto-generated method stub
		for (int node : fatherNode)
			System.out.print(node + " ");
		System.out.println();
	}

	private void printSetList(List<Set<String>> list) {
		// TODO Auto-generated method stub
		System.out.print("合并前:");
		for (int i = 0; i < SIZE; i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

	//general union in disjoin set.But we overload it in this case.  
	public void unionHelp(int x, int y) {
		if (father[x] != father[y]) {
			int fx = getFather(x);
			int fy = getFather(y);
			//merge two arrays to the array that has a smaller index.  
			if (fx < fy) {
				father[y] = fx;
			} else {
				father[x] = fy;
			}
		}
	}
	
}
