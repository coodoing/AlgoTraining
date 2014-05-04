package Thread;

/**
*
* http://blog.csdn.net/qingfengliuquan/article/details/5891187
* <p>
* Title: 迅雷笔试的测验
* </p>
* <p>
* Description: 把abc 看做是三盏灯，循环亮，三个开关控制三个灯的，三个人代表三个线程 每次先看前一盏灯的亮灯的情况
* 在看自己的情况，决定是否亮灯，然后将亮灯的消息通知给下个人
* </p>
* <p>
* Copyright: Copyright (c)
* </p>
* <p>
* Company:
* </p>
*/
public class SemaphoreABC implements java.lang.Runnable {
	// 内部开关类 isLighting=true 表示灯开
	private class Switch {
		public Switch(boolean printedSingal) {
			this.isLighting = printedSingal;
		}

		public boolean isLighting;
	}

	// a灯的开关
	Switch aSwitch = new Switch(false);

	// b灯的开关
	Switch bSwitch = new Switch(false);

	// c灯的开关 初始化 默认为开着，这样下次a灯才能亮
	Switch cSwitch = new Switch(true);

	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 10; i++) {
			// 如果是A线程
			if (Thread.currentThread().getName().equals("A")) {
				// 总是先去看前一盏灯的状态后，在决定是否轮到自己亮了
				// A去申请控制等c灯的开关，如果此时c的开关空闲，A锁住开关，防止他人摆弄
				synchronized (cSwitch) {
					// 轮询上一盏灯c，如果c一直灭着，则等待，由于我们已经默认初始化c亮着
					// 故不会死锁
					while (!cSwitch.isLighting) {
						try {
							// 等着c亮的好消息
							cSwitch.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// c终于亮了，该哥a亮了 先打印出自己
					System.out.print(Thread.currentThread().getName());
					// 关掉c灯
					cSwitch.isLighting = false;
					// A去申请控制等a灯的开关，如果此时a的开关空闲，A锁住开关，防止他人摆弄
					synchronized (aSwitch) {
						// a灯亮了
						aSwitch.isLighting = true;
						// 将a灯亮的消息通知给B
						aSwitch.notify();
					}

				}
			} // B线程上场了，同上
			else if (Thread.currentThread().getName().equals("B")) {
				synchronized (aSwitch) {
					while (!aSwitch.isLighting) {

						try {
							aSwitch.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.print(Thread.currentThread().getName());
					aSwitch.isLighting = false;
					synchronized (bSwitch) {

						bSwitch.isLighting = true;
						bSwitch.notify();
					}

				}
			} else if (Thread.currentThread().getName().equals("C")) {

				synchronized (bSwitch) {
					while (!bSwitch.isLighting) {
						try {
							bSwitch.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.print(Thread.currentThread().getName());
					bSwitch.isLighting = false;
					synchronized (cSwitch) {
						cSwitch.isLighting = true;
						cSwitch.notify();

					}
				}
			}
		}
	}

	public static void main(String[] args) {
		SemaphoreABC rt = new SemaphoreABC();
		Thread aThread = new Thread(rt, "A");
		Thread bThread = new Thread(rt, "B");
		Thread cThread = new Thread(rt, "C");
		aThread.start();
		bThread.start();
		cThread.start();
	}
}