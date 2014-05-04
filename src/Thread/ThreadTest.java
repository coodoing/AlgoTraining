package Thread;

public class ThreadTest {
	/**
	 * http://blog.csdn.net/gang00ge/article/details/5784360
	 * ���߳�ѭ��10�Σ��������߳�ѭ��100�������ֻص����߳�ѭ��10�Σ������ٻص����߳���ѭ��100�����ѭ��50�Σ���д������
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
		
		// ���õ�ǰ�����̽�������
		for(int i=0;i<50;i++)
		{
			business.MainThread(i);
		}		
		
		// ����һ���߳̽�������
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
	
	// violate ָ���������Ż�
	private class Business
	{
		volatile boolean bShouldSub = true;//�����൱�ڶ����˿��Ƹ�˭ִ�е�һ���źŵ�
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