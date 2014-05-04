package WrittenTest.BigIntProblem;

public class BIMPlus {

	static void plus(String input1, String input2) {
		char[] input11 = input1.toCharArray();
		char[] input21 = input2.toCharArray();

		int len1 = input11.length, len2 = input21.length;

		int len = len1 > len2 ? len1 : len2;
		int[] result = new int[len + 1]; // 结果数组

		int[] number1 = new int[len];
		int[] number2 = new int[len];

		// 数据反转 因为下标的因素
		for (int i = 0; i < len; i++) {
			// input1长,input2补位0
			if (len == len1) {
				number1[i] = input11[len - i - 1] - '0';
				if (i < len2) {
					number2[i] = input21[len2 - i - 1] - '0';
				} else
					number2[i] = 0;
			} else {
				number2[i] = input21[len - i - 1] - '0';
				if (i < len1) {
					number1[i] = input11[len1 - i - 1] - '0';
				} else
					number1[i] = 0;
			}
		}

		//print(number1);
		//print(number2);

		int count = 0;

		for (int i = 0; i < len; i++) {
			result[i] += number1[i] + number2[i];
			if (result[i] > 10) {
				result[i + 1] = result[i] / 10; // 进位
				result[i] = result[i] % 10;
			}

		}

		if (result[len] != 0)
			count = len;
		else
			count = len - 1;

		for (int i = count; i >= 0; i--) {
			System.out.print(result[i]);
		}
		System.out.println();
	}

	static void print(int[] number) {
		for (int i = number.length - 1; i >= 0; i--) {
			System.out.print(number[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("测试数据");
		String input11 = "1234332234456";
		String input12 = "12352153262131236";

		String input21 = "120";
		String input22 = "9";

		String input31 = "123";
		String input32 = "18";
		
		String input41 = "100";
		String input42 = "200";

		plus(input11, input12);
		plus(input21, input22);
		plus(input31, input32);
		plus(input41, input42);
	}

}
