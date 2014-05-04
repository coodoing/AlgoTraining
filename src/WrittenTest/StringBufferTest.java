package WrittenTest;

public class StringBufferTest {

	/**
	 * http://plum.0602.blog.163.com/blog/static/113000650201281914940149/
	 * java 中传值
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
		s1.intern(); //s1的引用没有发生变化
		s2 = s2.intern(); //把常量池中"kvill"的引用赋给s2
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

		//追加几行代码
		b.append("因为s2引用了s1指向的对象，s1和b1引用的是同一个对象，");
		b.append("所以改变s2引用的对象的内容，b1能反映");
		a = new StringBuffer("s1的指向改变了，指向了新new出来的StringBuffer，");
		a.append("s1指向的对象的内容改变了，所以改变s1的内容，不会再影响b1");
	}

}
