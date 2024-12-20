package lab1.join;

public class JoinDemo4 {

    public static void main(String[] args) throws InterruptedException {
    	Thread t2 = new Thread(() -> {
            System.out.println("下班");
        });
    	
        Thread t1 = new Thread(() -> {
        	int count = 10;
        	for(int i=1;i<=count;i++) {
        		System.out.printf("今天要做 %d 工作, 目前已完成: %d / %d\n", count, i, count);
        		try {
					Thread.sleep(1000);
				} catch (Exception e) {
					
				}
        	}
        	// 工作做完才能下班
        	t2.start();
        });

        Thread urgentTask = new Thread(() -> {
            System.out.println("處理緊急任務~~");
        });

        t1.start();
        
        try {
            Thread.sleep(3000);
            Thread.currentThread().interrupt(); // 中斷主執行緒, 觸發 InterruptedException
            urgentTask.start(); // 處理緊急任務執行緒
            urgentTask.join();
        } catch (InterruptedException e) {
            System.out.println("下班情緒被打斷, 主管交辦緊急任務...");
        }

        
    }
}
