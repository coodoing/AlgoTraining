package Greedy;

import java.util.Arrays;

@SuppressWarnings("unchecked")
// ��װ����
class LContainer implements Comparable {
	private int id; //���  
	private double weight; //����  
	private boolean loadFlag = false; //�Ƿ�װ�뼯װ���ʶ  

	public LContainer(int id,double weight) {
		this.id = id;
		this.weight = weight;
	}

	/**
	* ��ΪҪ�Զ��������������Ҫʵ��java.util.Comparator�ӿڵ�compareTo(T obj)�������ڸ÷������Զ��������㷨��
	*/
	public int compareTo(Object obj) {
		double ww = ((LContainer) obj).getWeight();
		if (this.weight > ww) {
			// ���������� 1 ʱ ���Ѳ���������ǰ�š�(ǰo.weight , ��this.weight) 
			return 1;
		} else if (this.weight < ww) {
			// ���������� -1 ʱ ���Ѳ�����������š�(ǰthis.weight , ��o.weight) 
			return -1;
		} else {
			return 0;
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}

	public boolean getLoadFlag() {
		return loadFlag;
	}
}

public class OptLoading {
	static int count;
	static LContainer[] container ;

	static int loading(double[] weight, double c) {
		container = new LContainer[weight.length] ;
		for(int i=0;i<weight.length;i++)
		{
			container[i] = new LContainer(i,weight[i]);
		}
		
		Arrays.sort(container); // ʵ����������
		
        for (int i = 0; i < container.length&&container[i].getWeight()<=c; i++)  
        {  
        	container[i].setLoadFlag(true);  
            count++;   
            c -= container[i].getWeight();  
        }  
		return count;
	}

	public static void main(String[] args) {
		double c = 1000;
		double[] weight = { 800, 1000, 1001, 200, 100, 400, 600, 50 };
		int result = loading(weight,c);
		System.out.println("����װ�ؼ�װ���������\n"+result);
		
		System.out.println("����װ�ص�˳�������£�");
		for (int i = 0; i < container.length; i++)  
        {  
            if (container[i].getLoadFlag())  
            {  
                System.out.print(container[i].getId() + " ");  
            }  
        }  
	}

}
