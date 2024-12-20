package lab1.join;

public class JoinDemo3 {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
            System.out.println("起床");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("刷牙洗臉");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("吃早餐");
        });

        Thread t4 = new Thread(() -> {
            System.out.println("出門上班");
        });

        Thread t5 = new Thread(() -> {
            System.out.println("抵達辦公室開始工作");
        });
        
        Thread t6 = new Thread(() -> {
            System.out.println("下班");
        });
		
		t1.start();
		t1.join();
		
		t2.start();
		t2.join();
		
		t3.start();
		t3.join();
		
		t4.start();
		t4.join();
		
		t5.start();
		t5.join();
		
		t6.start();
		t6.join();
		
	}
	
	
}
