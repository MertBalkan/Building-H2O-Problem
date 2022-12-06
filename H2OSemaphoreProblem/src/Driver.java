import java.util.Random;

public class Driver {
	public static void main(String[] args) {
		
	    int definedAtomCount = 6;
	    
	    Thread[] oxyThreads = new Thread[definedAtomCount];
	    Thread[] hydroThreads = new Thread[definedAtomCount];
	    
	    WaterBuilder waterBuilder = new WaterBuilder();
	    
	    int oxyId = 0;
	    int hydroId = 0;
	    
	    Random random = new Random();
	    boolean oxygenOrHydrogen;

	    while((hydroId + oxyId) != definedAtomCount){
            
	    	oxygenOrHydrogen = random.nextBoolean();
            
            if (oxygenOrHydrogen){
                oxyThreads[oxyId] = new Thread(new Oxygen(oxyId, waterBuilder));
                oxyThreads[oxyId].start();
                oxyId++;
            }
            
            else if(!oxygenOrHydrogen){
                hydroThreads[hydroId] = new Thread(new Hydrogen(hydroId, waterBuilder));
                hydroThreads[hydroId].start();
                hydroId++;
            }
        }
	    
        System.out.println("Donguden cikti *****************************");
	}
}
