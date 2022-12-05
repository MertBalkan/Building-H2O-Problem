import java.util.Random;
import java.util.concurrent.Semaphore;

public class WaterBuilder {
    Semaphore mutex = new Semaphore(1);
    Semaphore barrier = new Semaphore(3);
    Semaphore oxyQueue = new Semaphore(0);
    Semaphore hydroQueue = new Semaphore(0);
    public static int waterCount=0;
    int definedElementCount = 6;
    Thread[] oxyThreads = new Thread[definedElementCount];
    Thread[] hydroThreads = new Thread[definedElementCount];

    int oxyId=0;
    int hydroId=0;


    public static int currentOxyCount = 0;
    public static int currenthydroCount = 0;
    Random random = new Random();
    Boolean oxygenOrHydrogen;


    public void WaterBuilder() {
        while((hydroId+oxyId) != definedElementCount){
            oxygenOrHydrogen = random.nextBoolean();
            System.out.println(oxygenOrHydrogen);
            if (oxygenOrHydrogen == true){
                oxyThreads[oxyId] = new Thread(new Oxygen(oxyId,mutex,oxyQueue,hydroQueue,barrier));
                oxyThreads[oxyId].start();
                oxyId++;

            }
            else if(oxygenOrHydrogen == false){
                hydroThreads[hydroId] = new Thread(new Hydrogen(hydroId,mutex,oxyQueue,hydroQueue,barrier));
                hydroThreads[hydroId].start();
                hydroId++;
            }
        }
        System.out.println("Döngüden çıktı *****************************");
    }

    public static void main(String[] args) {
        WaterBuilder waterBuilder = new WaterBuilder();
        waterBuilder.WaterBuilder();
    }

}
