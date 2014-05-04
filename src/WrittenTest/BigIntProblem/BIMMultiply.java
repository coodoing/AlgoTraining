package WrittenTest.BigIntProblem;

// 大整数相乘问题
// http://blog.csdn.net/hackbuteer1/article/details/6532490
// 快速傅里叶变换：http://www.cnblogs.com/skyivben/archive/2008/07/23/1248413.html
public class BIMMultiply {

	static void multiply(String input1, String input2) {
		char[] input11 = input1.toCharArray();
		char[] input21 = input2.toCharArray();

		int len1 = input11.length, len2 = input21.length;

		int[] number1 = new int[len1];
		int[] number2 = new int[len2];

		// 数据反转 因为下标的因素
		for (int i = 0; i < len1; i++) {
			number1[i] = input11[len1 - i - 1] - '0';
		}

		for (int i = 0; i < len2; i++) {
			number2[i] = input21[len2 - i - 1] - '0';
		}

		//print(number1);
		//print(number2);

		int[] result = new int[len1 + len2]; // 结果数组
		int count = 0;

		for (int i = 0; i < len1; i++)
			for (int j = 0; j < len2; j++) {
				result[i + j] += number1[i] * number2[j];
				if (result[i + j] > 10) {
					result[i + j + 1] = result[i + j] / 10; // 进位
					result[i + j] = result[i + j] % 10;
				}
			}

		if (result[len2 + len1-1] != 0)
			count = len2 + len1 - 1;
		else
			count = len2 + len1 - 2;

		for (int i = count;i>=0; i--) {
			System.out.print(result[i]);
		}
		System.out.println();
	}

	static void print(int[] number) {
		for (int i = number.length-1;i>=0; i--) {
			System.out.print(number[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 1;
		int j = i++;
		System.out.println(i + " " + j);
		// System.out.println(i > j++);
		if ((i > j++) && (i++ == j))
			i += j;
		System.out.println(i + " " + j);
		System.out.println((int) 'a');
		System.out.println('0' + 1);
		
		System.out.println("测试数据");
		String input11 = "1234332234456";
		String input12 = "12352153262131236";

		String input21 = "120";
		String input22 = "9";

		String input31 = "123";
		String input32 = "2";

		String input41 = "100";
		String input42 = "20000000";
		
		multiply(input11,input12);
		multiply(input21, input22);
		multiply(input31, input32);
		multiply(input41, input42);
	}
}

/*
 * final static long mask = (1 << 31) - 1;
 * 
 * static ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer>
 * b) { ArrayList<Integer> result = new ArrayList<Integer>(a.size() * b.size() +
 * 1); for (int i = 0; i < a.size(); i++) { multiply(b, a.get(i), i, result); }
 * return result; }
 * 
 * static void multiply(ArrayList<Integer> x, int a, int base, ArrayList<Integer>
 * result) { if (a == 0) return; long overflow = 0; int i; for (i = 0; i <
 * x.size(); i++) { long tmp = x.get(i) * a + result.get(base + i) + overflow;
 * result.set(base + i, (int) (mask & tmp)); overflow = (tmp >> 31); } while
 * (overflow != 0) { long tmp = result.get(base + i) + overflow; result.set(base +
 * i, (int) (mask & tmp)); overflow = (tmp >> 31); } }
 * 
 * 
 * ArrayList<Integer> a = new ArrayList<Integer>(); int[] a1=new
 * int[]{1,2,3,4}; for(int ele:a1) a.add(ele); ArrayList<Integer> b = new
 * ArrayList<Integer>(); int[] a2=new int[]{1,2,3,4}; for(int ele:a2)
 * b.add(ele); System.out.println(a.size()+","+b.size()); ArrayList<Integer>
 * result = multiply(a,b); for(i=0;i<result.size();i++)
 * System.out.println(result.get(i));
 * 
 * 
 */
