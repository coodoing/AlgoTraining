package Sorting.CommonSort;

public class BitmapSort {
	
	/** 
	 * 使用位图法进行排序
	 * 找到最小值和最大值，申请位图数组，对应位赋值为1，最后依次输出。
	 *  @param  arr
	 */
	public static void bitmapSort(int[] arr) {
		int max = arr[0];
		int min = max;
		for (int i : arr) {
			if (max < i) {
				max = i;
			}
			if (min > i) {
				min = i;
			}
		}
		System.out.println("最大最小元素分别为："+max + " " + min);

		//  得到位图数组 
		int[] bitArr = new int[max - min + 1];
		for (int t : arr) {
			int index = t - min;
			bitArr[index]++;
		}
		
		System.out.println("bitmap后的数组元素为：");
		for (int t : bitArr) {
			System.out.print(t+" ");
		}
		System.out.println();

		//  重整arr中的元素 
		int index = 0;
		for (int i = 0; i < bitArr.length; i++) {
			while (bitArr[i] > 0) {
				arr[index] = i + min;
				// 移到下一个元素
				index++;
				bitArr[i]--;
			}
		}
	}
	
	public static void test(int[] bitarr,int data)
	{		
		int bytepos = data/8;
		int bitpos = data&7;
		
		bitarr[bytepos] = bitarr[bytepos]|(1<<(7-bitpos));
		
		int i = bitarr[bytepos];		
		//byte[] b = toByteArray(i, 4);   //整型到字节
		
		System.out.println(Integer.toBinaryString(i));
	}	
	
	public static void main(String[] args) {
		int[] arr = { 1, 7, -3, 0, 0, 6, 6, 9, -11 };
		bitmapSort(arr);
		System.out.println("Bitmap排序后的结果：");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		//整型与byte类型之间的转换
		int[] bitarr = new int[4096];
		int data = 1234;
		test(bitarr,data);
		data = 1236;
		test(bitarr,data);		
	}	
	
	// 将iSource转为长度为iArrayLen的byte数组，字节数组的低位是整型的低字节位  
    public static byte[] toByteArray(int iSource, int iArrayLen) {  
        byte[] bLocalArr = new byte[iArrayLen];  
        for ( int i = 0; (i < 4) && (i < iArrayLen); i++) {  
            bLocalArr[i] = (byte)( iSource>>8*i & 0xFF );           
        }  
        
        return bLocalArr;  
    }     
    
    // 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位  
    public static int toInt(byte[] bRefArr) {  
        int iOutcome = 0;  
        byte bLoop;  
          
        for ( int i =0; i<4 ; i++) {  
            bLoop = bRefArr[i];  
            iOutcome+= (bLoop & 0xFF) << (8 * i);  
            
        }    
          
        return iOutcome;  
    }      
}