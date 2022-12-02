import java.util.concurrent.Semaphore;

public class H2OBuilder {
	
	private Semaphore mutex;
	private Semaphore oxygenQueue;
	private Semaphore hydrogenQueue;
	private Semaphore barrier;
	
	private int hydrogenCount;
	private int oxygenCount;
	
	public H2OBuilder() {
		mutex = new Semaphore(1);
		oxygenQueue = new Semaphore(0);
		hydrogenQueue = new Semaphore(0);
		barrier = new Semaphore(3);
		
		hydrogenCount = 0;
		oxygenCount = 0;
	}
	
	public void createOxygen(int index) {
		try {
			mutex.acquire();
			
			oxygenCount++;
			System.out.println(index + ".OXYGEN CREATED");
			
			if(hydrogenCount >= 2)
				createWater();
				
			mutex.release();
			oxygenQueue.acquire();

			makeBound();
			barrier.acquire();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void createHydrogen(int index) {
		try {
			mutex.acquire();
			
			hydrogenCount++;
			System.out.println(index + ".HYDROGEN CREATED");
		
			if(hydrogenCount >= 2 && oxygenCount >= 1) 
				createWater();
			
			mutex.release();
			
			hydrogenQueue.acquire();
			makeBound();
			barrier.acquire();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void createWater() {
		barrier.release();
		
		hydrogenQueue.release();
		hydrogenCount -= 2;
		
		oxygenQueue.release();
		oxygenCount--;
	
		System.out.println("WATER CREATED! DRINK!!!!");
	
	}
	
	private void makeBound() {
		System.out.println("New Bound Created!");
	}
}