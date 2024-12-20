package lab1.join;

public class JoinDemo {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			System.out.println("上車");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("投幣/刷卡");
		});
		
		Thread t3 = new Thread(() -> {
			System.out.println("下車");
		});
		
		
		t1.start();
		t1.join(); // 等待 t1 完成後才進入下一個程序
		
		t3.start();
		t3.join();
		
		t2.start();
		t2.join();
		
		
	}
	
	
}
