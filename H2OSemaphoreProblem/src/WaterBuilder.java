import java.util.concurrent.Semaphore;

public class WaterBuilder {
    Semaphore mutex = new Semaphore(1);
    Semaphore oxyQueue = new Semaphore(0);
    Semaphore hydroQueue = new Semaphore(0);
    
    private int waterCount = 0;

    private int currentOxyCount = 0;
    private int currenthydroCount = 0;
        
    public void createOxygen(int atomId) {
    	try {
            mutex.acquire();
            
            currentOxyCount++;
            
            System.out.println("Oksijen Olusturuldu ID: "+ atomId +" Anlik Oksijen Sayisi: " + currentOxyCount);
           
            if (currenthydroCount >= 2){
                hydroQueue.release(2);
                oxyQueue.release();
            }
            
            else {
                mutex.release();
            }
            
            oxyQueue.acquire();
            
            bond(atomId, 'O');
            
            createWater(atomId);
            mutex.release();
        } catch (InterruptedException e)  { System.out.println(e.getMessage()); }
    }
    
    public void createHydrogen(int atomId) {
    	  try {
              mutex.acquire();
              
              currenthydroCount++;
              
              System.out.println("Hidrojen Olusturuldu ID: " + atomId + " Anlik Hidrojen Sayisi: " + currenthydroCount );
              
              if(currenthydroCount >= 2 && currentOxyCount >= 1){
                  hydroQueue.release(2);
                  oxyQueue.release();
              }
              else {
                  mutex.release();
              }
              
              hydroQueue.acquire();
              bond(atomId, 'H');
          }
    	  catch (InterruptedException e) { System.out.println(e.getMessage()); }
    }
    
    public void createWater(int atomId){
        System.err.println("------------------------Su Olusturuluyor------------------------");
        
        currenthydroCount = currenthydroCount - 2;
        currentOxyCount--;
        waterCount++;
        
        System.err.println("Su Olusturuldu "+ atomId + " Nolu Oksijen Tarafindan.");
        System.err.println("Olusturulan Toplam Su Sayisi: " + waterCount);
        System.err.println("-------------------------------W--------------------------------");
    }

    public void bond(int atomId, char atomName){
        System.out.println(atomId + " IDli Hidrojen Bag Kurmaya Hazir                             " + atomName);
    }
}