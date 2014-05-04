package WrittenTest;

public class SwitchCaseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 0, i;
		for (i = 1; i < 5; i++) {
			switch(i)
			{
			case 0:
			case 3: //执行3的时候需注意:后面的语句都需要执行
				a +=2;
				System.out.println("3");
			case 1:
			case 2:
				a+=3;
				System.out.println("1,2");
			default:
				a+=5;
				System.out.println("default");
			}			
		}
		System.out.println(a);
		
	}

}
