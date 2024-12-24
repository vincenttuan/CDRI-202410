package sync;

public class Main {
	public static void main(String[] args) {
		// 帳戶資源
		Account account = new Account(10000);
		// 提款任務
		Runnable r1 = new Withdraw(account, 5000);
		Runnable r2 = new Withdraw(account, 4000);
		Runnable r3 = new Withdraw(account, 3000);
		// 執行任務
		Thread t1 = new Thread(r1, "John");
		Thread t2 = new Thread(r2, "Jack");
		Thread t3 = new Thread(r3, "Rose");
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}
