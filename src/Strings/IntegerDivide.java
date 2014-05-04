package Strings;

// http://www.cnblogs.com/hoodlum1980/archive/2008/10/11/1308493.html
// 生成函数方法
public class IntegerDivide {

	public static void main(String[] args){
        // 表示划分的个数
        int num = 0;
        //比如求6的划分的个数
        num = divide(6, 6);
        System.out.println(num);
    }
 
    public static int divide(int n, int m){
        if (n < 1 || m < 1) {
            return 0;
        } else if (n == 1 || m == 1) {
            return 1;
        } else if (n == m) {
            return 1 + divide(n, n - 1);
        } else if (n < m) {
            return divide(n, n);
        } else {
            return divide(n, m - 1) + divide(n - m, m);
        }
    }

}