package Searching.HashFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

// http://blog.csdn.net/jiaomeng/article/details/1495500
// http://www.cnblogs.com/heaad/archive/2011/01/02/1924195.html

public class HashFunc {

	static int MAX_NUM = 1000000;
	static int ARRAY_NUM = 100000;

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		HTable hashtable = new HTable(MAX_NUM);

		int[] result = new int[9];
		int[] sum = new int[] { 231552, 234756, 596873, 648219, 726312, 981237,
				988331, 1277361, 1283379 };

		try {
			File file = new File("E:/PRO/Java/Data/HashInt.txt");
			//File file = new File("E:/PRO/Java/Data/HashIntTest.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				hashtable.insert(Integer.parseInt(line));
				list.add(line);
			}
			br.close();
			fr.close();
		} catch (Exception ex) {
		}

		int[] array0 = hashtable.getHashTable();
		System.out.println(array0.length);
		System.out.println(array0[203292]);

		int[] array = new int[ARRAY_NUM];
		for (int i = 0; i < ARRAY_NUM; i++) {
			array[i] = Integer.parseInt(list.get(i));
		}
		/*for (int i = 0; i < list.size(); i++) {
			System.out.print(array[i] + "   ");
		}	*/
		/*for (int i = 0; i < 9; i++) {
			System.out.print(sum[i] + "   ");
		}*/
		for (int m = 0; m < sum.length; m++) {
			for (int n = 0; n < list.size(); n++) {
				int x = sum[m] - array[n];
				if (x > 0 && x <= MAX_NUM) {
					//if(m==6)
					//System.out.print("");
					//continue;
					if (hashtable.find(x)) {
						result[m] = 1;
						break;
					}

				}
			}
			System.out.print(result[m]);
		}

	}
}
