package WrittenTest;

public class StringBufferTest {

	/**
	 * http://plum.0602.blog.163.com/blog/static/113000650201281914940149/
	 * java �д�ֵ
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		comp(a, b);

		System.out.println(a + "--" + b);

		String s00 = "kvill";
		String s10 = "kvill";
		String s20 = "kv" + "ill";
		System.out.println(s00 == s10);
		System.out.println(s00 == s20);
		System.out.println("********");
		
		String s0 = "kvill";
		String s1 = new String("kvill");
		String s2 = new String("kvill");
		System.out.println(s0 == s1);
		System.out.println(s1 == s2);
		System.out.println("&&&");
		s1.intern(); //s1������û�з����仯
		s2 = s2.intern(); //�ѳ�������"kvill"�����ø���s2
		System.out.println(s0 == s1);
		System.out.println(s0 == s1.intern());
		System.out.println(s0 == s2);
		System.out.println("**********");

		String str = "abc";
		String str1 = "abc";
		String str2 = new String("abc");
		System.out.println(str == str1);
		System.out.println(str1 == "abc");
		System.out.println(str2 == "abc");
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));  
		System.out.println(str1 == str2.intern());
		System.out.println(str2 == str2.intern());
		System.out.println(str1.hashCode() == str2.hashCode());

	}

	private static void comp(StringBuffer a, StringBuffer b) {
		// TODO Auto-generated method stub
		a.append(b); 
		System.out.println(a + "--" + b);
		b = a;
		System.out.println(a + "--" + b);

		//׷�Ӽ��д���
		b.append("��Ϊs2������s1ָ��Ķ���s1��b1���õ���ͬһ������");
		b.append("���Ըı�s2���õĶ�������ݣ�b1�ܷ�ӳ");
		a = new StringBuffer("s1��ָ��ı��ˣ�ָ������new������StringBuffer��");
		a.append("s1ָ��Ķ�������ݸı��ˣ����Ըı�s1�����ݣ�������Ӱ��b1");
	}

}
