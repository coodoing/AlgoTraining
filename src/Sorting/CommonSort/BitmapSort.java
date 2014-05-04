package Sorting.CommonSort;

public class BitmapSort {
	
	/** 
	 * ʹ��λͼ����������
	 * �ҵ���Сֵ�����ֵ������λͼ���飬��Ӧλ��ֵΪ1��������������
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
		System.out.println("�����СԪ�طֱ�Ϊ��"+max + " " + min);

		//  �õ�λͼ���� 
		int[] bitArr = new int[max - min + 1];
		for (int t : arr) {
			int index = t - min;
			bitArr[index]++;
		}
		
		System.out.println("bitmap�������Ԫ��Ϊ��");
		for (int t : bitArr) {
			System.out.print(t+" ");
		}
		System.out.println();

		//  ����arr�е�Ԫ�� 
		int index = 0;
		for (int i = 0; i < bitArr.length; i++) {
			while (bitArr[i] > 0) {
				arr[index] = i + min;
				// �Ƶ���һ��Ԫ��
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
		//byte[] b = toByteArray(i, 4);   //���͵��ֽ�
		
		System.out.println(Integer.toBinaryString(i));
	}	
	
	public static void main(String[] args) {
		int[] arr = { 1, 7, -3, 0, 0, 6, 6, 9, -11 };
		bitmapSort(arr);
		System.out.println("Bitmap�����Ľ����");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		//������byte����֮���ת��
		int[] bitarr = new int[4096];
		int data = 1234;
		test(bitarr,data);
		data = 1236;
		test(bitarr,data);		
	}	
	
	// ��iSourceתΪ����ΪiArrayLen��byte���飬�ֽ�����ĵ�λ�����͵ĵ��ֽ�λ  
    public static byte[] toByteArray(int iSource, int iArrayLen) {  
        byte[] bLocalArr = new byte[iArrayLen];  
        for ( int i = 0; (i < 4) && (i < iArrayLen); i++) {  
            bLocalArr[i] = (byte)( iSource>>8*i & 0xFF );           
        }  
        
        return bLocalArr;  
    }     
    
    // ��byte����bRefArrתΪһ������,�ֽ�����ĵ�λ�����͵ĵ��ֽ�λ  
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