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

public class RunnableDemo {
	public static void main(String[] args) {
		 
	}
}
