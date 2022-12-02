public class Driver {
	public static void main(String[] args) {
		Thread[] oxygenThreads = new Thread[3];
		Thread[] hydrogenThread = new Thread[8];
	
		H2OBuilder h2oBuilder = new H2OBuilder();
		 
		for(int i = 0; i < 3; i++) {
			oxygenThreads[i] = new Thread(new Oxygen(h2oBuilder, i));
			oxygenThreads[i].start();
		}
		

		for(int i = 0; i < 8; i++) {
			hydrogenThread[i] = new Thread(new Hydrogen(h2oBuilder, i));
			hydrogenThread[i].start();
		}
		
		
		
	}
}