package lab1.group;

import java.util.Date;
import java.util.Random;

// 執行緒群組管理
public class ThreadGroupExample {
	
	public static void main(String[] args) {
		Runnable job1 = () -> {
			Random random = new Random();
			System.out.println("幸運數字: " + random.nextInt(100));
		};
		
		Runnable job2 = () -> {
			System.out.println("現在時間: " + new Date());
		};
		
		Runnable job3 = () -> {
			Random random = new Random();
			System.out.println("目前氣溫: " + random.nextInt(10) + 10);
		};
		
		// 創建一個執行緒群組
		ThreadGroup group1 = new ThreadGroup("mygroup1");
		ThreadGroup group2 = new ThreadGroup("mygroup2");
		
		// 添加執行緒到執行緒群組中
		Thread t1 = new Thread(group1, job1);
		Thread t2 = new Thread(group2, job2);
		Thread t3 = new Thread(group1, job3);
		
		t1.start();
		t2.start();
		t3.start();
		
		// 觀察執行緒群組中的執行緒執行狀態
		ThreadGroup group = group2;
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads);
		
		for(Thread t : threads) {
			System.out.println(group.getName() +" 活動執行緒:" + t.getName());
		}
	}
	
}

















