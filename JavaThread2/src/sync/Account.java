package sync;

public class Account {
	
	private int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}

	public void withdraw(int amount) {
		String tName = Thread.currentThread().getName();
		System.out.println(tName + " 進來提款 $" + amount);
		
		int currentBalance = balance; // 取得最新餘額
		if(currentBalance >= amount) {
			balance = currentBalance - amount; // 修改餘額
			System.out.println(tName + " 提款 $" + amount + " 成功, 餘額 $" + balance);
		} else {
			System.out.println(tName + " 提款 $" + amount + " 失敗, 餘額 $" + balance);
		}
		
	}
	
}
