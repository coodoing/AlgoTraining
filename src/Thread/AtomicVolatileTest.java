package Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类描述：通过20个线程分别自增1000次，获取最后的值
 * 
 * volatile不能保证原子性,只能保证可见性。所以这里测试用CAS原子操作类
 * 
 * */ 
public class AtomicVolatileTest {

	public static AtomicInteger atom = new AtomicInteger(0);
	
	public static void increase()
	{
		atom.incrementAndGet();
	}
	
	public static final int THREAD_NUMBER = 20;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread[] threads = new Thread[THREAD_NUMBER];
		for(int i=0;i<THREAD_NUMBER;i++)
		{
			threads[i] = new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							for(int i=0;i<1000;i++)
							{
								increase();
							}
						}
					}
			);
			threads[i].start();
		}
		
		while(Thread.activeCount()>1)
			Thread.yield();
		
		System.out.println(atom);
	}

}
