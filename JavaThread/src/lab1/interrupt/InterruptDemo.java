package lab1.interrupt;

// 執行緒中斷
public class InterruptDemo {
	
	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(() -> {
			try {
				for(int i=1;true;i++) {
					System.out.println(i);
					Thread.sleep(1000); // 延遲 1s
				}
			} catch (InterruptedException e) {
				System.out.println("執行緒被中斷");
			}
		});
		
		t1.start();
		
		Thread.sleep(3000); // 主執行緒等待 3 秒後中斷 t1 執行緒
		
		t1.interrupt(); // 進行中斷干擾
		
	}
	
}
