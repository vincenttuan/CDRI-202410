package lab1.runnable;

// 設計二個工作給執行緒來執行
class Job1 implements Runnable {
	@Override
	public void run() {
		for(int i=1;i<=1000;i++) {
			String tName = Thread.currentThread().getName(); // 目前正在執行的執行緒名稱
			System.out.printf("%s 爬了 %d 公尺\n", tName, i);
		}
	}
}

class Job2 implements Runnable {
	@Override
	public void run() {
		for(int i=1;i<=1000;i++) {
			String tName = Thread.currentThread().getName();
			System.out.printf("%s 跑了 %d 公尺\n", tName, i);
		}
	}
}

class Job3 implements Runnable {
	@Override
	public void run() {
		while (true) {
			String tName = Thread.currentThread().getName();
			System.err.printf("\t[%s] 跟拍作業中\n", tName);
		}
	}
}

public class RunnableDemo {
	public static void main(String[] args) {
		// 建立工作 
		Runnable job1 = new Job1(); // 工作 1
		Runnable job2 = new Job2(); // 工作 2
		Runnable job3 = new Job3(); // 工作 3
		// 建立執行緒
		Thread t1 = new Thread(job1); // User Thread 使用者執行緒 
		Thread t2 = new Thread(job2); // User Thread 使用者執行緒
		Thread t3 = new Thread(job3); // Daemon Thread 背景執行緒
		// 設定背景執行緒
		t3.setDaemon(true);
		// 設定執行緒名稱
		t1.setName("烏龜");
		t2.setName("兔子");
		t3.setName("攝影車");
		// 設定執行緒權限(1(min)~10(max), 預設:5)
		t1.setPriority(10);
		t2.setPriority(Thread.MIN_PRIORITY); // 1
		// 啟動執行緒
		t1.start();
		t2.start();
		t3.start();
	}
}
