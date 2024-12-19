package lab1.runnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RunnableDemo2 {
	public static void main(String[] args) {
		Runnable job1 = () -> {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S E");
			System.out.println("現在時刻:" + sdf.format(new Date()));
		};
		
		Runnable job2 = () -> {
			System.out.println("我要上廁所");
		};
		
		// 單工模式
		//job1.run();
		//job2.run();
		
		// 多執行緒
		new Thread(job1).start();
		new Thread(job2).start();
		
	}
}
