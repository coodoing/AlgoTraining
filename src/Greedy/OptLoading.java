package Greedy;

import java.util.Arrays;

@SuppressWarnings("unchecked")
// 集装箱类
class LContainer implements Comparable {
	private int id; //编号  
	private double weight; //重量  
	private boolean loadFlag = false; //是否装入集装箱标识  

	public LContainer(int id,double weight) {
		this.id = id;
		this.weight = weight;
	}

	/**
	* 因为要对对象进行排序，所以要实现java.util.Comparator接口的compareTo(T obj)方法，在该方法中自定义排序算法。
	*/
	public int compareTo(Object obj) {
		double ww = ((LContainer) obj).getWeight();
		if (this.weight > ww) {
			// 当方法返回 1 时 ：把参数对象往前放。(前o.weight , 后this.weight) 
			return 1;
		} else if (this.weight < ww) {
			// 当方法返回 -1 时 ：把参数对象往后放。(前this.weight , 后o.weight) 
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
		
		Arrays.sort(container); // 实现数组排序
		
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
		System.out.println("最优装载集装箱的数量：\n"+result);
		
		System.out.println("依次装载的顺序编号如下：");
		for (int i = 0; i < container.length; i++)  
        {  
            if (container[i].getLoadFlag())  
            {  
                System.out.print(container[i].getId() + " ");  
            }  
        }  
	}

}
