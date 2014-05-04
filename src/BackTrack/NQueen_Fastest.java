package BackTrack;

import java.util.Calendar;

// http://blog.csdn.net/hackbuteer1/article/details/6657109
public class NQueen_Fastest {

	public static int sum = 0, upperlimit = 1;

	//放皇后时,从右到左递归放(右边是低位,左边是高位)
	/**
	 * 三个参数每一位代表一列,bit为1的位置不能放置皇后(与上面放置的皇后在45度角或垂直方向上有冲突)
	 * @param row 位为1的列说明上面某一行在此列己放置一个皇后
	 * @param ld 位为1的说明对应的左上角线己有皇后
	 * @param rd 位为1的说明对应的右上角线己有皇后
	 */
	public static void compute(int row, int ld, int rd) {

		if (row != upperlimit) {
			
			// row，ld，rd进行“或”运算，求得所有可以放置皇后的列,对应位为0，  
	        // 然后再取反后“与”上全1的数，来求得当前所有可以放置皇后的位置，对应列改为1  
	        // 也就是求取当前哪些列可以放置皇后  
			
			int pos = upperlimit & ~(row | ld | rd);
			//对当前行所有可以放置皇后的地方放置一个皇后,然后进入下一行			
			while (pos != 0) {// 0 -- 皇后没有地方可放，回溯 
				
				// 拷贝pos最右边为1的bit，其余bit置0  
	            // 也就是取得可以放皇后的最右边的列 
				int p = pos & -pos; // 等价于 pos &（~pos +1）
				
				// 将pos最右边为1的bit清零  
	            // 也就是为获取下一次的最右可用列使用做准备，  
	            // 程序将来会回溯到这个位置继续试探
				pos -= p;
				// row + p，将当前列置1，表示记录这次皇后放置的列。  
	            // (ld + p) << 1，标记当前皇后左边相邻的列不允许下一个皇后放置。  
	            // (ld + p) >> 1，标记当前皇后右边相邻的列不允许下一个皇后放置。  
	            // 此处的移位操作实际上是记录对角线上的限制，只是因为问题都化归  
	            // 到一行网格上来解决，所以表示为列的限制就可以了。显然，随着移位  
	            // 在每次选择列之前进行，原来N×N网格中某个已放置的皇后针对其对角线  
	            // 上产生的限制都被记录下来了  
				compute(row + p, (ld + p) << 1, (rd + p) >> 1);
			}

		} else
			//row所有列都为1,说明己成功得到一个方案
			sum++;
	}

	public static void main(String[] args) {
		Calendar start;
		int n = 12;
		if (args.length > 0)
			n = Integer.parseInt(args[0]);
		start = Calendar.getInstance();
		if ((n < 1) || (n > 32)) {
			System.out.println(" 只能计算1-32之间\n");
			return;
		}
		System.out.println(n + " 皇后\n");
		upperlimit = (upperlimit << n) - 1;
		compute(0, 0, 0);
		System.out.println("共有"
				+ sum
				+ "种排列, 计算时间"
				+ (Calendar.getInstance().getTimeInMillis() - start
						.getTimeInMillis()) + "毫秒 \n");
		
	}

}