package BackTrack;

import java.util.Calendar;

// http://blog.csdn.net/hackbuteer1/article/details/6657109
public class NQueen_Fastest {

	public static int sum = 0, upperlimit = 1;

	//�Żʺ�ʱ,���ҵ���ݹ��(�ұ��ǵ�λ,����Ǹ�λ)
	/**
	 * ��������ÿһλ����һ��,bitΪ1��λ�ò��ܷ��ûʺ�(��������õĻʺ���45�Ƚǻ�ֱ�������г�ͻ)
	 * @param row λΪ1����˵������ĳһ���ڴ��м�����һ���ʺ�
	 * @param ld λΪ1��˵����Ӧ�����Ͻ��߼��лʺ�
	 * @param rd λΪ1��˵����Ӧ�����Ͻ��߼��лʺ�
	 */
	public static void compute(int row, int ld, int rd) {

		if (row != upperlimit) {
			
			// row��ld��rd���С������㣬������п��Է��ûʺ����,��ӦλΪ0��  
	        // Ȼ����ȡ�����롱��ȫ1����������õ�ǰ���п��Է��ûʺ��λ�ã���Ӧ�и�Ϊ1  
	        // Ҳ������ȡ��ǰ��Щ�п��Է��ûʺ�  
			
			int pos = upperlimit & ~(row | ld | rd);
			//�Ե�ǰ�����п��Է��ûʺ�ĵط�����һ���ʺ�,Ȼ�������һ��			
			while (pos != 0) {// 0 -- �ʺ�û�еط��ɷţ����� 
				
				// ����pos���ұ�Ϊ1��bit������bit��0  
	            // Ҳ����ȡ�ÿ��ԷŻʺ�����ұߵ��� 
				int p = pos & -pos; // �ȼ��� pos &��~pos +1��
				
				// ��pos���ұ�Ϊ1��bit����  
	            // Ҳ����Ϊ��ȡ��һ�ε����ҿ�����ʹ����׼����  
	            // ����������ݵ����λ�ü�����̽
				pos -= p;
				// row + p������ǰ����1����ʾ��¼��λʺ���õ��С�  
	            // (ld + p) << 1����ǵ�ǰ�ʺ�������ڵ��в�������һ���ʺ���á�  
	            // (ld + p) >> 1����ǵ�ǰ�ʺ��ұ����ڵ��в�������һ���ʺ���á�  
	            // �˴�����λ����ʵ�����Ǽ�¼�Խ����ϵ����ƣ�ֻ����Ϊ���ⶼ����  
	            // ��һ������������������Ա�ʾΪ�е����ƾͿ����ˡ���Ȼ��������λ  
	            // ��ÿ��ѡ����֮ǰ���У�ԭ��N��N������ĳ���ѷ��õĻʺ������Խ���  
	            // �ϲ��������ƶ�����¼������  
				compute(row + p, (ld + p) << 1, (rd + p) >> 1);
			}

		} else
			//row�����ж�Ϊ1,˵�����ɹ��õ�һ������
			sum++;
	}

	public static void main(String[] args) {
		Calendar start;
		int n = 12;
		if (args.length > 0)
			n = Integer.parseInt(args[0]);
		start = Calendar.getInstance();
		if ((n < 1) || (n > 32)) {
			System.out.println(" ֻ�ܼ���1-32֮��\n");
			return;
		}
		System.out.println(n + " �ʺ�\n");
		upperlimit = (upperlimit << n) - 1;
		compute(0, 0, 0);
		System.out.println("����"
				+ sum
				+ "������, ����ʱ��"
				+ (Calendar.getInstance().getTimeInMillis() - start
						.getTimeInMillis()) + "���� \n");
		
	}

}