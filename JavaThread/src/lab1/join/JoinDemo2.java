package lab1.join;

public class JoinDemo2 {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			System.out.println("開始-瀏覽商品");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("挑選商品-放入購物車");
		});
		
		Thread t3 = new Thread(() -> {
			System.out.println("結帳-商品計價");
		});
		
		Thread t4 = new Thread(() -> {
			System.out.println("結帳-進入付款流程");
		});
		
		Thread t5 = new Thread(() -> {
			System.out.println("結束-付款完成");
		});
		
		t1.start();
		t1.join(); // 等待 t1 完成後才進入下一個程序
		
		t2.start();
		t2.join();
		
		t3.start();
		t3.join();
		
		t4.start();
		t4.join();
		
		t5.start();
		
		
	}
	
	
}
