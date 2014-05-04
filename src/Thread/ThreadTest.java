package Thread;

public class ThreadTest {
	/**
	 * http://blog.csdn.net/gang00ge/article/details/5784360
	 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次，请写出程序。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadTest().init();
	}
	public void init()
	{
		final Business business = new Business();
		new Thread(
				new Runnable()
				{
					public void run() {
						for(int i=0;i<10;i++)
						{
							business.SubThread(i);
						}						
					}
					
				}
		
		).start();
		
		// 利用当前主进程进行运行
		for(int i=0;i<50;i++)
		{
			business.MainThread(i);
		}		
		
		// 打开另一个线程进行运行
		/*new Thread(
				new Runnable()
				{
					public void run() {
						for(int i=0;i<10;i++)
						{
							business.MainThread(i);
						}						
					}
					
				}
		
		).start();*/
		
	}
	
	// violate 指令重排序优化
	private class Business
	{
		volatile boolean bShouldSub = true;//这里相当于定义了控制该谁执行的一个信号灯
		public synchronized void MainThread(int i)
		{
			if(bShouldSub)
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			for(int j=0;j<5;j++)
			{
				System.out.println(Thread.currentThread().getName() + ":i=" + i +",j=" + j);
			}
			bShouldSub = true;
			this.notify();
		
		}
		
		
		public synchronized void SubThread(int i)
		{
			if(!bShouldSub)
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			for(int j=0;j<2;j++)
			{
				System.out.println(Thread.currentThread().getName() + ":i=" + i +",j=" + j);
			}
			bShouldSub = false;				
			this.notify();			
		}
	}
}